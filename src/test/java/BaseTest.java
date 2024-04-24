import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static io.restassured.RestAssured.*;

public class BaseTest {
    static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setup() {

        LOGGER.info("-----BEFORE EACH STARTED-----");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://dummy.restapiexample.com";
        basePath = "/api/v1";


        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).build();


    }


}
