package co.novu

import co.novu.api.EventTriggerRequest
import co.novu.api.EventsApi
import co.novu.helpers.RetrofitHelper
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class Novu {

    private val eventsApi = RetrofitHelper.getInstance().create(EventsApi::class.java)

    fun trigger(body: EventTriggerRequest) = runBlocking {

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