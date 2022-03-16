package com.sunny.cds.service

import com.sunny.cds.repository.MongoCurrencyRepository
import com.sunny.cds.util.loggerDelegate
import org.springframework.stereotype.Service

@Service
class MarketDataService(
    private val mongoCurrencyRepository: MongoCurrencyRepository
) {
    private val log by loggerDelegate



//    fun loess_test(): Mono<Double> {
//        val loessInterpolator = LoessInterpolator(0.75, 2)
//        return mongoCurrencyRepository.findByQuoteAndIdxBetween("btc", 0, 50000).map {
//            (it.high - it.low) / 2
//        }.collectList().map {
//            loessInterpolator.smooth(
//                it.toDoubleArray(),
//                (0..50001).map { r -> r.toDouble() }.toDoubleArray())
//        }.map {
//            StatUtils.mean()
//        }
//    }

}