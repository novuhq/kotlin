package co.novu.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.Flow.Subscriber


data class EventTriggerRequest(
    var name: String,
    var to: EventTriggerTo,
    var payload: Map<String, Any> = mapOf(),
    var overrides: Map<String, Any>? = mapOf(),
    var transactionId: String? = null
)

data class EventTriggerResponse(
    var acknowledged: Boolean,
    var status: String,
    var transactionId: String?
)


data class SubscriberDTO(
    private var subscriberId: String,
    private var firstName: String? = null,
    private var lastName: String? = null,
    private var email: String? = null,
    private var phone: String? = null,
    private var avatar: String? = null
)

data class ListSubscribers(
    private var subscribers: List<SubscriberDTO>
)

class EventTriggerTo {

    private var subscriberId: String? = null
    private var subscriberIds: List<String>? = null
    private var subscriber: SubscriberDTO? = null
    private var subscribers: ListSubscribers? = null

    constructor(_subscriber: SubscriberDTO) {
        subscriber = _subscriber
    }

    constructor(_subscribers: ListSubscribers) {
        subscribers = _subscribers
    }

    constructor(_subscriberId: String) {
        subscriberId = _subscriberId
    }

    constructor(_subscriberIds: List<String>) {
        subscriberIds = _subscriberIds
    }


}


interface EventsApi {

    @POST("events/trigger")
    suspend fun eventTrigger(@Body body: EventTriggerRequest): Response<EventTriggerResponse>

    @POST("events/trigger/broadcast")
    suspend fun eventTriggerBroadcast(@Body body: EventTriggerRequest): Response<EventTriggerResponse>

}