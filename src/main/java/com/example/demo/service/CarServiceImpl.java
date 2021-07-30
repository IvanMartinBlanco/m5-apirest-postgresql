package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{

	private static final Integer MIN_DOORS = 3;
	private final Logger log=LoggerFactory.getLogger(CarServiceImpl.class);
	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository=carRepository;
	}
	
	
	@Override
	public List<Car> findAll() {
		log.info("Executing findAll Cars");
		return this.carRepository.findAll();
	}

	@Override
	public Optional<Car> findById(Long id) {
		log.info("Executing findOne");
		return this.carRepository.findById(id);
	}


	@Override
	public List<Car> findByDoors(Integer doors) {
		log.info("Searching by num doors");
		if(doors<MIN_DOORS) {
			log.info("Trying to search less than allowed doors");
			return new ArrayList<>();
		}
		return this.carRepository.findByNumDoors(doors);
	}


	@Override
	public Long count() {
		log.info("Get total number of cars");
		return this.carRepository.count();
	}


	@Override
	public Car save(Car car) {
		log.info("Trying to create a car");

		
		if(!this.validateCar(car)) {
			log.warn("Trying to create a invalid car");
			return null;
		}
		return this.carRepository.save(car);
	}


	private boolean validateCar(Car car) {

		if(car==null) {
			log.warn("Trying to create a null car");
			return false;
		}
		if(car.getNumDoors()==null || car.getNumDoors()<MIN_DOORS) {
			log.warn("Trying to create a invalid car");
			return false;
		}
		return true;
	}


	@Override
	public void delete(Long id) {
		log.info("Trying to delete a car");
		if (id == null || id < 0 || id == 0) {
			return;
		}
try {
	this.carRepository.deleteById(id);
}catch(Exception e) {
	log.error("Error trying to delete car with invalid id.");
}
		

	}


	@Override
	public void deleteAll() {
		log.info("Delete all cars");
		this.carRepository.deleteAll();
		
	}


	@Override
	public void deleteAll(List<Car> cars) {
		log.info("Delete a List of cars");
		if(CollectionUtils.isEmpty(cars)) {
			return;
		}
		
		this.carRepository.deleteAll(cars);
			
		
	}



	@Override
	public void deleteAllById(List<Long> ids) {
		log.info("Deleting car by id");
		if (CollectionUtils.isEmpty(ids)) {
			log.warn("Trying to delete an empty or null car list");
			return;
		}
		this.carRepository.deleteAllById(ids);
		
	}


	@Override
	public List<Car> findByManufacturerAndModel(String manufacturer, String model) {
		log.warn("Finding by manufacturer and model");
		if (!StringUtils.hasLength(model)) {
			log.warn("Trying to find empty or null model");
			return new ArrayList<>();
		}
		return this.carRepository.findByManufacturerAndModel(manufacturer, model);
	}


	@Override
	public List<Car> findByManufacturerOrModel(String manufacturer, String model) {
		log.warn("Finding by manufacturer or model");
		if (!StringUtils.hasLength(model)) {
			log.warn("Trying to find empty or null model");
			return new ArrayList<>();
		}
		return this.carRepository.findByManufacturerOrModel(manufacturer, model);
	}


	@Override
	public List<Car> findByNumDoorsGreaterThan(Integer doors) {
		if(doors==null || doors==0) {
			return new ArrayList<>();
		}
		return this.carRepository.findByNumDoorsGreaterThan(doors);
	}


	@Override
	public List<Car> findByModelContaining(String model) {

		return this.carRepository.findByModelContaining(model);
	}


	@Override
	public List<Car> findByYearIn(List<Integer> years) {
		return this.carRepository.findByYearIn(years);
	}


	@Override
	public List<Car> findByReleaseDateBetween(LocalDate startDate, LocalDate endDate) {
		return this.carRepository.findByReleaseDateBetween(startDate, endDate);
	}


	@Override
	public List<Car> findByUnavailableFalse() {
		return this.carRepository.findByUnavailableFalse();
	}


	@Override
	public Long deleteAllByAvailableFalse() {
		return this.carRepository.deleteAllByAvailableFalse();
	}

	
	
}
