package com.sunny.cds.web

import com.sunny.cds.model.MongoCurrencyPair
import com.sunny.cds.repository.MongoCurrencyRepository
import com.sunny.cds.util.Profiled
import com.sunny.cds.util.loggerDelegate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/currency")
class CurrencyResource (
    private val mongoCurrencyRepository: MongoCurrencyRepository,
//    private val twitterClient: TwitterClient,
    private val bitfinexListener: Mono<Void>,
    private val geminiBookListener: Mono<Void>
) {

    private val log by loggerDelegate

    @GetMapping("/test")
    fun test(): Mono<Any> {
        log.info("we hit the test!")
        return Mono.just(mapOf("message" to "Waddup dawgie"))
    }


    @Profiled
    @GetMapping("/pairs", params = ["quote", "start", "end"])
    fun pairs(quote: String, start: Long, end: Long): Flux<MongoCurrencyPair> {
        return mongoCurrencyRepository.findByQuoteAndIdxBetween(quote, 0, 1000000)
    }

    @GetMapping("/book")
    fun book(): Mono<Void> {
        return bitfinexListener
    }

    @GetMapping("/g/book")
    fun gBook(): Mono<Void> {
        return geminiBookListener
    }

//    @GetMapping("/news")
//    fun news(): Mono<List<Any>> {
//        return alpacaClient.get().uri("$alpacaDataHostname/v1beta1/news")
//            .exchangeToMono {
//                it.bodyToMono<List<Any>>()
//            }
//    }

//    @GetMapping("/tweets")
//    fun tweets(): Mono<Map<String, Serializable>> {
//        return twitterClient.getTweets()
//    }


}