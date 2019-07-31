package com.data.center.MedicalSupportCenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.center.MedicalSupportCenter.repository.MedicationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import java.util.stream.Stream;

import javax.validation.Valid;

import java.util.Optional;

import java.time.Duration;
import java.util.Date;

//import org.apache.el.stream.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.data.center.MedicalSupportCenter.domain.*;

@RestController
@RequestMapping("/order")
public class DataSourceController {

	private MedicationRepository medicationRepository;

	public DataSourceController(MedicationRepository medicationRepository) {
		
		this.medicationRepository = medicationRepository;
	}
	
	@GetMapping(path="/getAllMedication")
	public Flux<Medication> getAlls() {
		
		return medicationRepository.findAll();
	}
	
	@GetMapping("/getMedication/{id}")
    public Mono<Medication> getOneMedication(@PathVariable("id") final String id) {
		 Optional<Medication> pt= medicationRepository.findById(id).blockOptional();
		  System.out.println("----->  "+pt.get());
   	 return medicationRepository.findById(id);
    }
	
	@GetMapping(path="/{id}/medication/event", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<MedicalEvents> showOrder(@PathVariable("id") final String id) {
		
		return medicationRepository.findById(id).flatMapMany(medication ->{
			 Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
			 
			 Flux<MedicalEvents> buyProduct= Flux.fromStream(
					 Stream.generate(() -> new MedicalEvents(medication, new Date()))
				
					 );
			 
			 return  Flux.zip(interval, buyProduct)
					   .map(Tuple2::getT2);
			 
		});
				
	}
	
	@PostMapping(path="/save")
	public Mono<Medication> saveOne(@Valid @RequestBody Medication medication) {
		
		return medicationRepository.save(medication);
		
		//return Mono<Void>;
	}
	
	@PutMapping("/update")
	private Mono<Medication> updateMedication(@Valid @RequestBody Medication med) {
	    return medicationRepository.save(med);
	}
}
