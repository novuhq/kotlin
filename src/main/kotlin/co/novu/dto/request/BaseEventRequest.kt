package co.novu.dto.request

abstract class BaseEventRequest {

    // Mandatory fields
    protected lateinit var name: String
    protected var payload: Map<String, Any> = mapOf()


    //Optional fields
    protected var overrides: Map<String, Any>? = null
    protected var transactionId: String? = null

    protected fun init(
        _name: String,
        _payload: Map<String, Any>,
        _overrides: Map<String, Any>? = null,
        _transactionId: String? = null
    ): BaseEventRequest {

        return this.apply {
            this.name = _name
            this.payload = _payload
            this.overrides = _overrides
            this.transactionId = _transactionId
        }
    }

}