package co.novu

import co.novu.api.EventTriggerRequest


fun main() {
    val apiKey = "7ce3f39fe50967f68ab36f775ed1e79f"

    Novu(apiKey).trigger(name = "fdfsdf", EventTriggerRequest())
}