package co.novu

import co.novu.api.EventTriggerRequest
import co.novu.api.EventsApi
import co.novu.helpers.RetrofitHelper
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class Novu(
    private val apiKey: String
) {

    private val eventsApi = RetrofitHelper.getInstance(apiKey).create(EventsApi::class.java)

    fun trigger(name: String, body: EventTriggerRequest) = runBlocking {

        body.name = name

        logger.info { body }
        eventsApi.eventTrigger(body)
            .body()
            .apply { logger.info { this } }
    }

    fun triggerBroadcast(body: EventTriggerRequest) = runBlocking {

        logger.info { body }
        eventsApi.eventTriggerBroadcast(body)
            .body()
            .apply { logger.info { this } }
    }

}