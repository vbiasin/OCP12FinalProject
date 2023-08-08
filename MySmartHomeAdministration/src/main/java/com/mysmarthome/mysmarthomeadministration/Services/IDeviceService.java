package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.Entites.Camera;
import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;

import java.util.List;

public interface IDeviceService {
    public void removeSensor(int idSensor) throws Exception;
    public void removeCamera(int idCamera) throws Exception;
    public Sensor addSensor(Sensor sensor) throws Exception;
    public Camera addCamera(Camera camera) throws Exception;
    public List<Sensor> getSensors() throws Exception;
    public List<Camera> getCameras() throws Exception;

}
