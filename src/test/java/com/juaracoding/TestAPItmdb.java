package com.juaracoding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPItmdb {

String url = "https://api.themoviedb.org/";
    @Test
    public void testNowPlaying() {

        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjMwMzdjM2FkZWUzMjlhMGI1MDQxMDNhNTM5NjMyNyIsInN1YiI6IjY0ZGI3MGI0Yjc3ZDRiMTE0MWY3N2I5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sQnQI-y5XF-Pv6epv-CZRxuoRVg8FKtkRxmaZvAIZyA")
                .queryParam("language", "en-US")
                .queryParam("page", "1")
                .get(url+"3/movie/now_playing").then().statusCode(200).body("results.id[0]",equalTo(976573));
    }

    @Test
    public void testMovie_Popular() {
        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjMwMzdjM2FkZWUzMjlhMGI1MDQxMDNhNTM5NjMyNyIsInN1YiI6IjY0ZGI3MGI0Yjc3ZDRiMTE0MWY3N2I5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sQnQI-y5XF-Pv6epv-CZRxuoRVg8FKtkRxmaZvAIZyA")
                .queryParam("language", "en-US")
                .queryParam("page", "1")
                .get(url+"3/movie/popular").then().statusCode(200).body("results.original_title[0]",equalTo("Spider-Man: Across the Spider-Verse"));
    }

    @Test
    public void testMovie_Rating() {
        JSONObject request = new JSONObject();
        request.put("value",8.00);
        given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzYjMwMzdjM2FkZWUzMjlhMGI1MDQxMDNhNTM5NjMyNyIsInN1YiI6IjY0ZGI3MGI0Yjc3ZDRiMTE0MWY3N2I5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.sQnQI-y5XF-Pv6epv-CZRxuoRVg8FKtkRxmaZvAIZyA")
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .post(url+"3/movie/569094/rating")
                .then()
                .statusCode(201).body("status_message",equalTo("The item/record was updated successfully."));

    }
}
