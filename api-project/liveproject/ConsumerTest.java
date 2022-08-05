package liveproject;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {

    Map<String, String> headers = new HashMap<String, String>();

    String userResourcePath = "/api/users";

    @Pact(consumer = "UserConsumer", provider = "UserProvider")
    public RequestResponsePact creatPact(PactDslWithProvider builder) {
        headers.put("Content-Type", "application/json");

        //set body
        DslPart requestResponseBody = new PactDslJsonBody()
                .numberType("id")
                .stringType("firstName")
                .stringType("lastName")
                .stringType("email");

        //Create pact
        return builder.given("A request to create a user")
                .uponReceiving("A request to crete user")
                .method("POST")
                .headers(headers)
                .path(userResourcePath)
                .body(requestResponseBody)
                .willRespondWith()
                .status(201)
                .body(requestResponseBody).toPact();


    }

    @Test
    @PactTestFor(providerName = "UserProvider", port = "8282")
    public void consumerTest()

    {

        String baseUri = "http://localhost:8282";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 99);
        requestBody.put("firstName", "Jaga");
        requestBody.put("lastName", "Panda");
        requestBody.put("email", "pinkucet@gmail.com");

        // Generate Response
        Response res = given().headers(headers).when().body(requestBody).post(baseUri + userResourcePath);


    }


}
