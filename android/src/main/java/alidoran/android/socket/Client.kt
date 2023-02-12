package alidoran.android.socket

import java.net.Socket

class Client {
    fun run() {
            val client = Socket("wss://demo.piesocket.com/v3/channel_1?api_key=VCXCEuvhGcBDP7XhiJJUDvR1e1D3eiVjgZ9VRiaV&notify_self", 80)
            client.getOutputStream().write(byteArrayOf(96, 65))
    }
}