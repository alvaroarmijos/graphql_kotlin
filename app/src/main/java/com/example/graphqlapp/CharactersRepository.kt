package com.example.graphqlapp

import com.apollographql.apollo3.api.ApolloResponse


interface CharactersRepository {

    suspend fun getCharacters(): ApolloResponse<CharactersListQuery.Data>
}