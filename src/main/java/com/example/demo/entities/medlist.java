package com.example.demo.entities;

public class medlist {
    private String medname;
    private String patname;
    private float medcost;
    private int count;

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getMedcost() {
        return medcost;
    }

    public void setMedcost(float medcost) {
        this.medcost = medcost;
    }

    @Override
    public String toString() {
        return "medlist{" +
                "medname='" + medname + '\'' +
                ", patname='" + patname + '\'' +
                ", count=" + count +
                '}';
    }
}
