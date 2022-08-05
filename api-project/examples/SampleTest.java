package examples;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SampleTest {

    final String baseURI = "https://petstore.swagger.io/v2";

    @Test
    public void firstTest(){

        Response res = given().contentType(ContentType.JSON).get(baseURI+"/pet/findByStatus?status=sold");
        System.out.println(res.getHeaders().asList());
        System.out.println(res.asString());
    }
}
