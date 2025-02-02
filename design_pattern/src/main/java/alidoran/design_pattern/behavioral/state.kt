package alidoran.design_pattern.behavioral

// State Interface
interface PlayerState {
    fun pressPlay(player: MediaPlayer)
}

// Concrete States
class PlayingState : PlayerState {
    override fun pressPlay(player: MediaPlayer) {
        println("Pausing music...")
        player.state = PausedState()
    }
}

class PausedState : PlayerState {
    override fun pressPlay(player: MediaPlayer) {
        println("Resuming music...")
        player.state = PlayingState()
    }
}

// Context
class MediaPlayer {
    var state: PlayerState = PausedState() // Default state

    fun pressButton() = state.pressPlay(this)
}

fun main() {
    val player = MediaPlayer()

    player.pressButton() // Output: Resuming music...
    player.pressButton() // Output: Pausing music...
}
