package com.mysmarthome.mysmarthomeweb.Services;

import com.mysmarthome.mysmarthomeweb.DAO.UserAccountRepository;
import com.mysmarthome.mysmarthomeweb.Entites.Role;
import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDetails implements UserDetailsService {


    @Autowired
    UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Optional<UserAccount> userAccount = userAccountRepository.findByMail(mail);


        if (userAccount.isEmpty())
        {
            throw new UsernameNotFoundException("Pas d'utilisateur avec l'identifiant : " + mail);
        }

        if (userAccount.get().getActive()==false)
        {
            throw new UsernameNotFoundException("L'utilisateur avec l'identifiant : " + mail+" a été désactivé");
        }


        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : userAccount.get().getRoles()){
            System.out.println(role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails result = new User(mail, userAccount.get().getPassword(), authorities);
        System.out.println("UserAccount = "+mail + " mot de passe = " + userAccount.get().getPassword() + " Role "+ authorities );
        return result;
    }

}