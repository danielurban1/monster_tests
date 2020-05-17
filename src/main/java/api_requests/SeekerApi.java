package api_requests;


import api_requests.models.GetByEmailResponseParams;
import enums.URLs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class SeekerApi {
    private static final String APP_ID = "Lexus";
    private static final int CHANNEL_ID = 10338;

    public SeekerApi() {
        RestAssured.baseURI = URLs.SERVICES_SEEKER_API_ME_URL.getUrl();
    }

    public GetByEmailResponseParams getByEmail(String email) {
        String path = "getbyemail";
        Response response = given()
                .accept(ContentType.JSON)
                .header("AppID", APP_ID)
                .header("ChannelID", CHANNEL_ID)
                .queryParam("emailAddress", email)
                .when()
                .get(path)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        return response.as(GetByEmailResponseParams.class);
    }
}
