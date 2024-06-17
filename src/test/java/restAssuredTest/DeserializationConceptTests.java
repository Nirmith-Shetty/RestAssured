package restAssuredTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import models.CatFacts;
import models.CatFactsResponse;
import models.UsersResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeserializationConceptTests {



    @Test
    public void catGetRequestTest() {

        Response response = RestAssured.given()
                //Base url, headers, path parameters, query parameters, authorization
                .baseUri("https://cat-fact.herokuapp.com")
                .log().all()

                .when()
                //Make the request (GET/POST/PUT/DELETE/PATCH)
                .get("/facts");


       List<CatFacts> catFactsList= response.as(new TypeRef<List<CatFacts>>() {});


        Assert.assertEquals(catFactsList.get(0).getSource(), "user");

    }


    @Test
    public void testExampleTwo(){


        Response response = RestAssured.given()
                .baseUri("https://reqres.in")
                .queryParam("page",2)
                .log().all()

                .when()
                .get("/api/users");


        UsersResponse usersResponse = response.as(UsersResponse.class);
        Assert.assertEquals(usersResponse.getSupport().getUrl(),"https://reqres.in/#support-heading");
    }


}
