package co.novu.api


import co.novu.Novu
import co.novu.dto.request.BroadcastEventRequest
import co.novu.dto.request.SubscriberRequest
import co.novu.dto.request.TriggerEventRequest
import co.novu.extensions.broadcast
import co.novu.extensions.cancelTriggerEvent
import co.novu.extensions.trigger
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank


class EventsApiTest : DescribeSpec({

    describe("Trigger Event Tests") {

        it("Should trigger an event to subscriberId") {

            Novu(APIKEY).trigger(TriggerEventRequest(name = EXISTING_CHANNEL, to = "subid", payload = mapOf()))
                .run {
                    this?.data?.acknowledged shouldBe true
                    this?.data?.status shouldBe "processed"
                    this?.data?.transactionId.shouldNotBeBlank()
                }
        }

        it("Should trigger an event to subscriberId with specified transactionId") {
            Novu(APIKEY).trigger(
                TriggerEventRequest(
                    name = EXISTING_CHANNEL,
                    to = "subid", payload = mapOf(),
                    transactionId = TRANSACTION_ID
                )
            )
                .run {
                    this?.data?.acknowledged shouldBe true
                    this?.data?.status shouldBe "processed"
                    this?.data?.transactionId.shouldNotBeBlank()
                    this?.data?.transactionId shouldBe TRANSACTION_ID
                }
        }

        it("Should trigger an event to subscriberId to non existing channel") {
            Novu(APIKEY).trigger(TriggerEventRequest(name = NON_EXISTING_CHANNEL, to = "subid", payload = mapOf()))
                .run {
                    this?.data?.acknowledged shouldBe true
                    this?.data?.status shouldBe "template_not_found"
                    this?.data?.transactionId.shouldBeNull()
                }
        }

        it("Should trigger an event to list of subscriberId") {
            Novu(APIKEY).trigger(
                TriggerEventRequest(name = EXISTING_CHANNEL, to = listOf("sub1", "sub2"), payload = mapOf())
            ).run {
                this?.data?.status shouldBe "processed"
            }

        }

        it("Should trigger an event to subscriberDTO") {
            Novu(APIKEY).trigger(
                TriggerEventRequest(
                    name = EXISTING_CHANNEL,
                    to = SubscriberRequest(subscriberId = "id", firstName = "test", lastName = "test"),
                    payload = mapOf()
                )
            ).run {
                this?.data?.acknowledged shouldBe true
                this?.data?.status shouldBe "processed"
            }
        }
    }


    describe("Broadcast Event Tests") {

        it("Should broadcast an event") {
            Novu(APIKEY).broadcast(
                BroadcastEventRequest(name = EXISTING_CHANNEL, payload = mapOf())
            ).run {
                this?.data?.status shouldBe "processed"
                this?.data?.acknowledged shouldBe true
            }


        }
    }


    describe("Cancel event trigger test") {
        it("Should cancel an event trigger") {
            Novu(APIKEY).cancelTriggerEvent(TRANSACTION_ID)
                .run {
                    this?.data shouldBe false
                }
        }
    }


})