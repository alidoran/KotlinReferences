package alidoran.design_pattern.creational

object DatabaseConnection {
    init {
        println("Database Connection Initialized")
    }

    fun connect() {
        println("Connected to the Database")
    }

    fun disconnect() {
        println("Disconnected from the Database")
    }
}

fun main() {
    val db1 = DatabaseConnection
    val db2 = DatabaseConnection

    db1.connect()
    db2.disconnect()

    println("Are both instances the same? ${db1 === db2}")
}