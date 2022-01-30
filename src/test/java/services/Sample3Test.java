package services;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Sample3Test {


    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider(){
        return new Object[][] {
            {998,200},
            {997,200},
            {996,200}
        };


    }

    @Test(dataProvider = "dataProvider")
    public void petDetail(int petId, int statusCode){

        given().
                log().all().
                when().get("https://petstore.swagger.io/v2/pet/" + petId).
                then().
                body("id",equalTo(petId))
                .statusCode(statusCode)
                .log().all();
    }
}
