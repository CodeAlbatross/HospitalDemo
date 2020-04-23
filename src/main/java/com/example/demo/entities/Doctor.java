package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_doc")
public class Doctor {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    private Integer id;
    @Column
    private String loginNum;
    @Column
    private String docName;
    @Column
    private String docPsw;
    @Column
    private String docSex;
    @Column
    private String docTel;
    @Column
    private String docAdd;
    @Column
    private String docEmail;
    @Column
    private String docDepartment;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //级联保存、更新、删除、刷新;延迟加载。当删除用户，会级联删除该用户的所有文章
    //拥有mappedBy注解的实体类为关系被维护端
    //mappedBy="doctor"中的doctor是Patient中的doctor属性

    private Set<Patient> patients = new HashSet<>();//病人列表



    public String getDocEmail() {
        return docEmail;
    }

    public void setDocEmail(String docEmail) {
        this.docEmail = docEmail;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPsw() {
        return docPsw;
    }

    public void setDocPsw(String docPsw) {
        this.docPsw = docPsw;
    }

    public String getDocSex() {
        return docSex;
    }

    public void setDocSex(String docSex) {
        this.docSex = docSex;
    }

    public String getDocTel() {
        return docTel;
    }

    public void setDocTel(String docTel) {
        this.docTel = docTel;
    }

    public String getDocAdd() {
        return docAdd;
    }

    public void setDocAdd(String docAdd) {
        this.docAdd = docAdd;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public String getDocDepartment() {
        return docDepartment;
    }

    public void setDocDepartment(String docDepartment) {
        this.docDepartment = docDepartment;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", loginNum='" + loginNum + '\'' +
                ", DocName='" + docName + '\'' +
                ", DocPsw='" + docPsw + '\'' +
                ", DocSex='" + docSex + '\'' +
                ", DocTel='" + docTel + '\'' +
                ", DocAdd='" + docAdd + '\'' +
                ", DocEmail='" + docEmail + '\'' +
                '}';
    }
}
