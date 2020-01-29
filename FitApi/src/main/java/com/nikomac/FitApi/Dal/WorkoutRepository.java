package com.nikomac.FitApi.Dal;

/*
 * Repositorio que nos permite acceder a la base de datos extendemos tambien un repositorio Custom donde hemos implementado 
 * un metodo adicional de acceso a la base de datos 
 */

public interface WorkoutRepository extends WorkoutRepositoryBasic, WorkoutRepositoryCustom {
}
