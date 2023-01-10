package com.example.myapplication23;

import android.os.Build;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {
    private String Sender;
    private String Reciever;
    private String Content;
    private String Time;

    Message(){

    }

    Message(String message){
        ArrayList<String> arrayList = new ArrayList<>();
        String word="";
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)==','){
                arrayList.add(word);
                word="";
            }else{
                word+=message.charAt(i);
            }
        }


        this.Sender=arrayList.get(0);
        this.Reciever=arrayList.get(1);
        this.Content=arrayList.get(2);
        this.Time=arrayList.get(3);

    }


    Message(String sender, String reciever, String content,String time){
        this.Content = content;
        this.Reciever = reciever;
        this.Sender = sender;
        this.Time = time;
    }


    public String PrintMessage(){
        return "Sender: "+this.Sender+"\nReciever: "+this.Reciever+"\nContent: "+this.Content;
    }


    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
