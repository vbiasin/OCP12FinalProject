package com.mysmarthome.mysmarthomeadministration.DAO;

import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    public Optional<Sensor> findById(int id);
    public Optional<Sensor> findByName(String name);
}
