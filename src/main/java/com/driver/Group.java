package com.driver;

public class Group {
    private String name;
    private int numberOfParticipants;

    public Group(String name , int numberOfParticipants){
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public int getNumberOfParticipants(){return this.numberOfParticipants;}
    public void setNumberofParticipants(int numberOfParticipants){this.numberOfParticipants = numberOfParticipants;}
}
