package com.mysmarthome.mysmarthomeweb.Controllers;

import com.mysmarthome.mysmarthomeweb.Entites.Sensor;
import com.mysmarthome.mysmarthomeweb.Entites.Camera;
import com.mysmarthome.mysmarthomeweb.Proxies.MySmartHomeAdministrationDeviceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebDeviceController {

    @Autowired
    private MySmartHomeAdministrationDeviceProxy deviceProxy;
    @GetMapping("/devices")
    public String devices(Model model) throws Exception{

        List<Sensor> pageListSensors;
        List<Camera> pageListCameras;
        try {

            pageListSensors = deviceProxy.getSensors();
            model.addAttribute("listSensors",pageListSensors);

            pageListCameras = deviceProxy.getCameras();
            model.addAttribute("listCameras",pageListCameras);



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "devices";
    }

    @PostMapping("/removeSensor")
    public String removeSensor(@RequestParam int idSensor) {
        deviceProxy.removeSensor(idSensor);
        return "redirect:devices";
    }

    @PostMapping("/addSensor")
    public String sensor(String name, String sensorType, String localisation) {
        Sensor sensor = new Sensor(name, sensorType,localisation);
        deviceProxy.addSensor(sensor);
        return  "redirect:devices";
    }

    @PostMapping("/removeCamera")
    public String removeCamera(@RequestParam int idCamera) {
        deviceProxy.removeCamera(idCamera);
        return "redirect:devices";
    }

    @PostMapping("/addCamera")
    public String camera(String cameraModel, String localisation, String ipAdress) {
        Camera camera = new Camera(cameraModel,localisation,ipAdress);
        deviceProxy.addCamera(camera);
        return  "redirect:devices";
    }

    @GetMapping("/camera")
    public String cameraVideo() {
        return "camera";
    }
}
