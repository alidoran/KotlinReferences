package alidoran.third_party.apis.graphql_apollo

import com.apollographql.apollo3.ApolloClient

val apolloExampleClient = ApolloClient.Builder()
    .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
    .build()

val weatherClient = ApolloClient.Builder()
    .serverUrl("https://graphql-weather-api.herokuapp.com/")
    .build()