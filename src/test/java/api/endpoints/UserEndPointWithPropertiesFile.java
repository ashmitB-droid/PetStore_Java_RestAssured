package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloadpojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPointWithPropertiesFile {
	
	
	public static Response createUser(User payload) {
		Response response = given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.post(getUrl().getString("post_url"));
		return response;
	}
	
	public static Response getUser(String userName) {
		Response response = given()
		.pathParam("username", userName)
		.when()
		.get(getUrl().getString("get_url"));

		return response;
	}
	
	public static Response updateUser(User payload,String userName) {
		Response response = given()
		.pathParam("username", userName)
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		.when()
		.put(getUrl().getString("update_url"));
		
		return response;
	}
	
	public static Response deleteUser(String userName) {
		Response response = given()
		.pathParam("username", userName)
		.when()
		.delete(getUrl().getString("delete_url"));
		
		return response;
	}
	
	static ResourceBundle getUrl() {
		ResourceBundle rbroute = ResourceBundle.getBundle("routes"); 
		return rbroute;
	}
}
