package com.abrarsardar.whatsappa.Models;

public class SearchModel {
    String usrName, mail, password, userId;

    public SearchModel() {

    }

    public SearchModel(String usrName, String mail, String password, String userId) {
        this.usrName = usrName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
