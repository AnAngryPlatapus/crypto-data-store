package com.sunny.cds.model.book

import com.sunny.cds.model.Convertable

data class Gemini(
    val wrapper: GeminiItemWrapper
) : Convertable {
    override fun toData(): List<Book>? {
        return wrapper.changes?.map {
//            TODO make price negative if sell type
            val price = if(it[0] == "buy") it[1].toDouble() else it[1].toDouble() * -1
            Book.default(
                it[0],
                price,
                it[2].toDouble(),
                "GEMINI",
                wrapper.symbol
            )
            }
        }
}

data class GeminiItemWrapper(
    val type: String?,
    val symbol: String = "",
    val changes: List<List<String>>?,
    val trades: List<Any>?,
    val auction_events: List<Any>?)