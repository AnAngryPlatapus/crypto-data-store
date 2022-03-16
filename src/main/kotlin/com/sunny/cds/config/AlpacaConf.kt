//package com.sunny.cds.config
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.reactive.function.client.WebClient
//
//@Configuration
//class AlpacaConf (
//    @Value("\${champ.alpaca.key}")
//    private val alpacaKey: String,
//    @Value("\${champ.alpaca.secret}")
//    private val alpacaSecret: String,
//        ) {
//
//
//    @Bean
//    fun alpacaClient(): WebClient {
//        return WebClient.builder().defaultHeader("APCA-API-KEY-ID", alpacaKey)
//            .defaultHeader("APCA-API-SECRET-KEY", alpacaSecret)
//            .codecs { it.defaultCodecs().maxInMemorySize(16 * 1024 * 1024) }.build()
//    }
//}