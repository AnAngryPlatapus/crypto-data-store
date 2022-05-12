package com.sunny.cds.model.book

import com.sunny.cds.model.BaseMarketData
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document()
data class Book(
    @Id
    var id: String?,
    val type: String,
    val price: Double,
    val quantity: Double,

    override val exchange: String,
    override val symbol: String,

    override val nanoTimestamp: Long,
    override val milliTimestamp: Long,

    val metaData: Map<String, Any?>?

    ) : BaseMarketData {
    companion object Factory {

        private val nt = System.nanoTime()
        private val mt = System.currentTimeMillis()
        fun default(
            type: String,
            price: Double = 0.0,
            amount: Double = 0.0,

            exchange: String,
            symbol: String,
            metaData: Map<String, Any?> = emptyMap()
        ): Book {
            return Book(null,
                type, price, amount,
                symbol, exchange,
                nt, mt, metaData)
        }
    }

    override fun toData(): List<BaseMarketData>? {
        TODO("Not yet implemented")
    }
}

