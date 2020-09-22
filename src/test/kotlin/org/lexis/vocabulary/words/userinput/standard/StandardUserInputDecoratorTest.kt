package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.DefaultUserInput
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class StandardUserInputDecoratorTest {
    val standardUserInput = StandardUserInputDecorator(DefaultUserInput(), "default")

    @Test
    fun `test all terms`() {
        standardUserInput.delegatedUserInput.term = Term.fromUserInput("term")
        standardUserInput.description = Term.fromUserInput("description")

        expectThat(standardUserInput) {
            get { delegatedUserInput.term }.isEqualTo(Term.fromUserInput("term"))
            get { description }.isEqualTo(Term.fromUserInput("description"))
        }
    }
}