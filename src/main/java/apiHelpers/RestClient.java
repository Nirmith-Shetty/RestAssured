package apiHelpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class RestClient {

    public Response getRequest(String path) {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .extract().response();
        return response;
    }

    public Response getRequest(String path, Map<String, String> headers) {
        Response response = RestAssured.given()
                .headers(headers)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .extract().response();
        return response;
    }


    public Response getRequestWithQueryParams(String path, Map<String, Object> queryParams) {
        Response response = RestAssured.given()
                .queryParams(queryParams)
                .log().all()
                .when()
                .get(path)
                .then()
                .log().all()
                .extract().response();
        return response;
    }


}
