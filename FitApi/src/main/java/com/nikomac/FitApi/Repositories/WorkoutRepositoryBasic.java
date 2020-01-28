package com.nikomac.FitApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikomac.FitApi.Models.Workout;

public interface WorkoutRepositoryBasic extends JpaRepository<Workout, Integer> {

}
