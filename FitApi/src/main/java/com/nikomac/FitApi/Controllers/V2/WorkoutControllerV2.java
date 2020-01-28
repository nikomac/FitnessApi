package com.nikomac.FitApi.Controllers.V2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nikomac.FitApi.Contract.V2.WorkoutApiV2;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;
import com.nikomac.FitApi.Repositories.WorkoutRepository;
import com.nikomac.FitApi.Translators.V2.WorkoutTranslatorV2;

import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/workouts")
public class WorkoutControllerV2 {

	@Autowired
	WorkoutRepository workoutRepository;

	@GetMapping("")
	public List<WorkoutApiV2> getAll() {
		return workoutRepository.findAll().stream().map(x-> WorkoutTranslatorV2.Translate(x)).collect(Collectors.toList());
	}

	@PostMapping("")
	public WorkoutApiV2 create(@Valid @RequestBody WorkoutApiV2 workout) {
		
		Workout result = workoutRepository.save(WorkoutTranslatorV2.Translate(workout));
		return WorkoutTranslatorV2.Translate(result);
	}

	@GetMapping("/{id}")
	public WorkoutApiV2 getById(@PathVariable(value = "id") int wId) 
			throws WorkoutNotFoundException {
		
		Workout result = workoutRepository.findById(wId).orElseThrow(() -> new WorkoutNotFoundException(wId));
		return WorkoutTranslatorV2.Translate(result);
	}

	@PutMapping("/{id}")
	public WorkoutApiV2 update(@PathVariable(value = "id") int wId, @Valid @RequestBody WorkoutApiV2 workoutDetails)
			throws WorkoutNotFoundException {

		Workout result = workoutRepository.updateWorkout(wId, WorkoutTranslatorV2.Translate(workoutDetails));
		
		return WorkoutTranslatorV2.Translate(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int wId) 
			throws WorkoutNotFoundException {

		Workout workout = workoutRepository.findById(wId).orElseThrow(() -> new WorkoutNotFoundException(wId));
		workoutRepository.delete(workout);

		return ResponseEntity.ok().build();
	}
}