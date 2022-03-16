package com.sunny.cds.model

interface Convertable {
    fun toData(): List<BaseMarketData>?
}