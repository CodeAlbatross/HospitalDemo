package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pat")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Integer id;
    @Column
    private String patName;

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示doctor不能为空。删除病人，不影响医生
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;//所属医生

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示病房不能为空。删除病人，不影响医病房
    @JoinColumn(name = "room_id")
    private Sickroom sickroom;//所属病房



    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public Sickroom getSickroom() {
        return sickroom;
    }

    public void setSickroom(Sickroom sickroom) {
        this.sickroom = sickroom;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", PatName='" + patName + '\'' +
                ", doctor=" + doctor +
                '}';
    }
}
