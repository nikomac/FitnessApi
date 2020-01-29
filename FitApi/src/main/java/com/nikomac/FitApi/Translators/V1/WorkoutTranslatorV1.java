package com.nikomac.FitApi.Translators.V1;

import com.nikomac.FitApi.Contract.V1.WorkoutApiV1;
import com.nikomac.FitApi.Models.Workout;

/*
 * Version 1 de los traductores 
 */

public class WorkoutTranslatorV1 {

	public static WorkoutApiV1 Translate(Workout internal) {

		WorkoutApiV1 external = new WorkoutApiV1();
		external.id = internal.getId();
		external.name = internal.getName() + " - " + internal.getTrainer();
		external.description = internal.getDescription();

		return external;
	}

	public static Workout Translate(WorkoutApiV1 external) {

		Workout internal = new Workout();
		internal.setId(external.id);
		internal.setName(external.name);
		internal.setTrainer("Default");
		internal.setDescription(external.description);

		return internal;
	}
}
