package co.novu.dto.response

data class TriggerResponse(
    var acknowledged: Boolean,
    var status: String,
    var transactionId: String? = null
)