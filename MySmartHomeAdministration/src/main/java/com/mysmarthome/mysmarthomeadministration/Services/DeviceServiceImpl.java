package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.DAO.CameraRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.RecordValueRepository;
import com.mysmarthome.mysmarthomeadministration.DAO.SensorRepository;
import com.mysmarthome.mysmarthomeadministration.Entites.Camera;
import com.mysmarthome.mysmarthomeadministration.Entites.RecordValue;
import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private RecordValueRepository recordValueRepository;

    @Override
    public void removeSensor(int idSensor) throws Exception {
        Optional<Sensor> sensor = sensorRepository.findById(idSensor);
        if(sensor.isEmpty()) throw new Exception("Le capteur n'existe pas !");
        sensorRepository.delete(sensor.get());
    }

    @Override
    public void removeCamera(int idCamera) throws Exception {
        Optional<Camera> camera = cameraRepository.findById(idCamera);
        if(camera.isEmpty()) throw new Exception("La caméra n'existe pas !");
        cameraRepository.delete(camera.get());
    }

    @Override
    public Sensor addSensor(Sensor sensor) throws Exception {
        Optional<Sensor> newSensor = sensorRepository.findByName(sensor.getName());
        if(!newSensor.isEmpty()) throw new Exception("Un capteur avec ce nom existe déjà !");
        return sensorRepository.save(sensor);
    }

    @Override
    public Camera addCamera(Camera camera) throws Exception {
        Optional<Camera> newCamera = cameraRepository.findByIpAdress(camera.getIpAdress());
        if(!newCamera.isEmpty()) throw new Exception("Une caméra avec cette adresse ipV4 existe déjà !");
        return cameraRepository.save(camera);
    }

    @Override
    public List<Sensor> getSensors() throws Exception {
        return sensorRepository.findAll();
    }

    @Override
    public List<Camera> getCameras() throws Exception {
        return cameraRepository.findAll();
    }

    @Override
    public List<RecordValue> getRecordValues(String sensorName) throws Exception {
        List<RecordValue> recordValues = new ArrayList<>();
        recordValues = recordValueRepository.findRecordValueBySensorName(sensorName);
        if(recordValues.isEmpty()) recordValues = recordValueRepository.findAll();
        return recordValues;
    }

}
