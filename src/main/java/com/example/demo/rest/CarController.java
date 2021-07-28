package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Car;
import com.example.demo.repository.CarRepository;

@RestController
@RequestMapping("/api")
public class CarController {
	
	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	private CarRepository carRepo;
	
	public CarController(CarRepository carRepository) {
		this.carRepo=carRepository;
	}
	
		@GetMapping("/cars")
		public List<Car> findAll(){
			log.info("REST request to find all cars");

			return this.carRepo.findAll();
		}
		
		
		@GetMapping("/cars/{id}")
		public ResponseEntity<Car> findOne(@PathVariable Long id){
			log.info("REST request to find one car");
			Optional<Car> carOpt=this.carRepo.findById(id);

			if(carOpt.isPresent()) {
				
				return ResponseEntity.ok(carOpt.get());
			}
			
			return ResponseEntity.notFound().build();
	
		}
		
		@PostMapping("/cars")
		public ResponseEntity<Car> create(@RequestBody Car car){
			log.info("REST request to create a new car");
			if(car.getId()!=null) {
				log.warn("Se ha intentado crear con una id");
				return ResponseEntity.badRequest().build();
			}

			car= this.carRepo.save(car);
			return ResponseEntity.ok(car);
			
		}
		
		
		@PutMapping("/cars")
		public ResponseEntity<Car> edit(@RequestBody Car car){
			log.info("REST request to edit a car");
			if(car.getId()==null) {
				log.warn("Se ha intentado editar sin una id");
				return ResponseEntity.badRequest().build();
			}
			
			car= this.carRepo.save(car);
			return ResponseEntity.ok(car);
			
		}
		
		
		@DeleteMapping("/cars/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			log.info("REST request to delete a car");
			
			this.carRepo.deleteById(id);

			return ResponseEntity.noContent().build();
			
		}
		
		
		@DeleteMapping("/cars")
		public ResponseEntity<Void> deleteAll(){
			log.info("REST request to delete all cars 2");
			

			this.carRepo.deleteAll();
			return ResponseEntity.noContent().build();
			
		}
		
		
		@GetMapping("/cars/doors/{numDoors}")
		public List<Car> findByDoors(@PathVariable Integer numDoors){
			log.info("REST request to find cars by doors");
			
			return this.carRepo.findByNumDoors(numDoors);
		}
}

