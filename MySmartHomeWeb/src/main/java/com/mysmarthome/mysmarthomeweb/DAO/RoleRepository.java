package com.mysmarthome.mysmarthomeweb.DAO;


import com.mysmarthome.mysmarthomeweb.Entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Optional<Role> findById(long id);
    public Optional<Role> findByName(String name);
}
