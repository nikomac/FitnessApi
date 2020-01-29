package com.nikomac.FitApi.Dal;

import org.springframework.beans.factory.annotation.Autowired;

import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;

public class WorkoutRepositoryCustomImpl implements WorkoutRepositoryCustom {
	
	@Autowired
	WorkoutRepositoryBasic workoutRepository;

	@Override
	public Workout updateWorkout(int id, Workout workoutData) throws WorkoutNotFoundException {
		Workout workout = workoutRepository.findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));

		workout.setName(workoutData.getName());
		workout.setTrainer(workoutData.getTrainer());
		workout.setDescription(workoutData.getDescription());

		Workout updatedWorkout = workoutRepository.save(workout);
		return updatedWorkout;
	}
}
