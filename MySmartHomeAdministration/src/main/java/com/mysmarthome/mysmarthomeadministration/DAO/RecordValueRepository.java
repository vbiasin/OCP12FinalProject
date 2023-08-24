package com.mysmarthome.mysmarthomeadministration.DAO;

import com.mysmarthome.mysmarthomeadministration.Entites.RecordValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordValueRepository extends JpaRepository<RecordValue,Integer> {
    public List<RecordValue> findRecordValueBySensorName(String sensorName);
}
