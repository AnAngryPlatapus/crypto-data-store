package com.sunny.cds.model.book

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sunny.cds.model.Convertable

data class Bitfinex(
    val bitfinexWrapper: BitfinexWrapper,
    val symbol: String,
): Convertable {

    override fun toData(): List<Book> {
        return bitfinexWrapper.items.map {
            Book.default(
                "l2",
                it[0],
                it[2],
                "BFX",
                symbol
            )
        }
    }

}

@JsonDeserialize(using = BifinexDeserializer::class)
data class BitfinexWrapper(
    val channelId: Double,
    val items: List<List<Double>>)  {
    constructor(): this(0.0, emptyList())
}

class BifinexDeserializer: JsonDeserializer<BitfinexWrapper>() {

    override fun deserialize(p: JsonParser?, ctx: DeserializationContext?): BitfinexWrapper {
        val oc = p!!.codec
        val node = oc.readTree<JsonNode>(p)
        if (node.isArray) {
            val channelId = node[0].asDouble()
            val bookItemOrList = node[1]
            if (!bookItemOrList.isNull && !bookItemOrList.isTextual && bookItemOrList[0].isArray) {
                val bookItems: List<List<Double>> =
                    jacksonObjectMapper().convertValue(bookItemOrList, object : TypeReference<List<List<Double>>>() {})
                return BitfinexWrapper(channelId, bookItems)
            } else if (!bookItemOrList.isTextual) {
                //TODO fix this to do something with the data
                return BitfinexWrapper(channelId, emptyList())
            }
        }
        return BitfinexWrapper()
    }
}
