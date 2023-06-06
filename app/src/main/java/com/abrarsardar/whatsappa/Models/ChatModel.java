package com.abrarsardar.whatsappa.Models;

public class ChatModel {
    int profilepic;
    String name, message, time;

    public ChatModel(int profilepic, String name, String message, String time) {
        this.profilepic = profilepic;
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public int getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(int profilepic) {
        this.profilepic = profilepic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
