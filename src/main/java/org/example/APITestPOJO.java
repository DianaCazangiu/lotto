package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITestPOJO {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // ✅ GET cu mapare la obiect
    @Test
    public void testGetPostAsPojo() throws JSONException {
        Post post =
                given()
                        .when()
                        .get("/posts/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(Post.class); // mapare JSON → Post

        // afișare obiect direct
        System.out.println("Mapped POJO: " + post);

        // accesare câmpuri direct din obiect
        System.out.println("ID: " + post.getId());
        System.out.println("Title: " + post.getTitle());

        // poți folosi obiectul în asserts
        assert(post.getId() == 1);
        assert(post.getUserId() == 1);

        }

    // ✅ POST + print response
    @Test
    public void testCreatePostAsPojo() throws JSONException {
        JSONObject request = new JSONObject();
        request.put("title", "foo");
        request.put("body", "bar");
        request.put("userId", 1);

        Post post =
                given()
                        .header("Content-Type", "application/json")
                        .body(request.toString())
                        .when()
                        .post("/posts")
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(Post.class); // mapare JSON → Post



        // afișare obiect direct
        System.out.println("Mapped POJO: " + post);

        // accesare câmpuri direct din obiect
        System.out.println("ID: " + post.getId());
        System.out.println("Title: " + post.getTitle());

        // poți folosi obiectul în asserts
        assert(post.getId() == 101);
        assert(post.getUserId() == 1);
    }


}
