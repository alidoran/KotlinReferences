package alidoran.android.socket

import java.net.Socket

class Client {
    fun run() {
            val client = Socket("127.0.0.1", 12345)
            client.getOutputStream().write(byteArrayOf(96, 65))
    }
}