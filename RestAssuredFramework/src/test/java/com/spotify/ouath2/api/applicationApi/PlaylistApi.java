package com.spotify.ouath2.api.applicationApi;

import com.spotify.ouath2.api.RestResource;
import com.spotify.ouath2.pojo.Playlist;
import com.spotify.ouath2.utils.ConfigLoader;
import io.restassured.response.Response;

import static com.spotify.ouath2.api.Routes.PLAYLISTS;
import static com.spotify.ouath2.api.Routes.USERS;
import static com.spotify.ouath2.api.TokenManager.getToken;


public class PlaylistApi {


    public static Response post(Playlist requestPlaylist){
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() +
                PLAYLISTS, getToken(), requestPlaylist);
    }

    public static Response post(String token, Playlist requestPlaylist){
        return RestResource.post(USERS + "/" +  ConfigLoader.getInstance().getUser() +
                PLAYLISTS, token, requestPlaylist);
    }

    public static Response get(String playlistId){
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken() );
    }

    public static Response update(String playlistId, Playlist requestPlaylist){
        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), requestPlaylist);
    }
}
