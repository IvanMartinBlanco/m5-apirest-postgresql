package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import com.example.demo.dto.CarListDTO;
import com.example.demo.dto.CountDTO;
import com.example.demo.repository.CarRepository;
import com.example.demo.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
public class CarController {
	
	private final Logger log = LoggerFactory.getLogger(CarController.class);
	
	private CarService carServ;
	
	public CarController(CarService carServ) {
		this.carServ=carServ;
	}
	
		@GetMapping("/cars")
		public List<Car> findAll(){
			log.info("REST request to find all cars");

			return this.carServ.findAll();
		}
		
		
		@GetMapping("/cars/{id}")
		public ResponseEntity<Car> findById(@ApiParam("Clave primaria car") @PathVariable Long id){
			log.info("REST request to find one car");
			Optional<Car> carOpt=this.carServ.findById(id);

			if(carOpt.isPresent()) {
				
				return ResponseEntity.ok(carOpt.get());
			}
			
			return ResponseEntity.notFound().build();
			
			
			//return carOpt.map(car -> ResponseEntity.ok(car)).orElseGet(()-> new ResponseEntity<>(HttpStatus.ACCEPTED));
	
		}
		
		@PostMapping("/cars")
		public ResponseEntity<Car> create(@RequestBody Car car){
			log.info("REST request to create a new car");
			if(car.getId()!=null) {
				log.warn("Se ha intentado crear con una id");
				return ResponseEntity.badRequest().build();
			}

			car= this.carServ.save(car);
			return ResponseEntity.ok(car);
			
		}
		
		
		@PutMapping("/cars")
		public ResponseEntity<Car> update(@RequestBody Car car){
			log.info("REST request to edit a car");
			if(car.getId()==null) {
				log.warn("Se ha intentado editar sin una id");
				return ResponseEntity.badRequest().build();
			}
			
			return ResponseEntity.ok(this.carServ.save(car));
			
		}

		
		
		@DeleteMapping("/cars/{id}")
		public ResponseEntity<Void> delete(@PathVariable Long id){
			log.info("REST request to delete a car");
			
			this.carServ.delete(id);

			return ResponseEntity.noContent().build();
			
		}
		
		
		@DeleteMapping("/cars")
		public ResponseEntity<Void> deleteAll(){
			log.info("REST request to delete all cars 2");
			

			this.carServ.deleteAll();
			return ResponseEntity.noContent().build();
			
		}
		
		@PostMapping("/cars/deletemany")
		public ResponseEntity<Car> deleteMany(@RequestBody CarListDTO carListDTO){
			log.info("REST request to delete some cars");
			
			this.carServ.deleteAll(carListDTO.getCars());
			
			return ResponseEntity.noContent().build();
			
		}
		
		@GetMapping("/cars/deletemany/ids={ids}")
		public ResponseEntity<Car> deleteMany(@PathVariable List<Long> ids){
			log.info("REST request to delete all cars 2");
			

			//this.carServ.deleteAllById(ids);
			return ResponseEntity.noContent().build();
			
		}
		
		@GetMapping("/cars/count")
		public ResponseEntity<CountDTO> count(){
			log.info("REST request to count all cars");
			
			return ResponseEntity.ok(new CountDTO(this.carServ.count()));
		}
		
		
		@GetMapping("/cars/doors/{numDoors}")
		//@ApiIgnore //No aparecer en la documentación.
		@ApiOperation("Buscar coches por número de puertas") //Cambiar la documentación.
		public List<Car> findByDoors(@PathVariable Integer numDoors){
			log.info("REST request to find cars by doors");
			
			return this.carServ.findByDoors(numDoors);
		}
		
		@GetMapping("/cars/manufacturer/{manufacturer}/model/{model}")
		public List<Car> findByManufacturerAndModel(@PathVariable String manufacturer, @PathVariable String model){
			
			return this.carServ.findByManufacturerAndModel(manufacturer, model);
		}
		
		
		@GetMapping("/cars/doors-gte/{numDoors}")
		//@ApiIgnore //No aparecer en la documentación.
		@ApiOperation("Buscar coches por número de puertas") //Cambiar la documentación.
		public List<Car> findByDoorsGreaterThan(@PathVariable Integer numDoors){
			log.info("REST request to find cars by doors");
			
			return this.carServ.findByNumDoorsGreaterThan(numDoors);
		}

}
