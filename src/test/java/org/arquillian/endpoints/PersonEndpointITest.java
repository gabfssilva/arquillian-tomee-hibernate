package org.arquillian.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.arquillian.entities.Person;
import org.arquillian.producers.EntityManagerProducer;
import org.arquillian.repositories.PersonRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.*;
import static  org.hamcrest.Matchers.*;

@RunWith(Arquillian.class)
public class PersonEndpointITest {

    @Deployment
    public static WebArchive webArchive(){
        WebArchive webArchive = ShrinkWrap
                .create(WebArchive.class, "app.war")
                .addClasses(Person.class, PersonRepository.class, EntityManagerProducer.class, PersonEndpoint.class, JacksonJsonProvider.class)
                .addAsResource("META-INF/beans.xml", "META-INF/beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");

        return webArchive;
    }


    @Test
    public void mustReturn201AfterResourceCreated() throws JsonProcessingException {
        String successfulRequest = "{\"name\":\"Gabriel\",\"occupation\":\"Systems Analyst\"}";

        given()
            .contentType(MediaType.APPLICATION_JSON)
        .and()
            .request()
                 .body(successfulRequest)
        .then()
            .when()
                .post("/app/people")
            .then()
                .assertThat()
                    .statusCode(201)
                .and()
                    .body("id", notNullValue());
    }


    @Test
    public void mustReturn200OkAfterGetAnExistingResource() throws JsonProcessingException {
        String successfulRequest = "{\"name\":\"Gabriel\",\"occupation\":\"Systems Analyst\"}";

        String uri = given()
                        .contentType(MediaType.APPLICATION_JSON)
                    .and()
                        .request()
                    .body(successfulRequest)
                        .then()
                    .when()
                        .post("/app/people")
                    .then()
                        .assertThat()
                            .body("id", notNullValue())
                    .and()
                        .extract()
                            .header("location");

        when()
            .get(uri)
        .then()
            .assertThat()
                .statusCode(200)
             .and()
                .body("id", notNullValue());
    }


    @Test
    public void shouldContainAtLeast10() throws JsonProcessingException {
        String successfulRequest = "{\"name\":\"Gabriel\",\"occupation\":\"Systems Analyst\"}";

        for(int i = 0; i< 10;i++){
            given()
                .contentType(MediaType.APPLICATION_JSON)
            .and()
                .request()
                    .body(successfulRequest)
            .then()
            .when()
                .post("/app/people")
            .then()
                .assertThat()
                    .body("id", notNullValue());
        }


        when()
            .get("/app/people")
                .then()
                    .assertThat()
                        .statusCode(200)
                    .and()
                        .body("id", hasSize(greaterThanOrEqualTo(10)));
    }
}