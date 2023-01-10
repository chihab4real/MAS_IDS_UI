package com.example.myapplication23;

import java.io.Serializable;

public class ClsiData  {

    private String name;
    private int traindataSize;
    private double fMeasure;
    private double Precesion;
    private double FalsePositive;
    private double Kappa;

    ClsiData(){

    }

    ClsiData(String name,int traindataSize,double fMeasure,double precesion,double falsePositive,double kappa){
        this.name = name;
        this.traindataSize = traindataSize;
        this.fMeasure = fMeasure;
        this.Precesion = precesion;
        this.FalsePositive=falsePositive;
        this.Kappa=kappa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTraindataSize() {
        return traindataSize;
    }

    public void setTraindataSize(int traindataSize) {
        this.traindataSize = traindataSize;
    }

    public double getfMeasure() {
        return fMeasure;
    }

    public void setfMeasure(double fMeasure) {
        this.fMeasure = fMeasure;
    }

    public double getPrecesion() {
        return Precesion;
    }

    public void setPrecesion(double precesion) {
        Precesion = precesion;
    }

    public double getFalsePositive() {
        return FalsePositive;
    }

    public void setFalsePositive(double falsePositive) {
        FalsePositive = falsePositive;
    }

    public double getKappa() {
        return Kappa;
    }

    public void setKappa(double kappa) {
        Kappa = kappa;
    }
}
