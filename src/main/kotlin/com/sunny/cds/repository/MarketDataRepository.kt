package com.sunny.cds.repository

import com.sunny.cds.model.BaseMarketData
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

@NoRepositoryBean
interface MarketDataRepository<BaseMarketData, String>: ReactiveCrudRepository<BaseMarketData, String> {
    override fun <S : BaseMarketData> save(entity: S): Mono<S>
}