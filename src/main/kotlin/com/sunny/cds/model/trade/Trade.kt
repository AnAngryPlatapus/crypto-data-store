package com.sunny.cds.model.trade

import com.sunny.cds.model.BaseMarketData
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

//TODO finish this for order book state maintenance algorithm

@Document
data class Trade (
    @Id
    var id: String?,
    val type: String,

    override val nanoTimestamp: Long,
    override val milliTimestamp: Long,
    override val exchange: String,
    override val symbol: String
        ): BaseMarketData {
            companion object Factory {

            }

    override fun toData(): List<BaseMarketData>? {
        return null
    }
}