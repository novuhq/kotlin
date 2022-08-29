package co.novu.api


import co.novu.Novu
import co.novu.dto.request.BroadcastEventRequest
import co.novu.dto.request.Subscriber
import co.novu.dto.request.TriggerEventRequest
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank

val APIKEY: String = System.getProperty("APIKEY")
const val EXISTING_CHANNEL = "fdfsdf"
const val NON_EXISTING_CHANNEL = "non_existing_channel"

class EventsApiTest : DescribeSpec({

    describe("Trigger Event Tests") {

        it("Trigger event to subscriberId") {
            Novu(APIKEY).trigger(TriggerEventRequest(name = EXISTING_CHANNEL, to = "subid", payload = mapOf()))
                .run {
                    this?.data?.acknowledged shouldBe true
                    this?.data?.status shouldBe "processed"
                    this?.data?.transactionId.shouldNotBeBlank()
                }
        }

        it("Trigger event to subscriberId to non existing channel") {
            Novu(APIKEY).trigger(TriggerEventRequest(name = NON_EXISTING_CHANNEL, to = "subid", payload = mapOf()))
                .run {
                    this?.data?.acknowledged shouldBe true
                    this?.data?.status shouldBe "template_not_found"
                    this?.data?.transactionId.shouldBeNull()
                }
        }

        it("Trigger event to list of subscriberId") {
            Novu(APIKEY).trigger(
                TriggerEventRequest(name = EXISTING_CHANNEL, to = listOf("sub1", "sub2"), payload = mapOf())
            ).run {
                this?.data?.status shouldBe "processed"
            }

        }

        it("Trigger event to subscriberDTO") {
            Novu(APIKEY).trigger(
                TriggerEventRequest(
                    name = EXISTING_CHANNEL,
                    to = Subscriber(subscriberId = "id", firstName = "test", lastName = "test"),
                    payload = mapOf()
                )
            ).run {
                this?.data?.status shouldBe "processed"
            }
        }
    }


    describe("Broadcast Event Tests") {


        it("Broadcast event") {
            Novu(APIKEY).broadcast(
                BroadcastEventRequest(name = EXISTING_CHANNEL, payload = mapOf())
            ).run {
                this?.data?.status shouldBe "processed"
            }


        }
    }


})