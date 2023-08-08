package com.mysmarthome.mysmarthomeadministration.Controllers;

import com.mysmarthome.mysmarthomeadministration.Services.IDeviceService;
import com.mysmarthome.mysmarthomeadministration.Entites.Sensor;
import com.mysmarthome.mysmarthomeadministration.Entites.Camera;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @PostMapping("/addSensorBack")
    public ResponseEntity<Sensor> addSensor(@RequestBody Sensor sensor) throws Exception {
        try {
            return new ResponseEntity<Sensor>(deviceService.addSensor(sensor), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addCameraBack")
    public ResponseEntity<Camera> addCamera(@RequestBody Camera camera) throws Exception {
        try {
            return new ResponseEntity<Camera>(deviceService.addCamera(camera), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/removeSensorBack")
    public ResponseEntity<String> removeSensor(@RequestBody int idSensor) throws Exception {
        try {
            deviceService.removeSensor(idSensor);
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/removeCameraBack")
    public ResponseEntity<String> removeCamera(@RequestBody int idCamera) throws Exception {
        try {
            deviceService.removeCamera(idCamera);
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getCamerasBack")
    public ResponseEntity<String> getCameras() throws Exception {
        try {
            deviceService.getCameras();
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getSensorsBack")
    public ResponseEntity<String> getSensors() throws Exception {
        try {
            deviceService.getSensors();
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
