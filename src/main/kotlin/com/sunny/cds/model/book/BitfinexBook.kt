package com.sunny.cds.model.book

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sunny.cds.model.Convertable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

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

@Component
class BifinexDeserializer: JsonDeserializer<BitfinexWrapper>() {

    @Autowired
    private lateinit var g: Gson

    private inline fun <reified T> Gson.fromJson(json: String) = fromJson<T>(json, object: TypeToken<T>() {}.type)


    override fun deserialize(p: JsonParser?, ctx: DeserializationContext?): BitfinexWrapper {

        val oc = p!!.codec
        val node = oc.readTree<JsonNode>(p)
        if (node.isArray) {
            val channelId = node[0].asDouble()
            val bookItemOrList = node[1]
            val isValidOrder = !bookItemOrList.isNull && !bookItemOrList.isTextual && bookItemOrList[0].isArray
            if (isValidOrder) {
                val bookItems: List<List<Double>> = Gson().fromJson<List<List<Double>>>(bookItemOrList.textValue())
                return BitfinexWrapper(channelId, bookItems)
            } else if (!bookItemOrList.isTextual) {
                //TODO fix this to do something with the data
                return BitfinexWrapper(channelId, emptyList())
            }
        }
            return BitfinexWrapper()
    }
}

