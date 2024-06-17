package restAssuredTest;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.UserProfile;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class SerializationConcepts {


    @Test()
    public void testSerializationExample(){
        //in the form of class
        UserProfile userProfile = UserProfile.builder().name("morpheus").job("QA").build();
        // we need to convert this class to Json format
        Gson gson = new Gson();
        String userProfileInJsonFormat = gson.toJson(userProfile);

            RestAssured
                    .given()
                    .baseUri("https://reqres.in")
                    .contentType(ContentType.JSON)
                    .body(userProfileInJsonFormat)
                    .log().all()

                    .when()
                    .put("/api/users/2")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .body("job" , Matchers.equalTo("QA"))
                    .body("name" , Matchers.equalTo("morpheus"))
                    .log().all();

        }


}
