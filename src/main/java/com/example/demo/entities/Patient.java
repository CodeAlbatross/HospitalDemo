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
    @Column
    private String patGender;
    @Column
    private Integer patAge;
    @Column
    private String patDoctor;
    @Column
    private String patIllness;
    @Column
    private String patRoomName;
    @Column
    private Integer patBedNum;
    @Column
    private String patTel;
    @Column
    private String patRemarks;
    @Column
    private String birth;//入院时间

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

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
    }

    public Integer getPatAge() {
        return patAge;
    }

    public void setPatAge(Integer patAge) {
        this.patAge = patAge;
    }

    public String getPatDoctor() {
        return patDoctor;
    }

    public void setPatDoctor(String patDoctor) {
        this.patDoctor = patDoctor;
    }

    public String getPatIllness() {
        return patIllness;
    }

    public void setPatIllness(String patIllness) {
        this.patIllness = patIllness;
    }

    public String getPatRoomName() {
        return patRoomName;
    }

    public void setPatRoomName(String patRoomNum) {
        this.patRoomName = patRoomNum;
    }

    public Integer getPatBedNum() {
        return patBedNum;
    }

    public void setPatBedNum(Integer patBedNum) {
        this.patBedNum = patBedNum;
    }

    public String getPatTel() {
        return patTel;
    }

    public void setPatTel(String patTel) {
        this.patTel = patTel;
    }

    public String getPatRemarks() {
        return patRemarks;
    }

    public void setPatRemarks(String patRemarks) {
        this.patRemarks = patRemarks;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", patName='" + patName + '\'' +
                ", patGender='" + patGender + '\'' +
                ", patAge=" + patAge +
                ", patDoctor='" + patDoctor + '\'' +
                ", patIllness='" + patIllness + '\'' +
                ", patRoomNum=" + patRoomName +
                ", patBedNum=" + patBedNum +
                ", patTel='" + patTel + '\'' +
                ", patRemarks='" + patRemarks + '\'' +
                ", birth=" + birth +

                ", sickroom=" + sickroom +
                '}';
    }
}
