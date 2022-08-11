package edu.itsco.rickmortyclient.data.api

import edu.itsco.rickmortyclient.data.api.model.Characters
import retrofit2.http.GET

interface RickMortyApi {

    //Es la petición HTTP
    @GET("character")
    suspend fun getCharacters(): Characters


    /*
    @GET("episode")
    suspend fun getEpisodes()

    @POST("location")
    suspend fun getEpisodes()
    */

}