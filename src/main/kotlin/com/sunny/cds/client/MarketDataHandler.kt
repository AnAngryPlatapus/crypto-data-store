package com.sunny.cds.client

import com.google.gson.Gson
import com.sunny.cds.model.BaseMarketData
import com.sunny.cds.util.loggerDelegate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import kotlin.reflect.KClass


@Transactional
class DatabasePublisher<U: BaseMarketData> {

    @Autowired
    private lateinit var reactiveMongoTemplate: ReactiveMongoTemplate

    fun publishAll(marketData: List<U>, clazz: KClass<U>): Flux<U> {
        return reactiveMongoTemplate.insert(marketData, clazz.java)
    }
}

class MarketDataHandler<T: BaseMarketData> (
    val clazz: KClass<T>,
    val deserializer: (json: String) -> List<T>,
    private val databasePublisher: DatabasePublisher<T>,
    val subscriptionMessage: Map<String, Any>
) : WebSocketHandler {


    val g = Gson()
    val log by loggerDelegate

    override fun handle(session: WebSocketSession): Mono<Void> {
        session.send(Mono.just(session.textMessage(g.toJson(subscriptionMessage)))).subscribe()

        return session.receive().map {
            log.debug(it.payloadAsText)
            println(it.payloadAsText)
            deserializer(it.payloadAsText)
            }.flatMap {
                databasePublisher.publishAll(it, clazz)
            }.then()
    }
}

