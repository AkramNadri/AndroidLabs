package com.example.myapplication;

public class Message {
    private  String text;
    private boolean isSend;

    public Message(String text, boolean isSend) {
        this.text = text;
        this.isSend = isSend;
    }

    public String getText() {
        return text;
    }

    public boolean isSend() {
        return isSend;
    }
}