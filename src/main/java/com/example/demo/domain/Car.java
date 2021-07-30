package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("Clave primaria id Long autoincrementada")
	private Long id;
	
	//@Column(name="manufacturer")
	private String manufacturer;
	private String model;
	private Double cc;
	@Column(name="doors")
	private Integer numDoors;
	private Integer year;
	private LocalDate releaseDate;
	private Boolean unavailable;
	

	public Car() {
	}
	
	public Car(Long id, String manufacturer, String model, Double cc, Integer numDoors, Integer year, LocalDate releaseDate, Boolean unavailable) {
		super();
		this.id = id;
		this.manufacturer = manufacturer;
		this.model = model;
		this.cc = cc;
		this.numDoors = numDoors;
		this.releaseDate=releaseDate;
		this.year = year;
		this.unavailable=unavailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getCc() {
		return cc;
	}

	public void setCc(Double cc) {
		this.cc = cc;
	}

	public Integer getNumDoors() {
		return numDoors;
	}

	public void setNumDoors(Integer numDoors) {
		this.numDoors = numDoors;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	

	public Boolean getUnavailable() {
		return unavailable;
	}

	public void setUnavailable(Boolean unavailable) {
		this.unavailable = unavailable;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", manufacturer=" + manufacturer + ", model=" + model + ", cc=" + cc + ", numDoors="
				+ numDoors + ", year=" + year + ", releaseDate=" + releaseDate + ", unavailable=" + unavailable + "]";
	}


	
	


	
	
	
	
}
