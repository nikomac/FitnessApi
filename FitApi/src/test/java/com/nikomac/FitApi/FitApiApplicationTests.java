package com.nikomac.FitApi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.nikomac.FitApi.Contract.V2.WorkoutApiV2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FitApiApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getUrl() {
		return "http://localhost:" + port + "/api/v2/workouts/";
	}

	@Test
	public void contextLoads() {

	}
	
	@Test
	public void CRUD_Test() {
		testGetAll();
		int createdId = testCreate();
		testGetById(createdId);
		testUpdate(createdId);
		testDelete(createdId);
	}
	
	public void testGetAll() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.GET, entity, String.class);

		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
	}

	public int testCreate() {
		WorkoutApiV2 workout = new WorkoutApiV2();
		workout.name = "Monday Leg Day";
		workout.trainer = "Guillem";
		workout.description = "Squat until you die!";

		ResponseEntity<WorkoutApiV2> postResponse = restTemplate.postForEntity(getUrl(), workout, WorkoutApiV2.class);
		Assert.assertTrue(postResponse.getStatusCode() == HttpStatus.OK);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
		Assert.assertTrue(postResponse.getBody().id > 0);

		return postResponse.getBody().id;
	}

	public void testGetById(int id) {
		WorkoutApiV2 workout = restTemplate.getForObject(getUrl() + id, WorkoutApiV2.class);
		Assert.assertNotNull(workout);
	}

	public void testUpdate(int id) {
		WorkoutApiV2 workout = restTemplate.getForObject(getUrl() + id, WorkoutApiV2.class);
		Assert.assertNotNull(workout);
		workout.trainer = "Manolo";

		restTemplate.put(getUrl() + id, workout);

		WorkoutApiV2 updatedWorkout = restTemplate.getForObject(getUrl() + id, WorkoutApiV2.class);
		Assert.assertNotNull(updatedWorkout);
		Assert.assertEquals(workout.trainer, updatedWorkout.trainer);
	}

	public void testDelete(int id) {
		restTemplate.delete(getUrl() + id);

		try {
			restTemplate.getForObject(getUrl() + id, WorkoutApiV2.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}

}
