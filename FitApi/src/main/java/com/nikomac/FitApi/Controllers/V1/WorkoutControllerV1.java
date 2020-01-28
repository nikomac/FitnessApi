package com.nikomac.FitApi.Controllers.V1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nikomac.FitApi.Contract.V1.WorkoutApiV1;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;
import com.nikomac.FitApi.Repositories.WorkoutRepository;
import com.nikomac.FitApi.Translators.V1.WorkoutTranslatorV1;

import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/workouts")
public class WorkoutControllerV1 {

	@Autowired
	WorkoutRepository workoutRepository;

	@GetMapping("")
	public List<WorkoutApiV1> getAll() {
		return workoutRepository.findAll().stream().map(x-> WorkoutTranslatorV1.Translate(x)).collect(Collectors.toList());
	}

	@PostMapping("")
	public WorkoutApiV1 create(@Valid @RequestBody WorkoutApiV1 workout) {
		
		Workout result = workoutRepository.save(WorkoutTranslatorV1.Translate(workout));
		return WorkoutTranslatorV1.Translate(result);
	}

	@GetMapping("/{id}")
	public WorkoutApiV1 getById(@PathVariable(value = "id") int wId) 
			throws WorkoutNotFoundException {
		
		Workout result = workoutRepository.findById(wId).orElseThrow(() -> new WorkoutNotFoundException(wId));
		return WorkoutTranslatorV1.Translate(result);
	}

	@PutMapping("/{id}")
	public WorkoutApiV1 update(@PathVariable(value = "id") int wId, @Valid @RequestBody WorkoutApiV1 workoutDetails)
			throws WorkoutNotFoundException {

		Workout result = workoutRepository.updateWorkout(wId, WorkoutTranslatorV1.Translate(workoutDetails));
		
		return WorkoutTranslatorV1.Translate(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int wId) 
			throws WorkoutNotFoundException {

		Workout workout = workoutRepository.findById(wId).orElseThrow(() -> new WorkoutNotFoundException(wId));
		workoutRepository.delete(workout);

		return ResponseEntity.ok().build();
	}
}