package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Car;

public interface CarService {
	
	// Spring methods
	List<Car> findAll();
	
	Optional<Car> findById(Long id);

	Long count();
	
	Car save(Car car);
	
	void delete(Long id);
	
	void deleteAll();
	
	void deleteAll(List<Car> cars);

	void deleteAllById(List<Long> ids);
	
	
	//custom methods
	List<Car> findByDoors(Integer doors);
	
	public List<Car> findByManufacturerAndModel(String manufacturer, String model);
	
	public List<Car> findByManufacturerOrModel(String manufacturer, String model);
	
	public List<Car> findByNumDoorsGreaterThan(Integer doors);
	
	public List<Car> findByModelContaining(String model);
	
	public List<Car> findByYearIn(List<Integer> years);
	
	public List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate);
	
	public List<Car> findByUnavailableFalse();
	
	public Long deleteAllByAvailableFalse();
	
	
	
}
