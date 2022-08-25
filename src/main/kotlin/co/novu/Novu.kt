package co.novu

import co.novu.api.EventTriggerRequest
import co.novu.api.EventsApi
import co.novu.helpers.RetrofitHelper
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}


data class NovuConfig(var backendUrl: String = "https://api.novu.co/v1/")

class Novu(
    apiKey: String,
    config: NovuConfig = NovuConfig()
) {


    private val eventsApi =
        RetrofitHelper(apiKey = apiKey, baseUrl = config.backendUrl).getInstance().create(EventsApi::class.java)

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