package co.novu


import co.novu.api.SubscriberDTO
import co.novu.dto.request.BroadcastEventRequest

import co.novu.dto.request.TriggerEventRequest
import kotlin.system.exitProcess


fun main() {
    val apiKey = "7ce3f39fe50967f68ab36f775ed1e79f"


    Novu(apiKey).cancelTriggerEvent(
        "test"
    )


   /* Novu(apiKey).broadcast(
        BroadcastEventRequest(name = "", payload = mapOf())
    )





    Novu(apiKey).trigger(
        TriggerEventRequest(name = "fdfsdf", to = listOf("ciccio"), payload = mapOf())
    )


    Novu(apiKey).trigger(
        TriggerEventRequest(
            name = "fdfsdf",
            to = SubscriberDTO(firstName = "ciao", subscriberId = "sdsada"),
            payload = mapOf()
        )
    )


    Novu(apiKey).trigger(
        TriggerEventRequest(
            name = "fdfsdf",
            to = listOf(SubscriberDTO(firstName = "ciao", subscriberId = "sdsada")),
            payload = mapOf()
        )
    )

*/



    exitProcess(0)
}