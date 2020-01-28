package com.nikomac.FitApi.Exceptions;

public class WorkoutNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public WorkoutNotFoundException(long workoutId) {
		super(String.format("Workout not found with id : '%s'", workoutId));
	}
}