package com.javatechie.crud.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class addressVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String address;
	
	private String country;
	
	@ManyToMany(mappedBy = "addressVO",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonBackReference
	private List<studentVO> studentVO;

	
	

	public List<studentVO> getStudentVO() {
		return studentVO;
	}

	public void setStudentVO(List<studentVO> studentVO) {
		this.studentVO = studentVO;
	}

	@Override
	public String toString() {
		return "addressVO [id=" + id + ", address=" + address + ", country=" + country + ", studentVO=" + studentVO
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
