package com.mysmarthome.mysmarthomeadministration.DAO;

import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    public Optional<UserAccount> findByMail(String mail);
    public Optional<UserAccount> findById(int userAccountId);
    public Optional<UserAccount> findByMailAndPassword(String mail, String password);
}
