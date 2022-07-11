package alidoran.third_party.apis.graphql_apollo

/*
1- project build.gradle
                plugins {
                    id("com.android.application")
                    id("com.apollographql.apollo3").version("3.3.2")
}

2- project build.gradle
                apollo {
                    packageName.set("com.example.rocketreserver")
                }

3- project build.gradle
                dependencies {
                    implementation("com.apollographql.apollo3:apollo-runtime:3.3.2")
                }

4- Android studio terminal
my subProject name is thirdpartylibrarydependencies
VPN need in Iran
          ./gradlew :thirdpartylibrarydependencies:downloadApolloSchema --endpoint='https://apollo-fullstack-tutorial.herokuapp.com/graphql' --schema=thirdpartylibrarydependencies/src/main/graphql/com/example/rocketreserver/schema.graphqls

5- In "thirdpartylibrarydependencies/main/graphql/com/example/rocketreserver" level create a
new file by "LaunchList.graphql" name
put the below query there:
                query LaunchList {
                  launches {
                    cursor
                    hasMore
                    launches {
                      id
                      site
                    }
                  }
                }
If it ask for plugin install, accept it

6- rebuild project. your model should be created in blew address
thirdpartylibrarydependencies/build/generated/source/apollo/service/com/example/thirdpartylibrarydependencies/LaunchListQuery.kt

7- Create Apollo client variable by below code:
val apolloClient = ApolloClient.Builder()
    .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
    .build()

8- Run it in your Activity:
        GlobalScope.launch {
            Log.d(TAG, "onCreate: ")
            val response = apolloClient.query(LaunchListQuery()).execute()
            Log.d(TAG,response.data!!.launches.launches.get(0)!!.site.toString())
        }

------------------------------------------------------------------------------------------------------------------------------------------------------
Weather

2- Change apollo for multi API schema from below code:
                apollo {
                    packageName.set("com.alidoran.thirdpartylibrarydependencies")
                        }
To this code
                apollo {
                    service("weather") {
                    sourceFolder.set("weather")
                    packageName.set("weather")
                                        }
                service("rocketreserver") {
                sourceFolder.set("com/example/rocketreserver")
                packageName.set("com.example.rocketreserver")
                                            }
                        }

4-
./gradlew :thirdpartylibrarydependencies:downloadApolloSchema --endpoint='https://graphql-weather-api.herokuapp.com/' --schema=thirdpartylibrarydependencies/src/main/graphql/weather/schema.graphqls

5-query {getCityByName(name: "Tehran"){id,name,country,coord {lon,lat}}}

*/