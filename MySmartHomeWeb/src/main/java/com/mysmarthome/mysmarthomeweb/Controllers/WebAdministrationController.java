package com.mysmarthome.mysmarthomeweb.Controllers;

import com.mysmarthome.mysmarthomeweb.DTO.RoleDTO;
import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import com.mysmarthome.mysmarthomeweb.Proxies.MySmartHomeAdministrationUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebAdministrationController {

    @Autowired
    private MySmartHomeAdministrationUserProxy userProxy;
    @GetMapping("/administration")
    public String administration() {

        return "administration";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam String userAccountMail, @RequestParam int idRole) {

        RoleDTO roleDTO = new RoleDTO(userAccountMail,idRole);
        userProxy.addRole(roleDTO);
        return  "administration";
    }

    @PostMapping("/searchUserAccount")
    public String searchUserAccount( @RequestParam(name="mail", defaultValue="toto@exemple.com") String mail,Model model) throws Exception {

        String value = mail;
        List<UserAccount> pageListUsers;
        try {

            pageListUsers = userProxy.searchUserAccount(value);
            model.addAttribute("listUsers",pageListUsers);



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "administration";
    }

    @PostMapping("/removeRole")
    public String removeRole(@RequestParam String userAccountMail,@RequestParam int idRole) {
        RoleDTO roleDTO = new RoleDTO(userAccountMail,idRole);
        userProxy.removeRole(roleDTO);
        return  "administration";
    }

    @PostMapping("/removeUserAccount")
    public String removeUserAccount(@RequestParam int idUserAccount) {
        userProxy.removeUserAccount(idUserAccount);
        return "redirect:administration";
    }


}
