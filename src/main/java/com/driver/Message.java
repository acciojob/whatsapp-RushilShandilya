package com.driver;

import java.util.Date;

public class Message {
    private int id;
    private String content;
    private Date timestamp;

    public Message(int id , String content , Date timestamp){
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }
    public int getID(){return this.id;}
    public String getContent(){return this.content;}
    public Date getTimestamp(){return this.timestamp;}

    public void setID(int id){this.id = id;}
    public void setContent(String content){this.content=content;}
    public void setTimestamp(Date timestamp){this.timestamp = timestamp;}

}
