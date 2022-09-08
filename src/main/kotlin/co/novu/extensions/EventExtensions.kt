package co.novu.extensions

import co.novu.Novu
import co.novu.dto.request.BroadcastEventRequest
import co.novu.dto.request.TriggerEventRequest

import kotlinx.coroutines.runBlocking
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}

fun Novu.trigger(body: TriggerEventRequest) = runBlocking {
    eventsApi.triggerEvent(body)
        .body()
        .apply { logger.info { this } }
}

fun Novu.broadcast(body: BroadcastEventRequest) = runBlocking {
    eventsApi.broadcastEvent(body)
        .body()
        .apply { logger.info { this } }
}

fun Novu.cancelTriggerEvent(transactionId: String) = runBlocking {
    eventsApi.cancelTriggerEvent(transactionId)
        .body()
        .apply { logger.info { this } }
}
