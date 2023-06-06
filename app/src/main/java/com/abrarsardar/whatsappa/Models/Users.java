package com.abrarsardar.whatsappa.Models;

public class Users {

    String profilepic, usrName, mail, password, userId, lastMessage, userIdG, status, country, city;

    public Users(String profilepic, String usrName, String mail, String password, String userId, String lastMessage) {
        this.profilepic = profilepic;
        this.usrName = usrName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public Users(){}

    //signup constructor
    public Users(String usrName, String mail, String password, String country, String city) {
        this.usrName = usrName;
        this.mail = mail;
        this.password = password;
        this.country = country;
        this.city = city;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
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

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserIdG(String userIdG) {
        this.userIdG = userIdG;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
    public String getStatus(){
        return status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
