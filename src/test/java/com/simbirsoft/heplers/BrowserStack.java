package com.simbirsoft.heplers;

import static io.restassured.RestAssured.given;

public class BrowserStack {

    public static String videoUrl(String sessionId) {
        return given()
                .auth().basic("dankovalexander_2yl8UX", "W2RpSucJxcr84KwAcs89")
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId +".json")
                .then()
                .statusCode(200)
                .log().body()
                .extract()
                .response()
                .path("automation_session.video_url");

    }
}
