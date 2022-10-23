package co.novu.api

import java.util.*


data class TestVariables(
    val APIKEY: String = System.getProperty("APIKEY"),
    val EXISTING_CHANNEL: String = "fdfsdf",
    val NON_EXISTING_CHANNEL: String = "non_existing_channel",
    var TRANSACTION_ID: String = UUID.randomUUID().toString()
)