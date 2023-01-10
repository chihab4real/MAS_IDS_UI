package com.example.myapplication23;

public class ContainerForAdapter {
    private String Name;
    private String ID;

    ContainerForAdapter(){

    }

    ContainerForAdapter(String name,String id){
        this.ID=id;
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
