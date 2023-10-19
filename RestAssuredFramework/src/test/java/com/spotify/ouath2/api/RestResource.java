package com.spotify.ouath2.api;

import com.spotify.ouath2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.ouath2.api.Routes.API;
import static com.spotify.ouath2.api.Routes.TOKEN;
import static com.spotify.ouath2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                body(requestPlaylist).
                auth().oauth2(token).
        when().
                post(path).
        then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given(getAccountRequestSpec()).
                formParams(formParams).
                when().
                post(API + TOKEN).then().spec(getResponseSpec()).
                extract().
                response();
    }


    public static Response get(String path, String token){
        return  given(getRequestSpec()).
        when().
                auth().oauth2(token).
                get(path). //0MrQuFIlpjr6H8hT27PVoe
        then().
                spec(getResponseSpec()).
                extract().
                response();

    }

    public static Response update(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                auth().oauth2(token).
                body(requestPlaylist).
        when().
                put(path).//2lw83tjQiIDviMovNGvG8a
        then().
                spec(getResponseSpec()).
                extract().
                response();
    }

}
