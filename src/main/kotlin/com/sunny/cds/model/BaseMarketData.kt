package com.sunny.cds.model

interface BaseMarketData: Convertable {
    val milliTimestamp: Long
    val nanoTimestamp: Long
    val exchange: String
    val symbol: String
}