package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @Column
    private String patIdCardNum;//身份证号码
    @Column
    private final String role="patient";

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示doctor不能为空。删除病人，不影响医生
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;//所属医生

    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false)//可选属性optional=false,表示病房不能为空。删除病人，不影响医病房
    @JoinColumn(name = "room_id")
    private Sickroom sickroom;//所属病房


    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private TblCard tblCard;

    @ManyToMany()
    private Set<Medicine> medicines = new HashSet<>();



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

    public String getPatIdCardNum() {
        return patIdCardNum;
    }

    public void setPatIdCardNum(String idCardNum) {
        this.patIdCardNum = idCardNum;
    }

    public String getRole() {
        return role;
    }

    public TblCard getTblCard() {
        return tblCard;
    }

    public void setTblCard(TblCard tblCard) {
        this.tblCard = tblCard;
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(Set<Medicine> medicines) {
        this.medicines = medicines;
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
                ", patRoomName='" + patRoomName + '\'' +
                ", patBedNum=" + patBedNum +
                ", patTel='" + patTel + '\'' +
                ", patRemarks='" + patRemarks + '\'' +
                ", birth='" + birth + '\'' +
                ", patIdCardNum='" + patIdCardNum + '\'' +
                ", doctor=" + doctor +
                ", sickroom=" + sickroom +
                '}';
    }
}
