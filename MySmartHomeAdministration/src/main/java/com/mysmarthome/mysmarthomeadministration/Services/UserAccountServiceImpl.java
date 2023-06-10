package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.DAO.RoleRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.UserAccountRepository;
import com.mysmarthome.mysmarthomeadministration.Entites.Role;
import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserAccountServiceImpl implements IUserAccountService{

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public UserAccount register(UserAccount account) throws Exception {
        Optional<Role> defaultRole = roleRepository.findByName("USER");
        if(defaultRole.isEmpty()) throw new Exception("Erreur lors de l'affectation du Role USER");
        Optional<Role> adminRole = roleRepository.findByName("ADMIN");
        if(adminRole.isEmpty()) throw new Exception("Erreur lors de l'affectation du Role ADMIN");
        Optional<UserAccount> newUser = userAccountRepository.findByMail(account.getMail());
        if(!newUser.isEmpty()) throw new Exception("Un utilisateur avec cette adresse mail existe déjà !");
        Collection<Role> roles = new ArrayList<Role>();
        roles.add(defaultRole.get());
        roles.add(adminRole.get());
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setRoles(roles);
        return userAccountRepository.save(account);
    }

    @Override
    public boolean isValid(UserAccount account) throws Exception {
        String password= bCryptPasswordEncoder.encode(account.getPassword());
        System.out.println("mot de passe : " +account.getPassword());
        System.out.println("mot de passe crypté : " +password);

        Optional<UserAccount> newUserAccount = userAccountRepository.findByMail(account.getMail());
        String passwordDB=newUserAccount.get().getPassword();
        System.out.println("mot de passe DB : " +passwordDB);
        if(newUserAccount.isEmpty()) {
            throw new Exception("Utilisateur inexistant!");
        }
        if (!password.equals(passwordDB)){
            throw new Exception("Login ou mot de passe incorrect !");
        }
        return newUserAccount.get().getActive();
    }


    @Override
    public UserAccount getUserAccount(String mail) throws Exception {
        Optional<UserAccount>connectedUser = userAccountRepository.findByMail(mail);
        if(connectedUser.isEmpty()) throw new Exception("Cette adresse mail n'a pas été trouvée !");
        return connectedUser.get();
    }

    @Override
    public void removeUserAccount(int idUserAccount) throws Exception {
        Optional<UserAccount> account = userAccountRepository.findById(idUserAccount);
        if(account.isEmpty()) throw new Exception("Le compte utilisateur n'existe pas !");
        userAccountRepository.delete(account.get());
    }




}