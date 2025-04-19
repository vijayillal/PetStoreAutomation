package api.endpoints;

// Created user endpoint.java 
//to perform CRUD Method requests---> Create, Retrieve(Read), Update and Delete API Requests

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndpoint2 {

	// this method is created to get the URL from routes.properties file i.e 2nd approach
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //Load properties file
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url=getURL().getString("post_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(post_url);
			
			return res;	
	}
	
	public static Response getUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		
		Response res=given()
			.pathParam("username", userName)
		
		.when()
			.get(get_url);
		
		return res;
		
	}
	
	public static Response updateUser(String userName, User payload)
	{
		String update_url=getURL().getString("update_url");
		
		Response res=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		
		.when()
			.put(update_url);
		
		return res;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");
		
		Response res=given()
			.pathParam("username", userName)
		
		.when()
			.delete(delete_url);
		
		return res;
	}
	
}
