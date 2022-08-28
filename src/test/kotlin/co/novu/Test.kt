package co.novu


import co.novu.dto.request.TriggerEventRequest
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

val APIKEY: String = System.getProperty("APIKEY")

class MyTest : DescribeSpec({

    describe("Prova") {

        it("test") {
            val res = Novu(APIKEY).trigger(
                TriggerEventRequest(name = "fdfsdf", to = listOf("ciccio"), payload = mapOf())
            )


            res?.data?.status shouldBe "processed"

        }
    }
})