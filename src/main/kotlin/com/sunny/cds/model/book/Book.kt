package com.sunny.cds.model.book

import com.sunny.cds.model.BaseMarketData
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Book(
    @Id
    var id: String?,
    val type: String,
    val price: Double,
    val amount: Double,

    override val nanoTimestamp: Long,
    override val milliTimestamp: Long,
    override val exchange: String,
    override val symbol: String
): BaseMarketData {
    companion object Factory {

        private val nt = System.nanoTime()
        private val mt = System.currentTimeMillis()
        fun default(type: String,
                    price: Double = 0.0,
                    amount: Double = 0.0,
                    exchange: String,
                    symbol: String): Book {
            return Book(null,type, price, amount, nt, mt, exchange, symbol)
        }
    }

    override fun toData(): List<BaseMarketData>? {
        TODO("Not yet implemented")
    }
}