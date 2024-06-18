package restAssuredTest;

import apiHelpers.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import constants.HostURL;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import models.CatFacts;
import models.CatFactsResponse;
import models.UsersResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeserializationConceptTests {

    RestClient restClient = new RestClient();


    @Test
    public void catGetRequestTest() {

        RestAssured.baseURI = HostURL.CAT_FACTS_HOST;
        Response response = restClient.getRequest("facts");
        List<CatFacts> catFactsList= response.as(new TypeRef<List<CatFacts>>() {});
        Assert.assertEquals(catFactsList.get(0).getSource(), "user");


//
//        Response response = RestAssured.given()
//                //Base url, headers, path parameters, query parameters, authorization
//                .baseUri("https://cat-fact.herokuapp.com/")
//                .log().all()
//
//                .when()
//                //Make the request (GET/POST/PUT/DELETE/PATCH)
//                .get("/facts");
//
//
//       List<CatFacts> catFactsList= response.as(new TypeRef<List<CatFacts>>() {});
//
//
//        Assert.assertEquals(catFactsList.get(0).getSource(), "user");

    }


    @Test
    public void testExampleThree(){
        RestAssured.baseURI = HostURL.PET_STORE_HOST;
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("status","sold");
        Response response = restClient.getRequestWithQueryParams("v2/pet/findByStatus?status=sold", queryParam);
        System.out.println(response.asPrettyString());
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
