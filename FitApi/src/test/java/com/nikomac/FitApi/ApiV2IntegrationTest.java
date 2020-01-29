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

/*
 * Test de integracion donde levantamos la API y hacemos un testeo CRUD completo 
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiV2IntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getUrl() {
		return "http://localhost:" + port + "/api/v2/workouts/";
	}
	
	private HttpHeaders getHeader(){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("API_KEY", "testkey");
		return headers;
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
		HttpEntity<String> entity = new HttpEntity<String>(null, getHeader());

		ResponseEntity<String> response = restTemplate.exchange(getUrl(), HttpMethod.GET, entity, String.class);

		Assert.assertTrue(response.getStatusCode() == HttpStatus.OK);
		Assert.assertNotNull(response.getBody());
	}

	public int testCreate() {
		WorkoutApiV2 workout = new WorkoutApiV2();
		workout.name = "Monday Leg Day";
		workout.trainer = "Guillem";
		workout.description = "Squat until you die!";
		
		HttpEntity<WorkoutApiV2> entity = new HttpEntity<WorkoutApiV2>(workout, getHeader());
		ResponseEntity<WorkoutApiV2> postResponse = restTemplate.exchange(getUrl(), HttpMethod.POST, entity, WorkoutApiV2.class);

		Assert.assertTrue(postResponse.getStatusCode() == HttpStatus.OK);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
		Assert.assertTrue(postResponse.getBody().id > 0);

		return postResponse.getBody().id;
	}

	public void testGetById(int id) {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, getHeader());

		ResponseEntity<WorkoutApiV2> response = restTemplate.exchange(getUrl() + id, HttpMethod.GET, entity, WorkoutApiV2.class);
		Assert.assertEquals(id, response.getBody().id);
	}

	public void testUpdate(int id) {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, getHeader());
		ResponseEntity<WorkoutApiV2> response = restTemplate.exchange(getUrl() + id, HttpMethod.GET, entity, WorkoutApiV2.class);
		
		WorkoutApiV2 workout = response.getBody();
		Assert.assertEquals(id, response.getBody().id);
		
		workout.trainer = "Manolo";

		HttpEntity<WorkoutApiV2> updateEntity = new HttpEntity<WorkoutApiV2>(workout, getHeader());
		ResponseEntity<WorkoutApiV2> updateResponse = restTemplate.exchange(getUrl() + id, HttpMethod.PUT, updateEntity, WorkoutApiV2.class);

		WorkoutApiV2 updatedWorkout = updateResponse.getBody();
		Assert.assertNotNull(updatedWorkout);
		Assert.assertEquals(workout.trainer, updatedWorkout.trainer);
	}

	public void testDelete(int id) {
		
		HttpEntity<String> entity = new HttpEntity<String>(null, getHeader());
		restTemplate.exchange(getUrl() + id, HttpMethod.DELETE, entity, String.class);

		try {
			restTemplate.exchange(getUrl() + id, HttpMethod.GET, entity, WorkoutApiV2.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

}
