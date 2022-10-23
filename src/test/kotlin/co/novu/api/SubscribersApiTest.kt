package co.novu.api

import co.novu.Novu

import co.novu.dto.request.SubscriberRequest
import co.novu.dto.response.SubscriberResponse
import co.novu.extensions.createSubscriber
import co.novu.extensions.subscribers
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.types.shouldBeInstanceOf
import java.math.BigInteger
import java.util.UUID


class SubscribersApiTest : DescribeSpec({

    describe("Get List of subscribers") {

        it("Should get the list of subscribers") {

            Novu(TestVariables().APIKEY).subscribers().run {
                this?.page.shouldBeInstanceOf<BigInteger>()
                this?.totalCount.shouldBeInstanceOf<BigInteger>()
                this?.pageSize.shouldBeInstanceOf<BigInteger>()
                this?.data?.forEach { it.shouldBeInstanceOf<SubscriberResponse>() }
            }

        }
    }

    describe("Create subscriber") {
        it("Should create a subscriber") {

            Novu(TestVariables().APIKEY).createSubscriber(
                subscriberRequest = SubscriberRequest(
                    subscriberId = UUID.randomUUID().toString(),
                    firstName = "A",
                    lastName = "B"
                )
            )
        }
    }
})
