package com.orgaes.ls.Model;

public class Chat {

    private String sender;
    private String reciever;
    private String Messages;

    public Chat(String sender, String reciever, String messages) {
        this.sender = sender;
        this.reciever = reciever;
        Messages = messages;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getMessages() {
        return Messages;
    }

    public void setMessages(String messages) {
        Messages = messages;
    }
}
