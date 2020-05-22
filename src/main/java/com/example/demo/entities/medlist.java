package com.example.demo.entities;

public class medlist {
    private String medname;
    private String patname;
    private String costData;
    private String medunit;
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

    public String getCostData() {
        return costData;
    }

    public void setCostData(String costData) {
        this.costData = costData;
    }

    public String getMedunit() {
        return medunit;
    }

    public void setMedunit(String medunit) {
        this.medunit = medunit;
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
