package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

	public List<Car> findByNumDoors(Integer doors);
	
	public List<Car> findByManufacturerAndModel(String manufacturer, String model);
	
	public List<Car> findByManufacturerOrModel(String manufacturer, String model);
	
	public List<Car> findByNumDoorsGreaterThan(Integer doors);
	
	public List<Car> findByModelContaining(String model);
	
	public List<Car> findByYearIn(List<Integer> years);
	
	public List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<Car> findByUnavailableFalse();
	
	public Long deleteAllByUnavailableFalse();
}
