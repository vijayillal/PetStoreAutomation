package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class) //if data provider is in another class
	// if dataprovider is in same class then .class is not required
	public void testPostUser(String userid,String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(userName);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(useremail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response res=userEndpoint.createUser(userpayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void testDeleteUser(String userName)
	{
		Response res=userEndpoint.deleteUser(userName);
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	
	
}
