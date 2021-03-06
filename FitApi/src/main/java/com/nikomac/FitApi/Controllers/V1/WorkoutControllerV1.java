package com.nikomac.FitApi.Controllers.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nikomac.FitApi.Biz.WorkoutService;
import com.nikomac.FitApi.Contract.V1.WorkoutApiV1;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;
import com.nikomac.FitApi.Translators.V1.WorkoutTranslatorV1;

import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Version 1 de los controller 
 */

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutControllerV1 {

	@Autowired
	WorkoutService workoutService;

	@GetMapping("")
	public List<WorkoutApiV1> getAll() {

		List<Workout> workouts = workoutService.retrieveWorkouts();
		return workouts.stream().map(x -> WorkoutTranslatorV1.Translate(x)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public WorkoutApiV1 getById(@PathVariable(value = "id") int wId) throws WorkoutNotFoundException {

		Workout result = workoutService.getWorkout(wId);
		return WorkoutTranslatorV1.Translate(result);
	}

	@PostMapping("")
	public WorkoutApiV1 create(@Valid @RequestBody WorkoutApiV1 workout) {

		Workout toSave = WorkoutTranslatorV1.Translate(workout);
		Workout result = workoutService.createWorkout(toSave);
		return WorkoutTranslatorV1.Translate(result);
	}

	@PutMapping("/{id}")
	public WorkoutApiV1 update(@PathVariable(value = "id") int wId, @Valid @RequestBody WorkoutApiV1 workoutDetails)
			throws WorkoutNotFoundException {

		Workout updateData = WorkoutTranslatorV1.Translate(workoutDetails); 
		Workout result = workoutService.updateWorkout(wId, updateData);

		return WorkoutTranslatorV1.Translate(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int wId) throws WorkoutNotFoundException {

		workoutService.deleteWorkout(wId);

		return ResponseEntity.ok().build();
	}
}