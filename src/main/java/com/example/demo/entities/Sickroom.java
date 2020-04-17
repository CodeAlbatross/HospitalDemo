package com.example.demo.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_room")
public class Sickroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Integer id;

    @Column
    private String roomName;//病房病人

    @Column
    private String roomAdminName;//病房负责人名称

    @Column
    private Integer bedMaxNum;//最大床位数

    @Column
    private Integer occupiedBed=0;//已被占床位

    @Column
    private String roomAdminTel;//负责人电话号码

    @Column
    private String roomAdd;//病房地址



    @OneToMany(mappedBy = "sickroom",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Patient> patients = new HashSet<>();//病人列表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomAdminName() {
        return roomAdminName;
    }

    public void setRoomAdminName(String roomAdminName) {
        this.roomAdminName = roomAdminName;
    }

    public Integer getBedMaxNum() {
        return bedMaxNum;
    }

    public void setBedMaxNum(Integer bedMaxNum) {
        this.bedMaxNum = bedMaxNum;
    }

    public Integer getOccupiedBed() {
        return occupiedBed;
    }

    public void setOccupiedBed(Integer occupiedBed) {
        this.occupiedBed = occupiedBed;
    }

    public String getRoomAdminTel() {
        return roomAdminTel;
    }

    public void setRoomAdminTel(String roomAdminTel) {
        this.roomAdminTel = roomAdminTel;
    }

    public String getRoomAdd() {
        return roomAdd;
    }

    public void setRoomAdd(String roomAdd) {
        this.roomAdd = roomAdd;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Sickroom{" +
                "id=" + id +
                ", patName='" + roomName + '\'' +
                ", roomAdminName='" + roomAdminName + '\'' +
                ", bedMaxNum=" + bedMaxNum +
                ", occupiedBed=" + occupiedBed +
                ", roomAdminTel='" + roomAdminTel + '\'' +
                ", roomAdd='" + roomAdd + '\'' +
               // ", patients=" + patients +
                '}';
    }
}
