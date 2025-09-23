package org.example;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;


public class ComplexAPITest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // ✅ GET + print response
    @Test
    public void testGetPost() {
        Response response =
                given()
                        .when()
                        .get("/posts/1")
                        .then()
                        .statusCode(200)
                        .body("id", equalTo(1))
                        .extract().response();

        System.out.println("GET Response:\n" + response.asString());

        // extragere câmpuri individuale
        int id = response.jsonPath().getInt("id");
        int userId = response.jsonPath().getInt("userId");
        String title = response.jsonPath().getString("title");

        System.out.println("Extracted values:");
        System.out.println("id = " + id);
        System.out.println("userId = " + userId);
        System.out.println("title = " + title);
    }

    // ✅ POST + print response
    @Test
    public void testCreatePost() throws JSONException {
        JSONObject request = new JSONObject();
        request.put("title", "foo");
        request.put("body", "bar");
        request.put("userId", 1);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(request.toString())
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .extract().response();

        System.out.println("POST Response:\n" + response.asString());

        // extragere id nou creat
        int newId = response.jsonPath().getInt("id");
        String newTitle = response.jsonPath().getString("title");

        System.out.println("New Post created:");
        System.out.println("id = " + newId);
        System.out.println("title = " + newTitle);
    }

    // ✅ PUT + print response
    @Test
    public void testUpdatePost() throws JSONException {
        JSONObject request = new JSONObject();
        request.put("id", 1);
        request.put("title", "updated title");
        request.put("body", "updated body");
        request.put("userId", 1);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(request.toString())
                        .when()
                        .put("/posts/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println("PUT Response:\n" + response.asString());
    }

    // ✅ DELETE + print response
    @Test
    public void testDeletePost() {
        Response response =
                given()
                        .when()
                        .delete("/posts/1")
                        .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println("DELETE Response:\n" + response.asString());
    }


}
