package co.novu.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


data class EventTriggerRequest(
    var name: String? = "",
    var to: Map<String, Any>? = mapOf("subscriberId" to "ciao"),
    var payload: Map<String, Any>? = mapOf(),
    var overrides: Map<String, Any>? = mapOf(),

    )

data class EventTriggerResponse(
    var data: Map<String, Any>? = mapOf(),
)


interface EventsApi {

    @POST("events/trigger")
    suspend fun eventTrigger(@Body body: EventTriggerRequest): Response<EventTriggerResponse>

    @POST("events/trigger/broadcast")
    suspend fun eventTriggerBroadcast(@Body body: EventTriggerRequest): Response<EventTriggerResponse>

}