package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payloadpojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//Used to perform CRUD operation on users

public class UserEndPoints {
	
	public static Response createUser(User payload) {
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(Routes.post_url);
		
		return response;
	}
	
	public static Response getUser(String userName) {
		Response response = given()
		.pathParam("username", userName)
		.when()
		.get(Routes.get_url);

		return response;
	}
	
	public static Response updateUser(User payload,String userName) {
		Response response = given()
		.pathParam("username", userName)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
		.pathParam("username", userName)
		.when()
		.delete(Routes.delete_url);
		
		return response;
	}
}
