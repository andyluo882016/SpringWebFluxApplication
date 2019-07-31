package com.data.center.MedicalSupportCenter.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MedicalEvents {

	private Medication medication;
	
	private Date date;

	public Medication getMedication() {
		return medication;
	}

	public void setMedication(Medication medication) {
		this.medication = medication;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public MedicalEvents(Medication medication, Date date) {
		
		this.medication = medication;
		this.date = date;
	}

	public MedicalEvents() {
		
	}
	
	
}
