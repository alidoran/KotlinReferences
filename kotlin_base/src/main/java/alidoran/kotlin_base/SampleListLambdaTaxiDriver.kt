package alidoran.kotlin_base

data class TaxiPark(
    val allDriver: Set<Driver>,
    val allPassenger: Set<Passenger>,
    val trip: List<Trip>
)

data class Driver(val name: String)
data class Passenger(val name: String)

data class Trip(
    val driver: Driver,
    val passenger: Set<Passenger>,
    val duration: Int,
    val distance: Double,
    val discount: Double? = null
) {
    val cost: Double
        get() = (1 - (discount ?: 0.0)) * (duration + distance)
}

