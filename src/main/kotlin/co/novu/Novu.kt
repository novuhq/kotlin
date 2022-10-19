package co.novu


import co.novu.api.EventsApi
import co.novu.api.SubscribersApi
import co.novu.dto.request.BroadcastEventRequest
import co.novu.dto.request.TriggerEventRequest
import co.novu.dto.response.TriggerResponse
import co.novu.helpers.RetrofitHelper
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging


private val logger = KotlinLogging.logger {}

data class NovuConfig(var backendUrl: String = "https://api.novu.co/")


class Novu(
    apiKey: String,
    config: NovuConfig = NovuConfig()
) {

    private val eventsApi =
        RetrofitHelper(apiKey = apiKey, baseUrl = config.backendUrl).getInstance().create(EventsApi::class.java)

    internal val subscribersApi =
        RetrofitHelper(apiKey = apiKey, baseUrl = config.backendUrl).getInstance().create(SubscribersApi::class.java)


    fun trigger(body: TriggerEventRequest) = runBlocking {
        eventsApi.triggerEvent(body)
            .body()
            .apply { logger.info { this } }
    }

    fun broadcast(body: BroadcastEventRequest) = runBlocking {
        eventsApi.broadcastEvent(body)
            .body()
            .apply { logger.info { this } }
    }

    fun cancelTriggerEvent(transactionId: String) = runBlocking {
        eventsApi.cancelTriggerEvent(transactionId)
            .body()
            .apply { logger.info { this } }
    }
}

