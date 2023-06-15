package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.DAO.RoleRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.UserAccountRepository;
import com.mysmarthome.mysmarthomeadministration.Entites.Role;
import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService{

    @Autowired
    @Qualifier("userAccountRepository")
    private UserAccountRepository userRepository;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;


    @Override
    public void addRoleToUserAccount(String mail, int idRole) throws Exception {
        Optional<UserAccount> userAccount = userRepository.findByMail(mail);
        if(userAccount.isEmpty()) throw new Exception("L'utilisateur spécifié n'existe pas !");
        Optional<Role> role = roleRepository.findById(idRole);
        if(role.isEmpty()) throw new Exception("Aucun rôle ne correspond à cet identifiant !");
        Collection<Role> roles =  userAccount.get().getRoles();
        // vérifier si rôle existe déjà
        boolean check = false;
        for (Role roleInCollection : roles) {
            if(roleInCollection.getName().equals(role.get().getName())) check=true;
        }
        if(!check) roles.add(role.get());
        userAccount.get().setRoles(roles);
        userRepository.saveAndFlush(userAccount.get());
    }

    @Override
    public void removeRoleFromUserAccount(String mail, int idRole) throws Exception {
        Optional<UserAccount> userAccount = userRepository.findByMail(mail);
        if(userAccount.isEmpty()) throw new Exception("L'utilisateur spécifié n'existe pas !");
        Optional<Role> role = roleRepository.findById(idRole);
        if(role.isEmpty()) throw new Exception("Aucun rôle ne correspond à cet identifiant !");
        Collection<Role> roles =  userAccount.get().getRoles();
        for (Role roleInCollection : roles) {
            if(roleInCollection.getName().equals(role.get().getName())){
                roles.remove(roleInCollection);
                break;
            }
        }
        userAccount.get().setRoles(roles);
        userRepository.saveAndFlush(userAccount.get());
    }

}