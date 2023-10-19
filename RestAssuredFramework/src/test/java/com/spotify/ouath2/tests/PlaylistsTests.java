package com.spotify.ouath2.tests;

import com.spotify.ouath2.api.StatusCode;
import com.spotify.ouath2.api.applicationApi.PlaylistApi;
import com.spotify.ouath2.pojo.Error;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.DataLoader;
import io.qameta.allure.*;

import io.restassured.response.Response;

import org.testng.annotations.Test;

import static com.spotify.ouath2.utils.FakerUtils.generateDescription;
import static com.spotify.ouath2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistsTests extends BaseTest{

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("123456")
    @Issue("1234568")
    @Description("This is description")
    @Test(description = "Should be able to create a playlist")
    public void ShouldBeAbleToCreateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(), false);
        Response response =  PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201.getCode());
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }


    @Test
    public void ShouldBeAbleToGetAPlaylist(){
        Playlist requestPlaylist = playlistBuilder("Updated Playlist Name",
                "Updated playlist description", false);
        Response response =  PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(responsePlaylist, requestPlaylist);
    }



    @Test
    public void ShouldBeAbleToUpdateAPlaylist(){
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(), false);
        Response response =  PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200.getCode());
    }

    @Test
    @Story("Create a playlist story")
    public void ShouldNotBeAbleToCreateAPlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response =  PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400.getCode());
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_400.getCode());
    }

    @Test
    @Story("Create a playlist story")
    public void ShouldNotBeAbleToCreateAPlaylistWithExpiredToken(){
        String invalid_token = "123456";
        Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(), false);
        Response response =  PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(),StatusCode.CODE_401.getCode());

    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public){
        return new Playlist().builder().
                name(name).
                description(description).
                _public(_public)
                .build();
    }
    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist){
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }
    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode){
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }
    @Step
    public void assertError(Error responseError, int expectedStatusCode){
        assertThat(responseError.getError().getStatus(), equalTo(expectedStatusCode));
    }
}
