package com.mysmarthome.mysmarthomeweb.Entites;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class Sensor  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String sensorType;
    private String localisation;
    private boolean isActive;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<RecordValue> recordValues;

    public Sensor() {
        super();
        this.isActive=true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Collection<RecordValue> getRecordValues() {
        return recordValues;
    }

    public void setRecordValues(Collection<RecordValue> recordValues) {
        this.recordValues = recordValues;
    }
}
