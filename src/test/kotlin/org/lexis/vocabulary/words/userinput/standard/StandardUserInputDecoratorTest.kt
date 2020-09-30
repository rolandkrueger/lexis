package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.junit.jupiter.api.Test
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class StandardUserInputDecoratorTest {
    val standardUserInput = StandardUserInputDecoratorImpl("default", UserInputDataImpl())

    @Test
    fun `test all terms`() {
        standardUserInput.description = Term.fromUserInput("description")

        expectThat(standardUserInput) {
            get { description }.isEqualTo(Term.fromUserInput("description"))
        }
    }
}