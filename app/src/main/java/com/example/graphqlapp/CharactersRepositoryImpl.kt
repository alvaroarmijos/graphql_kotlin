package com.example.graphqlapp

import com.apollographql.apollo3.api.ApolloResponse
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersApi: RickAndMortyApi
) : CharactersRepository{
    override suspend fun getCharacters(): ApolloResponse<CharactersListQuery.Data> {
        return charactersApi.getApolloClient().query(CharactersListQuery()).execute()
    }
}