package com.sunny.cds.repository

import com.sunny.cds.model.MongoCurrencyPair
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface MongoCurrencyRepository: ReactiveMongoRepository<MongoCurrencyPair, String> {
    fun findByQuoteAndIdxBetween(quote: String, startIndex: Int, endIndex: Int): Flux<MongoCurrencyPair>
}