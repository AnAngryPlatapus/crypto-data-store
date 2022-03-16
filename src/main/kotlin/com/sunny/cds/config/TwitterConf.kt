//package com.sunny.cds.config
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.web.reactive.function.client.WebClient
//
//@Configuration
//class TwitterConf (
//    @Value("\${twitter.bearer.token}")
//    private val bearerToken: String
//        ) {
//
//    @Bean
//    fun twitter(): WebClient {
//        return WebClient.builder()
//            .defaultHeaders{headers ->
//                headers.add("Authorization",
//                    "Bearer $bearerToken")
//            }.build()
//    }
//}