package api.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPointWithPropertiesFile;
import api.endpoints.UserEndPoints;
import api.payloadpojo.User;
import io.restassured.response.Response;

public class UserPropertiesTest {
	// In this class we are testing using urls from properties files
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		Response response = UserEndPointWithPropertiesFile.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetUserByName(){
		Response response = UserEndPointWithPropertiesFile.getUser(this.userPayload.getUsername()); 
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		// updating data
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		Response response = UserEndPointWithPropertiesFile.updateUser(userPayload, this.userPayload.getUsername( ));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// check that data is updated
		Response responseAfterUpdate = UserEndPointWithPropertiesFile.getUser(this.userPayload.getUsername()); 
		response.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testDeleteUserByName() {
		Response response = UserEndPointWithPropertiesFile.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
