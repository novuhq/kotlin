package co.novu.dto.response

import co.novu.dto.Channel
import co.novu.dto.Subscriber
import java.math.BigInteger

class SubscriberResponse(
    subscriberId: String,
    firstName: String? = null,
    lastName: String? = null,
    email: String? = null,
    phone: String? = null,
    avatar: String? = null,
    var _id: String,
    var _organizationId: String,
    var _environmentId: String,
    var deleted: Boolean,
    var createdAt: String,
    var updatedAt: String,
    val channels: List<Channel>? = null,
    var __v: BigInteger
) : Subscriber(
    subscriberId,
    firstName,
    lastName,
    email,
    phone,
    avatar
)


