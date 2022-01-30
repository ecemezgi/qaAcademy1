package services;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Sample4Test {

    @Test
    public void getIventory(){
        String baseUrl="https://petstore.swagger.io/v2/store/inventory";

        RequestSpecification requestSpecification = RestAssured.given()
                .header("Study","Test")
                .log().all();

        Response response = requestSpecification.get(baseUrl);
        attachment(requestSpecification,baseUrl,response);
        Assert.assertEquals(response.getStatusCode(),200);


    }

    public String attachment(RequestSpecification httpRequest, String baseUrl, Response response){

        String html = "Url = " + baseUrl + "\n\n" +
                "request header ="  +((RequestSpecificationImpl) httpRequest).getHeaders() + "\n\n" +
                "request body =" +((RequestSpecificationImpl) httpRequest).getBody() + "\n\n" +
                "response body =" + response.getBody().asString();

        Allure.addAttachment("request detail",html);
        return html;


    }

}
