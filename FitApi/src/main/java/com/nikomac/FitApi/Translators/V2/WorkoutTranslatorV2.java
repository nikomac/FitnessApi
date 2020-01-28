package com.nikomac.FitApi.Translators.V2;

import com.nikomac.FitApi.Contract.V2.WorkoutApiV2;
import com.nikomac.FitApi.Models.Workout;

public class WorkoutTranslatorV2 {

	public static WorkoutApiV2 Translate(Workout internal) {

		WorkoutApiV2 external = new WorkoutApiV2();
		external.id = internal.getId();
		external.name = internal.getName();
		external.trainer = internal.getTrainer();
		external.description = internal.getDescription();

		return external;
	}

	public static Workout Translate(WorkoutApiV2 external) {

		Workout internal = new Workout();
		internal.setId(external.id);
		internal.setName(external.name);
		internal.setTrainer(external.trainer);
		internal.setDescription(external.description);

		return internal;
	}
}
