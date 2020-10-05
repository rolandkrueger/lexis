package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.junit.jupiter.api.Test
import org.lexis.tests.assertions.userInputEquals
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class StandardUserInputDecoratorTest {
    val input = StandardUserInputDecoratorImpl("default", UserInputDataImpl())

    @Test
    fun `test all terms`() {
        input.description = Term.fromUserInput("description")
        input.example = Term.fromUserInput("example")
        input.phonetics = Term.fromUserInput("phonetics")

        expectThat(input) {
            get { description }.userInputEquals("description")
            get { example }.userInputEquals("example")
            get { phonetics }.userInputEquals("phonetics")
        }
    }
}