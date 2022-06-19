package android.socket

import java.net.ServerSocket
import kotlin.concurrent.thread


class Server {
    fun run() {
        val server = ServerSocket(12345)
        thread {
            while (true) {
                val socket = server.accept()
                for (b in socket.getInputStream().readBytes())
                    println(" $b ")
            }
        }
    }
}