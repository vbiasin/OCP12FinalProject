package com.mysmarthome.mysmarthomeweb.DAO;

import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    public Optional<UserAccount> findByMail(String mail);

}
