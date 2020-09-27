package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.UserInputImpl
import org.junit.jupiter.api.Test
import org.lexis.vocabulary.words.userinput.TermPropertyDelegate
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class StandardAdjectiveUserInputDecoratorTest {

    private var testInput: TestAdjectiveUserInput = TestAdjectiveUserInput()

    @Test
    fun `use positive and example terms`() {
        testInput.positive = Term.fromUserInput("positive")
        testInput.example = Term.fromUserInput("example")

        expectThat(testInput) {
            get { positive.getUserInput() }.isEqualTo("positive")
            get { delegatedUserInput.example.getUserInput() }.isEqualTo("example")
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

private class TestAdjectiveUserInput :
        StandardAdjectiveUserInputDecorator<StandardUserInputDecorator<UserInputImpl>>(StandardUserInputDecorator(UserInputImpl(), "test"), "test") {

    var example by TermPropertyDelegate(delegatedUserInput.exampleKey)
}