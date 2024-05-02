package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authentication.generateToken;

public class ContactListBaseUrl {

    public static RequestSpecification spec;

    static {
//static block will work before anything that is called from this class.
// When we call spec object from another class, static block will work and assign value to spec.
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+generateToken())
                .build();

    }


}