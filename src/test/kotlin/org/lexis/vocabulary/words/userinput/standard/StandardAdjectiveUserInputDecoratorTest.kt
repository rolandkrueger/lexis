package org.lexis.vocabulary.words.userinput.standard

import org.junit.jupiter.api.Test
import org.lexis.tests.assertions.userInputEquals
import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.UserInputData
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class StandardAdjectiveUserInputDecoratorTest {

    private var testInput: TestAdjectiveUserInput = TestAdjectiveUserInput()

    @Test
    fun `set and read terms`() {
        testInput.positive = Term.fromUserInput("positive")
        testInput.example = Term.fromUserInput("example")
        testInput.description = Term.fromUserInput("description")
        testInput.phonetics = Term.fromUserInput("phonetics")
        testInput.isIrregular = true

        expectThat(testInput) {
            get { positive }.userInputEquals("positive")
            get { example }.userInputEquals("example")
            get { description }.userInputEquals("description")
            get { phonetics }.userInputEquals("phonetics")
            get { isIrregular }.isTrue()
        }
    }

    @Test
    fun `resolve comparative and superlative with word stem`() {
        testInput.positive = Term.fromUserInput("schön")
        testInput.comparative = Term.fromUserInput("--er")
        testInput.superlative = Term.fromUserInput("am --sten")

        expectThat(testInput) {
            get { getResolvedComparative() }.userInputEquals("schöner")
            get { getResolvedSuperlative() }.userInputEquals("am schönsten")
        }
    }
}

private class TestAdjectiveUserInput(userInputData: UserInputData = UserInputDataImpl()) :
        StandardUserInputDecorator by StandardUserInputDecoratorImpl("test", userInputData),
        StandardAdjectiveUserInputDecorator by StandardAdjectiveUserInputDecoratorImpl(userInputData, "test")