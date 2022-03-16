package com.sunny.cds.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("crypto_pairs")
data class MongoCurrencyPair(
    @Id
    val id: String,
    val idx: Int,
    val time: Instant,
    val open: Double,
    val close: Double,
    val high: Double,
    val low: Double,
    val volume: Double,
    val base: String,
    val quote: String
)
