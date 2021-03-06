package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;

    @Column
    private float medicineCost;

    @Column
    private String medicineName;

    @Column
    private String medicineUnit;

    @ManyToMany()
    @JoinTable(name = "tbl_pat_medicines",joinColumns = {@JoinColumn(name = "medicines_id")},inverseJoinColumns = {@JoinColumn(name = "patients_id")})
    private Set<Patient> patients = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getMedicineCost() {
        return medicineCost;
    }

    public void setMedicineCost(float medicineCost) {
        this.medicineCost = medicineCost;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(String medicineUnit) {
        this.medicineUnit = medicineUnit;
    }
}
