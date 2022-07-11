package alidoran.third_party.apis.graphql_retrofit


    data class CityModel(
        val data: Data
    )

    data class Data(
        val getCityByName: GetCityByName
    )

    data class GetCityByName(
        val id: String,
        val name: String,
        val country: String,
        val coord: Coord
    )

    data class Coord(
        val lon: Double,
        val lat: Double
    )