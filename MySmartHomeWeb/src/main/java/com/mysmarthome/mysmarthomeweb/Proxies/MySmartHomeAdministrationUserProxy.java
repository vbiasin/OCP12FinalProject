package com.mysmarthome.mysmarthomeweb.Proxies;

import com.mysmarthome.mysmarthomeweb.DTO.RoleDTO;
import com.mysmarthome.mysmarthomeweb.Entites.UserAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MySmartHomeAdministration", url = "localhost:8280")
public interface MySmartHomeAdministrationUserProxy {
    @PostMapping(value = "/register")
    UserAccount registerUser(@RequestBody UserAccount userAccount);

    @PostMapping(value = "/loginBack")
    Boolean login(@RequestBody UserAccount userAccount);

    @PostMapping(value = "/addRoleBack")
    void addRole(@RequestBody RoleDTO roleDTO);

    @PostMapping(value = "/removeRoleBack")
    void removeRole(@RequestBody RoleDTO roleDTO);

    @PostMapping(value = "/removeUserAccountBack")
    void removeUserAccount(@RequestBody int idUserAccount);

}
