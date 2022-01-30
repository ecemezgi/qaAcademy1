package services;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Sample2Test {

    @Test
    public void postCreatePet(){
        String postData = "{\n" +
                "  \"id\": 996,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"Nemo\"\n" +
                "  },\n" +
                "  \"name\": \"Nemo\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 996,\n" +
                "      \"name\": \"Nemo\"\n" +
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
    public void getPetDetail(){
        int petId = 998;
        given().
                log().all().
                when().get("https://petstore.swagger.io/v2/pet/" + petId).
                then().
                body("id",equalTo(petId))
                .log().all();
    }

/*    @AfterTest
    public void deletePet(){

        int petId = 998;

        given().log().all().header("api-key","special-key").
                when().delete("https://petstore.swagger.io/v2/pet/" + petId).
                then().statusCode(200).log().all();

    }*/
}
