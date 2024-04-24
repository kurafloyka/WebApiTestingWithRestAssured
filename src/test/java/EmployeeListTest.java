import employeelist.DataItem;
import employeelist.EmployeeList;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
public class EmployeeListTest extends BaseTest {
    private static final String EMPLOYEE_LIST_ENDPOINT = "/employees";

    @Test
    @DisplayName("Employee List Test")
    @Story("User validates to lists size and specified employee")
    @Description("Employee List Test")
    @Epic("EMPLOYEE LIST WEB SERVICES TEST")
    public void employeeListTest() throws IOException {

        LOGGER.info("EMPLOYEE LIST WEB SERVICES TEST");
        Response response = given()
                .when()
                .log().all()
                .request("GET", EMPLOYEE_LIST_ENDPOINT)
                .prettyPeek();
        response.then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .assertThat().body("data.size()", is(24));

        EmployeeList employeeList = response.getBody().as(EmployeeList.class);
        for (DataItem dataItem : employeeList.getData()) {
            //LOGGER.info(dataItem.getEmployeeName());
            //TODO I need to handle specific data with Model on JSON
            if (dataItem.getEmployeeSalary() == 313500) {
                assertEquals(dataItem.getEmployeeName(), "Haley Kennedy");
                LOGGER.info("Haley Kennedy is verified.");
            }
        }

    }
}
