package com.nikomac.FitApi.Biz;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nikomac.FitApi.Dal.WorkoutRepository;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;

@Component
public class WorkoutService {

	@Autowired
	WorkoutRepository workoutRepository;

	public List<Workout> retrieveWorkouts() {
		return workoutRepository.findAll();
	}
	
	public Workout getWorkout(int id) throws WorkoutNotFoundException {
		Workout result = workoutRepository.findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));	
		return result;
	}

	public Workout createWorkout(Workout workout) {
		Workout result = workoutRepository.save(workout);
		return result;
	}

	public Workout updateWorkout(int id, Workout workout) throws WorkoutNotFoundException {
		Workout result = workoutRepository.updateWorkout(id, workout);
		return result;
	}
	
	public void deleteWorkout(int id) throws WorkoutNotFoundException {		
		Workout workout = workoutRepository.findById(id).orElseThrow(() -> new WorkoutNotFoundException(id));
		workoutRepository.delete(workout);
	}

}
