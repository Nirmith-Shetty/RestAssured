package restAssuredTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class CatTest {



    @Test
    public void catGetRequestTest(){

        RestAssured.given()
                //Base url, headers, path parameters, query parameters, authorization
                .baseUri("https://cat-fact.herokuapp.com")
                .log().all()

                .when()
                //Make the request (GET/POST/PUT/DELETE/PATCH)
                .get("/facts")

                .then()
                //capture the response and assert on the response
                .assertThat()
                .statusCode(200)
                .body("[4].status.sentCount", Matchers.equalTo(1))
                .log().all();

    }


    @Test
    public void testQueryParamTest(){

        RestAssured.given()
                .baseUri("https://reqres.in")
                .queryParam("page",2)
                .log().all()

                .when()
                .get("/api/users")

                .then().
                assertThat()
                .statusCode(200)
                .log().all();


    }


    @Test
    public void createPostTest()
    {

        RestAssured.given()
                .baseUri("https://reqres.in")
                .header("Content-Type", "application/json")
                //.contentType(ContentType.JSON) //use this or above one
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .log().all()

                .when()
                .post("/api/users")

                .then()
                .assertThat()
                .statusCode(201)
                .log().all();


    }


    @Test
    public void putRequestTest(){
        RestAssured
                .given()
                .baseUri("https://reqres.in")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}")
                .log().all()

                .when()
                .put("/api/users/2")
         .then()
         .assertThat()
         .statusCode(200)
         .body("job" , Matchers.equalTo("Nirmith"))
         .log().all();


//       String putResponse = response.getBody().asString();
//
//       Assert.assertEquals(putResponse.contains("QA"), true);

    }



    @Test
    public void testAuthorization(){

        Response response = RestAssured.given()
                .baseUri("https://api.escuelajs.co")
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"email\": \"john@mail.com\",\n" +
                        "  \"password\": \"changeme\"\n" +
                        "}")
                .post("/api/v1/auth/login");

        String authToken = response.getBody().jsonPath().getString("access_token");



        RestAssured.given()
                .baseUri("https://api.escuelajs.co")
                .header("Authorization", "Bearer "+ authToken)
                .log().all()
                .when()
                .get("/api/v1/auth/profile")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();


    }







}
