package alidoran.design_pattern.java

import android.widget.Toast

class Hamburger(
    onion: Boolean,
    beef: Boolean,
    cheese: Boolean
) {
    class Builder {
        private var onion = false
        private var beef = false
        private var cheese = false

        fun onion(value: Boolean) = apply { onion = value }
        fun beef(value: Boolean) = apply { beef = value }
        fun cheese(value: Boolean) = apply { cheese = value }
        fun build() = Hamburger(onion, beef, cheese)
    }
}

fun main() {
    val hamBuilder = Hamburger.Builder().beef(true).onion(true).cheese(true)
    val mHamburger = hamBuilder.build()

}