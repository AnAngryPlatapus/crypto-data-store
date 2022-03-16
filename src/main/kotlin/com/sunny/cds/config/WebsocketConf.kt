package com.sunny.cds.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.sunny.cds.client.DatabasePublisher
import com.sunny.cds.client.MarketDataHandler
import com.sunny.cds.model.book.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
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
    private val bookPublisher: BookPublisher,
    private val objectMapper: ObjectMapper
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
                        Bitfinex(this.objectMapper.readValue(it, BitfinexWrapper::class.java), "BTCUSD").toData()
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

@Service
class BookPublisher: DatabasePublisher<Book>()

