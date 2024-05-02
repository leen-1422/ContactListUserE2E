package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojos.ContactPojo;
import pojos.UserPojo;
import pojos.UserResponsePojo;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactListContactStepDefinitions {
    ContactPojo expectedData;
    Response response;

    @Given("set the url for adding contact")
    public void set_the_url_for_adding_contact() {
        //https://thinking-tester-contact-list.herokuapp.com/contacts
        spec.pathParams("first", "contacts");
    }

    @Given("set the expected data for adding contact")
    public void set_the_expected_data_for_adding_contact() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "Lama",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);


    }

    @When("send the post request for adding contact")
    public void send_the_post_request_for_adding_contact() {
        response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();
    }

    @Then("do assertion for adding contact")
    public void do_assertion_for_adding_contact() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getBirthdate(), actualData.getBirthdate());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getCountry(), actualData.getCountry());
        assertEquals(expectedData.getPhone(), actualData.getPhone());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getStreet1(), actualData.getStreet1());
        assertEquals(expectedData.getStreet2(), actualData.getStreet2());
        assertEquals(expectedData.getPostalCode(), actualData.getPostalCode());
        assertEquals(expectedData.getStateProvince(), actualData.getStateProvince());


    }


// update contact

    @Given("set the url for update contact")
    public void setTheUrlForUpdateContact() {
        spec.pathParams("first", "contacts","second","66340d6afddb5f00137d2a97");
    }

    @And("set the expected data for update contact")
    public void setTheExpectedDataForUpdateContact() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "Lama2",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);


//        expectedData = new ContactPojo("Amy",
//                "Miller",
//               "Canada",
//                "1992-02-02",
//               "8005554242",
//                "Washington",
//                "A1A1A1",
//                "QC",
//                "13 School St.",
//                "Apt. 5",
//                "amiller@fake.com");
//        System.out.println("expectedData = " + expectedData);

    }

    @When("send the put request for update contact")
    public void sendThePutRequestForUpdateContact() {
        response = given(spec).body(expectedData).put("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for update contact")
    public void doAssertionForUpdateContact() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getBirthdate(), actualData.getBirthdate());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getCountry(), actualData.getCountry());
        assertEquals(expectedData.getPhone(), actualData.getPhone());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getStreet1(), actualData.getStreet1());
        assertEquals(expectedData.getStreet2(), actualData.getStreet2());
        assertEquals(expectedData.getPostalCode(), actualData.getPostalCode());
        assertEquals(expectedData.getStateProvince(), actualData.getStateProvince());
    }

    //read contact

    @Given("set the url for Read")
    public void setTheUrlForRead() {
        spec.pathParams("first", "contacts","second","66340d6afddb5f00137d2a97");


    }

    @And("set the expected data for read")
    public void setTheExpectedDataForRead() throws JsonProcessingException {
        String strJson = """
                {
                    "firstName": "Lama2",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        expectedData = new ObjectMapper().readValue(strJson, ContactPojo.class);
        System.out.println("expectedData = " + expectedData);


    }

    @When("send the get request for read")
    public void sendTheGetRequestForRead() {
        response = given(spec).get("{first}/{second}");
        response.prettyPrint();

    }

    @Then("do assertion for read")
    public void doAssertionForRead() {
        ContactPojo actualData = response.as(ContactPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getBirthdate(), actualData.getBirthdate());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getCountry(), actualData.getCountry());
        assertEquals(expectedData.getPhone(), actualData.getPhone());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getStreet1(), actualData.getStreet1());
        assertEquals(expectedData.getStreet2(), actualData.getStreet2());
        assertEquals(expectedData.getPostalCode(), actualData.getPostalCode());
        assertEquals(expectedData.getStateProvince(), actualData.getStateProvince());

    }



    //delete contact

    @Given("set the url for delete contact")
    public void setTheUrlForDeleteContact() {
        spec.pathParams("first", "contacts","second","6634176ffddb5f00137d2abc");


    }

    @When("send the delete request contact")
    public void sendTheDeleteRequestContact() {
        response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

    }

    @Then("do assertion for delete contact")
    public void doAssertionForDeleteContact() {
        assertEquals(200, response.statusCode());

    }
}