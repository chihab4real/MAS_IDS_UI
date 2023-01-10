package com.example.myapplication23;

import com.anychart.anychart.DataEntry;

public class CustomDataEntry extends DataEntry {
    private String name;
    private int i;
    private int j;
    private int k;


    CustomDataEntry(String name,int i,int j,int  k){
        this.name=name;
        this.i=i;
        this.j=j;
        this.k=k;
    }
}
