package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloadpojo.User;
import api.utilities.TestDataProvider;
import io.restassured.response.Response;

public class UserDataTest {
	// In this class we are testing using data provider which is fed by xlsx sheet
	@Test(priority=1, dataProvider = "AllUserData", dataProviderClass = TestDataProvider.class)
	public void testPostUser(String userId, String userName, String fname, String lname, 
			String useremail, String password, String phone){
		User userPayload = new User();
		Faker faker = new Faker();
//		userPayload.setId(Integer.parseInt(userId));
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		 
		Response response = UserEndPoints.createUser(userPayload);
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = TestDataProvider.class)
	public void testDeleteUserByName(String username) {
		Response response = UserEndPoints.deleteUser(username);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
