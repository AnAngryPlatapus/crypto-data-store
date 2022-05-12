package com.sunny.cds.model.book

import com.sunny.cds.model.Convertable

data class Gemini(
    val wrapper: GeminiItemWrapper
) : Convertable {
    override fun toData(): List<Book> {
        val metadata = mapOf<String, Any?>(
            "eventId" to wrapper.event_id,
            "timestamp" to wrapper.timestamp,
            "price" to wrapper.price,
            "quantity" to wrapper.quantity,
            "side" to wrapper.side
        )
        return wrapper.changes?.map {
            Book.default(
                "${wrapper.type}_${it[0]}",
                it[1].toDouble(),
                it[2].toDouble(),
                "GEMINI",
                wrapper.symbol?: "NA",
                metadata
            )
            }?: listOf(Book.default(
                "${wrapper.type}_${wrapper.side}",
                wrapper.price?.toDouble()?: 0.0,
                wrapper.quantity?.toDouble()?: 0.0,
                "GEMINI",
                wrapper.symbol?: "HEARTBEAT",
                metadata
            ))
        }
}

data class GeminiItemWrapper(
    val type: String?,
    val symbol: String? = "",
    val event_id: Long?,
    val timestamp: Long?,
    val price: String?,
    val quantity: String?,
    val side: String?,
    val changes: List<List<String>>?,
    val trades: List<Any>?,
    val auction_events: List<Any>?)


