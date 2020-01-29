package com.nikomac.FitApi.Controllers.V2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nikomac.FitApi.Biz.WorkoutService;
import com.nikomac.FitApi.Contract.V2.WorkoutApiV2;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;
import com.nikomac.FitApi.Translators.V2.WorkoutTranslatorV2;

import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Version 2 de los controller 
 */

@RestController
@RequestMapping("/api/v2/workouts")
public class WorkoutControllerV2 {

	@Autowired
	WorkoutService workoutService;

	@GetMapping("")
	public List<WorkoutApiV2> getAll() {

		List<Workout> workouts = workoutService.retrieveWorkouts();
		return workouts.stream().map(x -> WorkoutTranslatorV2.Translate(x)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public WorkoutApiV2 getById(@PathVariable(value = "id") int wId) throws WorkoutNotFoundException {

		Workout result = workoutService.getWorkout(wId);
		return WorkoutTranslatorV2.Translate(result);
	}

	@PostMapping("")
	public WorkoutApiV2 create(@Valid @RequestBody WorkoutApiV2 workout) {

		Workout toSave = WorkoutTranslatorV2.Translate(workout);
		Workout result = workoutService.createWorkout(toSave);
		return WorkoutTranslatorV2.Translate(result);
	}

	@PutMapping("/{id}")
	public WorkoutApiV2 update(@PathVariable(value = "id") int wId, @Valid @RequestBody WorkoutApiV2 workoutDetails)
			throws WorkoutNotFoundException {

		Workout updateData = WorkoutTranslatorV2.Translate(workoutDetails); 
		Workout result = workoutService.updateWorkout(wId, updateData);

		return WorkoutTranslatorV2.Translate(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int wId) throws WorkoutNotFoundException {

		workoutService.deleteWorkout(wId);

		return ResponseEntity.ok().build();
	}
}