package com.te.studentsdata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name = "student_info")
@JsonRootName("student-info")
@JsonPropertyOrder({ "user_Id", "name" })
@XmlRootElement(name = "student-info")

public class Student implements Serializable {
	@Id
	@Column
	@JsonProperty("user_Id")
	private int user_Id;
	@Column
	private String email;
	@Column
	private double marks;
	@Column
	private String name;
	@Column
	private String grade;

}