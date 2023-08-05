package com.mysmarthome.mysmarthomeadministration.DTO;

public class UserAccountDTO {

    private String mail;


    public UserAccountDTO(String mail) {
        this.mail = mail;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}