package co.novu


import co.novu.dto.request.TriggerEventRequest
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe


class MyTest : DescribeSpec({

    describe("Prova") {

        it("test") {
            val res = Novu("7ce3f39fe50967f68ab36f775ed1e79f").trigger(
                TriggerEventRequest(name = "fdfsdf", to = listOf("ciccio"), payload = mapOf())
            )


            res?.data?.status shouldBe "processed"

        }
    }
})