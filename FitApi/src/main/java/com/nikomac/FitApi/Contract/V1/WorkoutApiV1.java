package com.nikomac.FitApi.Contract.V1;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Workout")
public class WorkoutApiV1 {	
	public int id;
	public String name;
	public String description;
}
