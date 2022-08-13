package com.nicetohave.songbook

import com.nicetohave.songbook.core.modules.AndroidAppModule
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import org.koin.ksp.generated.module
import org.koin.test.KoinTest
import org.koin.test.check.checkModules


class MyFirstTestClass : FunSpec({

    test("my first test") {
        1 + 2 shouldBe 3
    }

})

class CheckModulesTest : ShouldSpec(), KoinTest {

    init {
        context("[Koin Modules]") {
            should("check all modules") {
                // Assert

                checkModules {
                    properties(mapOf("network_info_host" to "https://1.1.1.1"))
                    modules(AndroidAppModule().module)
                }
            }
        }
    }

}