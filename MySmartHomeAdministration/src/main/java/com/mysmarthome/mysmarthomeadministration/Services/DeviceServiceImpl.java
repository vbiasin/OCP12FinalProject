package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.Entites.Camera;
import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {
    @Override
    public void removeSensor(int idSensor) throws Exception {

    }

    @Override
    public void removeCamera(int idCamera) throws Exception {

    }

    @Override
    public Sensor addSensor(Sensor sensor) throws Exception {
        return null;
    }

    @Override
    public Camera addCamera(Camera camera) throws Exception {
        return null;
    }

    @Override
    public List<Sensor> getSensors() throws Exception {
        return null;
    }

    @Override
    public List<Camera> getCameras() throws Exception {
        return null;
    }

    @Override
    public void displayDevice() throws Exception {

    }
}
