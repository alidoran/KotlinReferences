package alidoran.design_pattern.structural

interface MediaPlayer {
    fun play(file: String)
}

class MP3Player : MediaPlayer {
    override fun play(file: String) = println("Playing MP3 file: $file")
}

class MP4Player {
    fun playMP4(file: String) = println("Playing MP4 file: $file")
}

class MP4Adapter(private val mp4Player: MP4Player) : MediaPlayer {
    override fun play(file: String) = mp4Player.playMP4(file)
}

fun main() {
    val mp3Player: MediaPlayer = MP3Player()
    mp3Player.play("song.mp3")

    val mp4Adapter: MediaPlayer = MP4Adapter(MP4Player())
    mp4Adapter.play("video.mp4")
}
