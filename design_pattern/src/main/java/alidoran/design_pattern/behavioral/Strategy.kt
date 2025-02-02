package alidoran.design_pattern.behavioral

interface PaymentStrategy {
    fun pay(amount: Double)
}

class CreditCardPayment : PaymentStrategy {
    override fun pay(amount: Double) = println("Paid $$amount with Credit Card")
}

class PayPalPayment : PaymentStrategy {
    override fun pay(amount: Double) = println("Paid $$amount using PayPal")
}

class PaymentProcessor(private val strategy: PaymentStrategy) {
    fun processPayment(amount: Double) = strategy.pay(amount)
}

fun main() {
    val payment = PaymentProcessor(CreditCardPayment())
    payment.processPayment(100.0)
}
