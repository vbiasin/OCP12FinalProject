package com.mysmarthome.mysmarthomeweb.Proxies;

import com.mysmarthome.mysmarthomeweb.Entites.Camera;
import com.mysmarthome.mysmarthomeweb.Entites.Sensor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MySmartHomeAdministration", url = "localhost:8180")
public interface MySmartHomeAdministrationDeviceProxy {
    @PostMapping(value = "/removeSensorBack")
    void removeSensor(@RequestBody int idSensor);

    @PostMapping(value = "/addSensorBack")
    Sensor addSensor(@RequestBody Sensor sensor);

    @PostMapping(value = "/removeCameraBack")
    void removeCamera(@RequestBody int idCamera);

    @PostMapping(value = "/addCameraBack")
    Camera addCamera(@RequestBody Camera camera);

    @GetMapping(value = "/getCamerasBack")
    List<Camera> getCameras();

    @GetMapping(value = "/getSensorsBack")
    List<Sensor> getSensors();




}
