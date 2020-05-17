package com.example.demo.entities;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pat_medicines")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PatMed {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patients_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "medicines_id")
    private Medicine medicine;

    @JoinColumn
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id=id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "PatMed{" +
                "id=" + id +
                ", patient=" + patient +
                ", medicine=" + medicine +
                ", count=" + count +
                '}';
    }
}
