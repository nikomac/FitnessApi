package com.nikomac.FitApi.Repositories;

import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;

public interface WorkoutRepositoryCustom {

	public Workout updateWorkout(int id, Workout w) throws WorkoutNotFoundException;

}
