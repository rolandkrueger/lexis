package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.terms.Term
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue

internal class DefaultUserInputTest {

    private val input: DefaultUserInput = DefaultUserInputImpl()

    @Test
    fun `Add a term and read it again`() {
        input.term = Term.fromUserInput("test term")

        expectThat(input) {
            get { term.getUserInput() }.isEqualTo("test term")
        }
    }

    @Test
    fun `isEmpty() for an empty user input`() {
        expectThat(input.isEmpty()).isTrue()
    }

    @Test
    fun `isEmpty() for a non-empty user input`() {
        input.term = Term.fromUserInput("test term")
        expectThat(input.isEmpty()).isFalse()
    }
}