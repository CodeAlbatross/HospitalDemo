package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  
 * @Author  lzyyy
 * @Date 2020-04-26 21:46:24 
 */

@Entity
@Table ( name ="tbl_card")
public class TblCard  implements Serializable {



	@Id
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "pat_add" )
	private String patAdd;

   	@Column(name = "pat_age" )
	private String patAge;

   	@Column(name = "pat_allergy_history" )
	private String patAllergyHistory;

   	@Column(name = "pat_bed_num" )
	private String patBedNum;

   	@Column(name = "pat_chief_complaint" )
	private String patChiefComplaint;

   	@Column(name = "pat_collection_date" )
	private String patCollectionDate;

   	@Column(name = "pat_current_medical_history" )
	private String patCurrentMedicalHistory;

   	@Column(name = "pat_date" )
	private String patDate;

   	@Column(name = "pat_date_of_medical_history_collection" )
	private String patDateOfMedicalHistoryCollection;

   	@Column(name = "pat_family_history" )
	private String patFamilyHistory;

   	@Column(name = "pat_gender" )
	private String patGender;

   	@Column(name = "pat_hometown" )
	private String patHometown;

   	@Column(name = "pat_marry" )
	private Boolean patMarry;

   	@Column(name = "pat_marry_history" )
	private String patMarryHistory;

   	@Column(name = "pat_medical_history_narrator" )
	private String patMedicalHistoryNarrator;

   	@Column(name = "pat_name" )
	private String patName;

   	@Column(name = "pat_nation" )
	private String patNation;

   	@Column(name = "pat_num" )
	private String patNum;

   	@Column(name = "pat_past_history" )
	private String patPastHistory;

   	@Column(name = "pat_physical" )
	private String patPhysical;

   	@Column(name = "pat_room" )
	private String patRoom;

   	@Column(name = "pat_work" )
	private String patWork;

   	@Column(name = "pat_work_place" )
	private String patWorkPlace;

   	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL) //JPA注释： 一对一 关系
	//referencedColumnName：参考列名,默认的情况下是列表的主键
	//nullable=是否可以为空，
	//insertable：是否可以插入，
	//updatable：是否可以更新
	//columnDefinition=列定义，
	//foreignKey=外键
	@JoinColumn(name = "pat_id")
	private Patient patient;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatAdd() {
		return this.patAdd;
	}

	public void setPatAdd(String patAdd) {
		this.patAdd = patAdd;
	}

	public String getPatAge() {
		return this.patAge;
	}

	public void setPatAge(String patAge) {
		this.patAge = patAge;
	}

	public String getPatAllergyHistory() {
		return this.patAllergyHistory;
	}

	public void setPatAllergyHistory(String patAllergyHistory) {
		this.patAllergyHistory = patAllergyHistory;
	}

	public String getPatBedNum() {
		return this.patBedNum;
	}

	public void setPatBedNum(String patBedNum) {
		this.patBedNum = patBedNum;
	}

	public String getPatChiefComplaint() {
		return this.patChiefComplaint;
	}

	public void setPatChiefComplaint(String patChiefComplaint) {
		this.patChiefComplaint = patChiefComplaint;
	}

	public String getPatCollectionDate() {
		return this.patCollectionDate;
	}

	public void setPatCollectionDate(String patCollectionDate) {
		this.patCollectionDate = patCollectionDate;
	}

	public String getPatCurrentMedicalHistory() {
		return this.patCurrentMedicalHistory;
	}

	public void setPatCurrentMedicalHistory(String patCurrentMedicalHistory) {
		this.patCurrentMedicalHistory = patCurrentMedicalHistory;
	}

	public String getPatDate() {
		return this.patDate;
	}

	public void setPatDate(String patDate) {
		this.patDate = patDate;
	}

	public String getPatDateOfMedicalHistoryCollection() {
		return this.patDateOfMedicalHistoryCollection;
	}

	public void setPatDateOfMedicalHistoryCollection(String patDateOfMedicalHistoryCollection) {
		this.patDateOfMedicalHistoryCollection = patDateOfMedicalHistoryCollection;
	}

	public String getPatFamilyHistory() {
		return this.patFamilyHistory;
	}

	public void setPatFamilyHistory(String patFamilyHistory) {
		this.patFamilyHistory = patFamilyHistory;
	}

	public String getPatGender() {
		return this.patGender;
	}

	public void setPatGender(String patGender) {
		this.patGender = patGender;
	}

	public String getPatHometown() {
		return this.patHometown;
	}

	public void setPatHometown(String patHometown) {
		this.patHometown = patHometown;
	}

	public Boolean getPatMarry() {
		return this.patMarry;
	}

	public void setPatMarry(Boolean patMarry) {
		this.patMarry = patMarry;
	}

	public String getPatMarryHistory() {
		return this.patMarryHistory;
	}

	public void setPatMarryHistory(String patMarryHistory) {
		this.patMarryHistory = patMarryHistory;
	}

	public String getPatMedicalHistoryNarrator() {
		return this.patMedicalHistoryNarrator;
	}

	public void setPatMedicalHistoryNarrator(String patMedicalHistoryNarrator) {
		this.patMedicalHistoryNarrator = patMedicalHistoryNarrator;
	}

	public String getPatName() {
		return this.patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public String getPatNation() {
		return this.patNation;
	}

	public void setPatNation(String patNation) {
		this.patNation = patNation;
	}

	public String getPatNum() {
		return this.patNum;
	}

	public void setPatNum(String patNum) {
		this.patNum = patNum;
	}

	public String getPatPastHistory() {
		return this.patPastHistory;
	}

	public void setPatPastHistory(String patPastHistory) {
		this.patPastHistory = patPastHistory;
	}

	public String getPatPhysical() {
		return this.patPhysical;
	}

	public void setPatPhysical(String patPhysical) {
		this.patPhysical = patPhysical;
	}

	public String getPatRoom() {
		return this.patRoom;
	}

	public void setPatRoom(String patRoom) {
		this.patRoom = patRoom;
	}

	public String getPatWork() {
		return this.patWork;
	}

	public void setPatWork(String patWork) {
		this.patWork = patWork;
	}

	public String getPatWorkPlace() {
		return this.patWorkPlace;
	}

	public void setPatWorkPlace(String patWorkPlace) {
		this.patWorkPlace = patWorkPlace;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"patAdd='" + patAdd + '\'' +
					"patAge='" + patAge + '\'' +
					"patAllergyHistory='" + patAllergyHistory + '\'' +
					"patBedNum='" + patBedNum + '\'' +
					"patChiefComplaint='" + patChiefComplaint + '\'' +
					"patCollectionDate='" + patCollectionDate + '\'' +
					"patCurrentMedicalHistory='" + patCurrentMedicalHistory + '\'' +
					"patDate='" + patDate + '\'' +
					"patDateOfMedicalHistoryCollection='" + patDateOfMedicalHistoryCollection + '\'' +
					"patFamilyHistory='" + patFamilyHistory + '\'' +
					"patGender='" + patGender + '\'' +
					"patHometown='" + patHometown + '\'' +
					"patMarry='" + patMarry + '\'' +
					"patMarryHistory='" + patMarryHistory + '\'' +
					"patMedicalHistoryNarrator='" + patMedicalHistoryNarrator + '\'' +
					"patName='" + patName + '\'' +
					"patNation='" + patNation + '\'' +
					"patNum='" + patNum + '\'' +
					"patPastHistory='" + patPastHistory + '\'' +
					"patPhysical='" + patPhysical + '\'' +
					"patRoom='" + patRoom + '\'' +
					"patWork='" + patWork + '\'' +
					"patWorkPlace='" + patWorkPlace + '\'' +

				'}';
	}

}
