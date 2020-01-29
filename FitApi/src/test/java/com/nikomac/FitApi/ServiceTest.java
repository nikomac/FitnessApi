package com.nikomac.FitApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.nikomac.FitApi.Biz.WorkoutService;
import com.nikomac.FitApi.Dal.WorkoutRepository;
import com.nikomac.FitApi.Exceptions.WorkoutNotFoundException;
import com.nikomac.FitApi.Models.Workout;

/*
 * Test de los Servicios de la BIZ donde mockeamos la DAL en este caso el WorckoutRepository
 */

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

	@Mock
	WorkoutRepository data;

	@InjectMocks
	WorkoutService service;

	private int validId = 1;
	private int newId = 4;
	private int notFoundId = 10;

	private boolean setUpIsDone = false;

	private Workout firstW = new Workout(1, "Monday Legs", "Guillem", "Squat until you die!");
	private Workout lastW = new Workout(3, "Friday Chest", "Guillem", "Just 200 push ups");

	private Workout updateWorkout = new Workout(0, "Saturday Whole Body", "Guillem", "Muscle ups time!");
	private Workout updatedWorkout = new Workout(validId, updateWorkout.getName(), updateWorkout.getTrainer(),
			updateWorkout.getDescription());

	private Workout createWorkout = new Workout(0, "Sunday Rest", "Guillem", "And the last day we shall rest!");
	private Workout createdWorkout = new Workout(newId, createWorkout.getName(), createWorkout.getTrainer(),
			createWorkout.getDescription());

	private Workout deleteWorkout = new Workout(2, "Wednesday Back", "Guillem", "Pull up you piece of ####");

	@Before
	public void before() throws WorkoutNotFoundException {

		if (!setUpIsDone) {

			List<Workout> list = new ArrayList<Workout>();
			list.add(firstW);
			list.add(deleteWorkout);
			list.add(lastW);

			Mockito.when(data.findAll()).thenReturn(list);

			Mockito.when(data.findById(validId)).thenReturn(list.stream().filter(x-> x.getId() == validId).findFirst());
			Mockito.when(data.findById(notFoundId)).thenReturn(Optional.empty());

			Mockito.when(data.updateWorkout(validId, updateWorkout)).thenReturn(updatedWorkout);
			Mockito.when(data.updateWorkout(notFoundId, updateWorkout)).thenThrow(new WorkoutNotFoundException(notFoundId));

			Mockito.when(data.save(createWorkout)).thenReturn(createdWorkout);

			//Mockito.doNothing().when(data).delete(deleteWorkout);

			setUpIsDone = true;
		}
	}

	@Test
	public void serviceRetrieveWorkouts() {

		Assert.assertEquals(3, service.retrieveWorkouts().size());
	}

	@Test
	public void serviceGetByWorkoutOk() throws WorkoutNotFoundException {
		Assert.assertEquals(validId, service.getWorkout(validId).getId());
	}

	@Test
	public void serviceGetByWorkoutFail() {

		try {
			service.getWorkout(notFoundId);
		} catch (WorkoutNotFoundException e) {
			Assert.assertEquals(notFoundId, e.getWorkoutId());
		}

	}

	@Test
	public void serviceUpdateWorkoutOk() throws WorkoutNotFoundException {
		
		Workout wo = service.updateWorkout(validId, updateWorkout);
		Assert.assertEquals(updateWorkout.getName(), wo.getName());
	}
	
	@Test
	public void serviceUpdateWorkoutFail() {
		
		try {
			service.updateWorkout(notFoundId, updateWorkout);
		} catch (WorkoutNotFoundException e) {
			Assert.assertEquals(notFoundId, e.getWorkoutId());
		}		
	}

	@Test
	public void setviceCreateWorkouts() {
		Workout wo = service.createWorkout(createWorkout);
		Assert.assertEquals(newId, wo.getId());
		Assert.assertEquals(createWorkout.getName(), wo.getName());

	}

	@Test
	public void setviceDeleteWorkoutsOk() throws WorkoutNotFoundException {

		service.deleteWorkout(validId);
	}
	
	@Test
	public void setviceDeleteWorkoutsFail() {
		
		try {
			service.deleteWorkout(notFoundId);
		} catch (WorkoutNotFoundException e) {
			Assert.assertEquals(e.getWorkoutId(), notFoundId);
		}		
	}

}
