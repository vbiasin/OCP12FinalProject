package com.mysmarthome.mysmarthomeadministration.Services;

public interface IRoleService {
    public void addRoleToUserAccount (String mail, int idRole) throws Exception;
    public void removeRoleFromUserAccount (String mail, int idRole) throws Exception;
}