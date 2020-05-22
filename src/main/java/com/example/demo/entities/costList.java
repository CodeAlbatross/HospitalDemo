package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_costlist")
public class costList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column
    private Float surgeryFee=0f;//手术费

    @Column
    private Float nursingFee=0f;//护理费

    @Column
    private Float consultationFee=0f;//诊查费

    @Column
    private Float inspectionFee=0f;//检查费

    @Column
    private Float labFee=0f;//化验费

    @Column
    private String costData;//记录日期

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSurgeryFee() {
        return surgeryFee;
    }

    public void setSurgeryFee(Float surgeryFee) {
        this.surgeryFee = surgeryFee;
    }

    public Float getNursingFee() {
        return nursingFee;
    }

    public void setNursingFee(Float nursingFee) {
        this.nursingFee = nursingFee;
    }

    public Float getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(Float consultationFee) {
        this.consultationFee = consultationFee;
    }

    public Float getLabFee() {
        return labFee;
    }

    public void setLabFee(Float labFee) {
        this.labFee = labFee;
    }

    public Float getInspectionFee() {
        return inspectionFee;
    }

    public void setInspectionFee(Float inspectionFee) {
        this.inspectionFee = inspectionFee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getCostData() {
        return costData;
    }

    public void setCostData(String costData) {
        this.costData = costData;
    }

    public Float totalCost(){
        return consultationFee+inspectionFee+labFee+nursingFee+surgeryFee;
    }
}
