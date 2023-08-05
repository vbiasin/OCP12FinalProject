package com.mysmarthome.mysmarthomeadministration.Services;

import com.mysmarthome.mysmarthomeadministration.Entites.UserAccount;

import java.util.List;


public interface IUserAccountService {

    public UserAccount register(UserAccount account) throws Exception;
    public boolean isValid(UserAccount account) throws Exception;
    public UserAccount getUserAccount(String mail) throws Exception;
    public void removeUserAccount(int idUserAccount) throws Exception;
    public List<UserAccount> searchUserAccount(String mail) throws Exception;


}