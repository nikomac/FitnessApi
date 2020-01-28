package com.nikomac.FitApi.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "workouts")
public class Workout {
	
	@Id
	@GeneratedValue
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String trainer;

	private String description;

	public Workout() {
		super();
	}

	public Workout(int id, String name, String trainer, String description) {
		super();
		this.id = id;
		this.name = name;
		this.trainer = trainer;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
