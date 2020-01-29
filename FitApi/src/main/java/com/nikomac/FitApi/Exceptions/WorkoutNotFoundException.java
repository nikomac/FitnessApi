package com.nikomac.FitApi.Exceptions;

public class WorkoutNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	private int workoutId;

	public int getWorkoutId() {
		return workoutId;
	}

	public WorkoutNotFoundException(int workoutId) {
		super(String.format("Workout not found with id : '%s'", workoutId));
		this.workoutId = workoutId;
	}
}