package com.mysmarthome.mysmarthomeadministration.Controllers;

import com.mysmarthome.mysmarthomeadministration.Services.IRoleService;
import com.mysmarthome.mysmarthomeadministration.Services.IUserAccountService;
import com.mysmarthome.mysmarthomeadministration.DTO.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrationController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserAccountService userAccountService;

    @PostMapping("/addRoleBack")
    public ResponseEntity<String> addRole(@RequestBody RoleDTO roleDTO) throws Exception {
        try {
            roleService.addRoleToUserAccount(roleDTO.getMail(),roleDTO.getIdRole());
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/removeRoleBack")
    public ResponseEntity<String> removeRole(@RequestBody RoleDTO roleDTO) throws Exception {
        try {
            roleService.removeRoleFromUserAccount(roleDTO.getMail(),roleDTO.getIdRole());
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @PostMapping("/removeUserAccountBack")
    public ResponseEntity<String>removeUserAccount(@RequestBody int idUserAccount) throws Exception {
        try {
            userAccountService.removeUserAccount(idUserAccount);
            return new ResponseEntity<String>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
