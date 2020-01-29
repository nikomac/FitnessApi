package com.nikomac.FitApi.Repositories;

import org.springframework.beans.factory.annotation.Autowired;

import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;

public class WorkoutRepositoryCustomImpl implements WorkoutRepositoryCustom {

//	private final WorkoutRepositoryBasic workoutRepository;
//
//	public WorkoutRepositoryCustomImpl(WorkoutRepositoryBasic workoutRepository) {
//		this.workoutRepository = workoutRepository;
//	}
	
	@Autowired
	WorkoutRepositoryBasic workoutRepository;

	@Override
	public Workout updateWorkout(int id, Workout w) throws WorkoutNotFoundException {
		Workout workout = workoutRepository.findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));

		workout.setName(w.getName());
		workout.setTrainer(w.getTrainer());
		workout.setDescription(w.getDescription());

		Workout updatedWorkout = workoutRepository.save(workout);
		return updatedWorkout;
	}
}
