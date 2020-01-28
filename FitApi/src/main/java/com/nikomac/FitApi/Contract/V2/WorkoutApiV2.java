package com.nikomac.FitApi.Contract.V2;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Workout")
public class WorkoutApiV2 {
	public int id;
	public String name;
	public String trainer;
	public String description;
}
