package com.mysmarthome.mysmarthomeweb.Controllers;

import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import com.mysmarthome.mysmarthomeweb.Proxies.MySmartHomeAdministrationUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebUserAccountController {

    @Autowired
    MySmartHomeAdministrationUserProxy userProxy;

    @GetMapping("/")
    public String start() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/inscription")
    public String inscriptionForm( String mail, String password) {
        UserAccount userAccount = new UserAccount(mail,password);
        userProxy.registerUser(userAccount);
        return "redirect:administration";
    }


}
