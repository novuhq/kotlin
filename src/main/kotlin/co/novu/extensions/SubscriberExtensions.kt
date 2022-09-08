package co.novu.extensions

import co.novu.Novu
import co.novu.dto.request.SubscriberRequest
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import java.math.BigInteger

private val logger = KotlinLogging.logger {}

fun Novu.subscribers(page: BigInteger? = null) = runBlocking {
    subscribersApi.getSubscribers(page)
        .body()
        .apply { logger.info { this } }
}


fun Novu.createSubscriber(subscriberRequest: SubscriberRequest) = runBlocking {
    subscribersApi.createSubscriber(subscriberRequest)
        .body()
        .apply { logger.info { this } }

}