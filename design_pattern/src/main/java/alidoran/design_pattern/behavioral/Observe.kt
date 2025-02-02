package alidoran.design_pattern.behavioral

interface Observer {
    fun update(temperature: Float)
}

class WeatherStation {
    private val observers = mutableListOf<Observer>()
    fun addObserver(observer: Observer) = observers.add(observer)
    fun removeObserver(observer: Observer) = observers.remove(observer)
    fun notifyObservers(temp: Float) = observers.forEach { it.update(temp) }
}

class PhoneDisplay : Observer {
    override fun update(temperature: Float) = println("Phone Display: Temperature is $temperature°C")
}

fun main() {
    val weatherStation = WeatherStation()
    val phoneDisplay = PhoneDisplay()

    weatherStation.addObserver(phoneDisplay)
    weatherStation.notifyObservers(25.5f)
}