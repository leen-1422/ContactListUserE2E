package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import pages.ContactListAddUserPage;
import pages.ContactListHomePage;

import pojos.UserPojo;
import pojos.UserResponsePojo;
import utilities.Driver;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EndToEndTestStepDefinitions {

    ContactListHomePage contactListHomePage;
    ContactListAddUserPage contactListAddUserPage;
    String firstname;
    String lastname;
    public static String email;
    UserPojo expectedData;
    Response response;


    @Given("user goes to {string}")
    public void user_goes_to(String url) {

        Driver.getDriver().get(url);
    }

    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {

        contactListHomePage = new ContactListHomePage();
        contactListHomePage.signup.click();

    }

    @When("User enters firstname, lastname, email, password")
    public void user_enters_firstname_lastname_email_password() {

        Faker faker = new Faker();
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        email = faker.internet().emailAddress();

        contactListAddUserPage = new ContactListAddUserPage();
        contactListAddUserPage.firstName.sendKeys(firstname);
        contactListAddUserPage.lastName.sendKeys(lastname);
        contactListAddUserPage.email.sendKeys(email);
        contactListAddUserPage.password.sendKeys("Password.123");

    }

    @When("user clicks on submit button")
    public void user_clicks_on_submit_button() throws InterruptedException {

        contactListAddUserPage.submit.click();
        Thread.sleep(1000);

    }


    @And("user closes browser")
    public void userClosesBrowser() {

        Driver.closeDriver();

    }

    @Then("verify the user via API request")
    public void verifyTheUserViaAPIRequest() {

        //Set the url
        //https://thinking-tester-contact-list.herokuapp.com/users/me
        spec.pathParams("first", "users", "second", "me");

        //Set the expected data
        /*
        {
          "_id": "608b2db1add2691791c04c89",
          "firstName": "Test",
          "lastName": "User",
          "email": "test@fake.com",
          "__v": 1
        }
         */
        UserResponsePojo expectedData = new UserResponsePojo(firstname, lastname, null, null, email);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        UserResponsePojo actualData = response.as(UserResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());

    }

    @Given("set the url for update")
    public void setTheUrlForUpdate() {
        spec.pathParams("first", "users", "second", "me");
    }

    @And("set the expected data for update")
    public void setTheExpectedDataForUpdate() {

        email = Faker.instance().internet().emailAddress();

        expectedData = new UserPojo("Mary", "Steel", "Password.123", email);
        System.out.println("expectedData = " + expectedData);

    }

    @When("send the patch request for update")
    public void sendThePatchRequestForUpdate() {
        response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for update")
    public void doAssertionForUpdate() {

        UserResponsePojo actualData = response.as(UserResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getEmail(), actualData.getEmail());

    }

    @Given("set the url for delete")
    public void setTheUrlForDelete() {
        spec.pathParams("first", "users", "second", "me");
    }

    @When("send the delete request")
    public void sendTheDeleteRequest() {
        response = given(spec).delete("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for delete")
    public void doAssertionForDelete() {
        assertEquals(200, response.statusCode());
        assertTrue(response.asString().isEmpty());
    }
}