package liveproject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHub_RestAssured_Project {
    //Base URI
    String baseURI = "https://api.github.com";
    String accessToken ="token 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";
    int id;
    RequestSpecification req;

    @BeforeClass
    public void setUp(){

        //Request Specefication
       req = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURI)
                .addHeader("Authorization",accessToken)
                .build();

    }


    @Test(priority=1)
    public void test1(){
        //Method 1 for post request

        String body = "{\n" +
                "    \"title\": \"TestAPIKey\",\n" +
                "    \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQClZSbN+1ujeCmgn9jnwrtYgYUeTAmqE6LZyMQf/mbHBxfGJMP0lB9m+Q49a6xEgAc1i9NST1Ir3pXNUjvvwmSEsbsj+X6TW4ZU+f/rgHZ5EcB41CejHjzs2xT3kU194SHi+6hLbAgrewrdaXY23JTwKiK4OI3mDMogA3P1roUTTvljlB7KaVPDOioCkwUyD5foS2VHI3Fi5FbI0TJPeQMupLhEenk5Kv5NeElXycg1qTeJD5VVv1iBE8dx4mW7XgpMZjbzU3M1Eshw339a6cHpxQbN6vUT9caCMwuBqrVRUPtxh3Bs5Xv2QKEtIDgN3ECWyfvsiFNC5wnvAER+Dfx3 \"\n" +
                "}";

        Response res = given().spec(req).body(body).when().post("/user/keys");
        res.then().log().all().statusCode(201);
        id = res.then().extract().path("id");
        System.out.println(res.asPrettyString());


    }

    @Test(priority=2)
    public void test2(){
        //Method 2 for Get request

        Response res = given().spec(req).when().pathParam("keyId",id).get("/user/keys/{keyId}");
        res.then().log().all().statusCode(200);
        System.out.println(res.asPrettyString());


    }

    @Test(priority=3)
    public void test3(){
        //Method 3 for Delete request

        Response res = given().spec(req).when().pathParams("keyId",id).delete("/user/keys/{keyId}");
        res.then().log().all().statusCode(204);
        System.out.println(res.asPrettyString());


    }

}
