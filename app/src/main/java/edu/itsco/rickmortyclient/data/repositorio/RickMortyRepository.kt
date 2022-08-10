package edu.itsco.rickmortyclient.data.repositorio

import edu.itsco.rickmortyclient.data.api.RickMortyApi
import edu.itsco.rickmortyclient.data.api.model.Characters
import javax.inject.Inject

class RickMortyRepository @Inject constructor(
    private val rickMortyApi: RickMortyApi
){
    suspend fun getCharacters(): Characters{
        return rickMortyApi.getCharacters()
    }
}