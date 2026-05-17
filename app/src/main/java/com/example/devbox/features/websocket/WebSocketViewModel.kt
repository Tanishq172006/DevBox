package com.example.devbox.features.websocket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import okhttp3.*

class WebSocketViewModel : ViewModel() {

    private val client =
        OkHttpClient()

    private var webSocket:
            WebSocket? = null

    var connectionStatus by
    mutableStateOf(
        "Disconnected"
    )

    var url by mutableStateOf(
        "wss://ws.ifelse.io"
    )

    var messageInput by
    mutableStateOf("")

    var messages =
        mutableStateListOf<String>()

    fun connect() {

        val request =
            Request.Builder()
                .url(url)
                .build()

        webSocket =
            client.newWebSocket(

                request,

                object : WebSocketListener() {

                    override fun onOpen(

                        webSocket: WebSocket,

                        response: Response

                    ) {

                        connectionStatus =
                            "Connected"

                        messages.add(
                            "✅ Connected"
                        )
                    }

                    override fun onMessage(

                        webSocket: WebSocket,

                        text: String

                    ) {

                        messages.add(
                            "📩 $text"
                        )
                    }

                    override fun onClosing(

                        webSocket: WebSocket,

                        code: Int,

                        reason: String

                    ) {

                        connectionStatus =
                            "Closing"
                    }

                    override fun onClosed(

                        webSocket: WebSocket,

                        code: Int,

                        reason: String

                    ) {

                        connectionStatus =
                            "Disconnected"

                        messages.add(
                            "❌ Disconnected"
                        )
                    }

                    override fun onFailure(

                        webSocket: WebSocket,

                        t: Throwable,

                        response: Response?

                    ) {

                        connectionStatus =
                            "Error"

                        messages.add(
                            "⚠️ ${t.message}"
                        )
                    }
                }
            )
    }

    fun disconnect() {

        webSocket?.close(
            1000,
            "Closed by user"
        )
    }

    fun sendMessage() {

        if (
            messageInput.isNotBlank()
        ) {

            webSocket?.send(
                messageInput
            )

            messages.add(
                "➡️ $messageInput"
            )

            messageInput = ""
        }
    }
}