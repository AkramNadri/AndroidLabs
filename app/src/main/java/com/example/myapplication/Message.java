package com.example.myapplication;


public class Message {


    protected long id;
    private  String text;
    private boolean isSend;



    public Message(long id,String text, boolean isSend) {
        this.id = id;
        this.text = text;
        this.isSend = isSend;
    }
    public Message(String text, long id) {

        this.text = text;
        this.id = id;
    }
    public Message(String text,boolean isSend) {
        this(0,text,isSend);
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public boolean isSend() {
        return isSend;
    }
    public void setSend (boolean isSend) {
        this.isSend = isSend;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}