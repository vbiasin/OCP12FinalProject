package com.mysmarthome.mysmarthomeweb.DAO;

import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    public Optional<UserAccount> findByMail(String mail);

}
