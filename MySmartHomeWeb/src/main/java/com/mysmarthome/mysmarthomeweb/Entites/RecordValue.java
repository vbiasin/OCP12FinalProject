package com.mysmarthome.mysmarthomeweb.Entites;

import jakarta.persistence.*;
import com.mysmarthome.mysmarthomeweb.Entites.Sensor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;


@Entity
public class RecordValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String sensorName;
    private String value;
    private LocalDateTime recordDate;
    @ManyToMany(mappedBy = "recordValues")
    private Collection<Sensor> sensors;

    public RecordValue() {
        super();
        this.recordDate= LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public Collection<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Collection<Sensor> sensors) {
        this.sensors = sensors;
    }
}
