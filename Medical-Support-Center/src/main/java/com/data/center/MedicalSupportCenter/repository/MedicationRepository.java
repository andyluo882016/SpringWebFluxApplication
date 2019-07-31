package com.data.center.MedicalSupportCenter.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.data.center.MedicalSupportCenter.domain.Medication;

import reactor.core.publisher.Mono;

@Repository
public interface MedicationRepository extends ReactiveMongoRepository<Medication, String>{

	//public Mono<Medication> updateMedication(Medication medication);
}
