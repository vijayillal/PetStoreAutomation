package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndpoint;
import api.payload.User;

import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User Userpayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		Userpayload=new User();
		
		Userpayload.setId(faker.idNumber().hashCode());
		Userpayload.setUsername(faker.name().username());
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Userpayload.setEmail(faker.internet().safeEmailAddress());
		Userpayload.setPassword(faker.internet().password(5, 10));
		Userpayload.setPhone(faker.phoneNumber().cellPhone());
		
		// Adding loggers into the project
		
		logger=LogManager.getLogger(this.getClass());
		
		
	}
	
	@Test(priority=1)
	public void testCreateUser()
	{
		logger.info("****** Creating the User **********");
		
		Response res=userEndpoint.createUser(Userpayload);
		
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("****** User Created Successfully **********");
		
		
	}
	
	@Test(priority=2)
	public void testgetUser()
	{
		logger.info("****** Reading User information **********");
		
		Response res=userEndpoint.getUser(this.Userpayload.getUsername());
		res.then().log().all();
		//res.statusCode();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("****** Reading User information Displayed **********");
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("****** Updating User information **********");
		
		Userpayload.setFirstName(faker.name().firstName());
		Userpayload.setLastName(faker.name().lastName());
		Userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res=userEndpoint.updateUser(this.Userpayload.getUsername(), Userpayload);
		/*res.then().log().all();  // normal validation
		res.statusCode();          // normal validation
		*/
		
		//res.then().log().body().statusCode(200);  // Chai Assertion
		
		
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);  // test NG Assertion
		
		// response after update 
		
		Response resAfterUpdate=userEndpoint.getUser(this.Userpayload.getUsername());
		resAfterUpdate.then().log().all();
		
		
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
		
		logger.info("****** User information Updated **********");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		
		logger.info("****** Deleting User information **********");
		
		Response res=userEndpoint.deleteUser(this.Userpayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("****** User information Deleted **********");
	}
	
	
	
	
	
	
}
