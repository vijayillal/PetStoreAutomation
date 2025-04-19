package api.endpoints;

// Created user endpoint.java 
//to perform CRUD Method requests---> Create, Retrieve(Read), Update and Delete API Requests

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoint {

	public static Response createUser(User payload)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(Routes.post_url);
			
			return res;	
	}
	
	public static Response getUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
		
		.when()
			.get(Routes.get_url);
		
		return res;
		
	}
	
	public static Response updateUser(String userName, User payload)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		
		.when()
			.put(Routes.update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		Response res=given()
			.pathParam("username", userName)
		
		.when()
			.delete(Routes.delete_url);
		
		return res;
	}
	
}
