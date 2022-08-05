package liveproject;



import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;


@Provider("UserProvider")
@PactFolder("target/pacts")
public class PactPoviderTest {

    @BeforeEach
    public void createProvider(PactVerificationContext cont){

        HttpTestTarget target = new HttpTestTarget("localhost",8585);
        cont.setTarget(target);

    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void providerTest(PactVerificationContext cont){
     cont.verifyInteraction();


    }

    @State("A request to create a user")
    public void providerState(){

    }


}
