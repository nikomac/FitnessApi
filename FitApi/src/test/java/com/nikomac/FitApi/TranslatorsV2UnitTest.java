package com.nikomac.FitApi;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.nikomac.FitApi.Contract.V2.WorkoutApiV2;
import com.nikomac.FitApi.Models.Workout;
import com.nikomac.FitApi.Translators.V2.WorkoutTranslatorV2;

/*
 * Test unitario de los traductores que usamos en los Controller
 */

@SpringBootTest
public class TranslatorsV2UnitTest {

	private static Workout core = new Workout();

	private static WorkoutApiV2 api = new WorkoutApiV2();
	
	@BeforeClass
	public static void before() {
		int id = 1;
		String name = "Monday Legs";
		String trainer = "Guillem";
		String description = "Squat until you die!";
		
		core.setId(id);
		core.setDescription(description);
		core.setName(name);
		core.setTrainer(trainer);
		
		api.id = id;
		api.description = description;
		api.name = name;
		api.trainer = trainer;		
	}

	@Test
	public void apiToCore() {

		Workout coreTrans = WorkoutTranslatorV2.Translate(api);
		Assert.assertEquals(api.name, coreTrans.getName());
		Assert.assertEquals(api.description, coreTrans.getDescription());
		Assert.assertEquals(api.trainer, coreTrans.getTrainer());
		Assert.assertEquals(api.id, coreTrans.getId());
	}

	
	@Test
	public void coreToApi() {

		WorkoutApiV2 apiTrans = WorkoutTranslatorV2.Translate(core);
		Assert.assertEquals(core.getName(), apiTrans.name);
		Assert.assertEquals(core.getDescription(), apiTrans.description);
		Assert.assertEquals(core.getTrainer(), apiTrans.trainer);
		Assert.assertEquals(core.getId(), apiTrans.id);
	}
	

}
