package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import org.junit.jupiter.api.Test
import org.lexis.vocabulary.words.userinput.UserInputData
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class StandardAdjectiveUserInputDecoratorTest {

    private var testInput: TestAdjectiveUserInput = TestAdjectiveUserInput(UserInputDataImpl())

    @Test
    fun `use positive and example terms`() {
        testInput.positive = Term.fromUserInput("positive")
        testInput.example = Term.fromUserInput("example")

        expectThat(testInput) {
            get { positive.getUserInput() }.isEqualTo("positive")
            get { example.getUserInput() }.isEqualTo("example")
        }
    }

    @Test
    fun `resolve comparative and superlative with word stem`() {
        testInput.positive = Term.fromUserInput("schön")
        testInput.comparative = Term.fromUserInput("--er")
        testInput.superlative = Term.fromUserInput("am --sten")

        expectThat(testInput) {
            get { getResolvedComparative().getUserInput() }.isEqualTo("schöner")
            get { getResolvedSuperlative().getUserInput() }.isEqualTo("am schönsten")
        }
    }
}

private class TestAdjectiveUserInput(userInputData : UserInputData) : StandardUserInputDecorator by StandardUserInputDecoratorImpl("test", userInputData),
        StandardAdjectiveUserInputDecorator by StandardAdjectiveUserInputDecoratorImpl(userInputData, "test")