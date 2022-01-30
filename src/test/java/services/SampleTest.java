package services;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.OrderRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class SampleTest {

    @Test
    public void sample(){
        Response response = RestAssured.get("https://petstore.swagger.io/v2/store/inventory");

        System.out.println("getBody: " + response.asString());
        System.out.println("getBody: " + response.getBody().asString());
        System.out.println("getstatuscode: " + response.getStatusCode());
        //System.out.println("getheader: " + response.getHeaders());

        Assert.assertEquals(response.getStatusCode(),200);


    }

    @Test
    public void getIventory(){
        given()
                .log().all().
                when()
                .get("https://petstore.swagger.io/v2/store/inventory").
                then().
                statusCode(200).
                time(lessThan(2000L)).log().all();
    }

    @Test
    public void findPetId(){
        int petId = 1;
        given().
                log().all().
                when().get("https://petstore.swagger.io/v2/pet/" + petId).
                then().
                body("id",equalTo(petId))
                .body("name",equalTo("Cat"))
                .body("status",equalTo("Sold"))
                .log().all();
    }

    @Test
    public void addNewPet(){
        String postData = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Nemo\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given().body(postData)
                .contentType(ContentType.JSON)
                .log().all()
                .when().post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).log().all();

    }

    @Test
    public void postOrder(){
        OrderRequest orderRequest = new OrderRequest(1,1,1,"2022-01-30T14:59:30.555Z","placed",true);

        String request = new Gson().toJson(orderRequest);
        given()
                .log().all().header("Content-Type","application/json")
                .body(request).
                when().post("https://petstore.swagger.io/v2/store/order").
                then().statusCode(200).log().all();
    }

    @Test
    public void postUpdatePet(){

        int petId = 10;

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name","Jane Doe");
        queryParams.put("status","available");

        given().log().all().queryParams(queryParams).when().post("https://petstore.swagger.io/v2/pet/" + petId)
                .then().statusCode(200).log().all();

    }


}
