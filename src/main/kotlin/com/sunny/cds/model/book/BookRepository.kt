package com.sunny.cds.model.book

import com.sunny.cds.repository.MarketDataRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface BookRepository: MarketDataRepository<Book, String> {
    fun save(input: List<Book>): Flux<Book>
}