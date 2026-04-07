package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FifthTest {

    @Test
    public void case5Test() {

        Response response = RestAssured
                .given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users");

        Assert.assertEquals(response.getStatusCode(), 200);

        List<Map<String, Object>> users = response.jsonPath().getList("");

        for (Map<String, Object> user : users) {
            String name = user.get("name").toString();
            String email = user.get("email").toString();
            System.out.println(name + " | " + email);
        }

        String firstEmail = users.get(0).get("email").toString();
        Assert.assertTrue(firstEmail.contains("@"));
    }
}