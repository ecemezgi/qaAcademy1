package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sample4Test {

    @Test
    public void getIventory(){
        String baseUrl="https://petstore.swagger.io/v2/store/inventory";

        RequestSpecification requestSpecification = RestAssured.given()
                .header("Study","Test")
                .log().all();

        Response response = requestSpecification.get(baseUrl);
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
