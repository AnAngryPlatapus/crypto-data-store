//package com.sunny.cds.client
//
//import org.springframework.stereotype.Service
//import org.springframework.web.reactive.function.client.WebClient
//import org.springframework.web.reactive.function.client.bodyToMono
//import reactor.core.publisher.Mono
//
//@Service
//class TwitterClient(
//    private val twitter: WebClient
//) {
//
//    fun getTweets(): Mono<List<String>> {
//        return twitter.get().uri("https://api.twitter.com/1.1/search/tweets.json?q=nasa&result_type=popular")
//            .retrieve()
//            .bodyToMono()
//    }
//}