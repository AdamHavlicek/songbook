package com.nicetohave.songbook.core.exception

import com.nicetohave.songbook.core.exception.ServerFailure as tFailure
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

class FailureTests : ShouldSpec({

    context("[toString]") {
        should("return string containing [message] property") {
            // Arrange
            val message = "test message"
            val className = "ServerFailure"
            // Act
            val result = tFailure(message)
            // Assert
            result.message shouldBe message
            result.toString() shouldContain message
            result.toString() shouldContain className
        }
    }
})