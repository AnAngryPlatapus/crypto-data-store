package com.sunny.cds.config

import com.google.gson.Gson
import com.sunny.cds.client.MarketDataHandler
import com.sunny.cds.model.book.*
import com.sunny.cds.repository.BookPublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import reactor.netty.http.client.WebsocketClientSpec
import java.net.URI

@Configuration
class WebsocketConf(
    @Value("\${bitfinex.stream.hostname}")
    private val bitfinexStream: String,
    @Value("\${gemini.stream.hostname}")
    private val geminiStream: String,
    @Value("\${gemini.stream.btc.hostname}")
    private val geminiBtcStream: String,
    private val bookPublisher: BookPublisher,
    private val g: Gson,
) {

    private fun client(): ReactorNettyWebSocketClient {
        return ReactorNettyWebSocketClient(
            HttpClient.create(),
            WebsocketClientSpec.builder().maxFramePayloadLength(1000000)
        )
    }

    @Bean
    fun bitfinexListener(): Mono<Void> {
        val subBitBook = mapOf(
            "event" to "subscribe",
            "channel" to "book",
            "symbol" to "tBTCUSD",
            "prec" to "P0",
            "LEN" to "250"
        )
        return client()
            .execute(
                URI(bitfinexStream), MarketDataHandler(
                    Book::class,
                    {
                        Bitfinex(g.fromJson(it, BitfinexWrapper::class.java), "BTCUSD").toData()
                    },
                    bookPublisher,
                    subBitBook
                )
            )
    }

    @Bean
    fun geminiBookListener(): Mono<Void> {
        val subGemBook = mapOf(
            "type" to "subscribe",
            "subscriptions" to arrayOf(
                mapOf(
                    "name" to "l2",
                    "symbols" to arrayOf("BTCUSD")
                )
            )
        )
        //TODO autowire configured Gson
        val g = Gson()
        return client().execute(
            URI(geminiStream), MarketDataHandler(
                Book::class,
                {
                    val gem = Gemini(g.fromJson(it, GeminiItemWrapper::class.java))
                    gem.toData()?: emptyList()
                },
                bookPublisher,
                subGemBook
            )
        )
    }
}


