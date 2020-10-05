package org.lexis.vocabulary.words.userinput

import org.junit.jupiter.api.Test
import org.lexis.tests.assertions.isEmpty
import org.lexis.tests.assertions.isNotEmpty
import org.lexis.tests.assertions.userInputEquals
import org.lexis.vocabulary.terms.Term
import strikt.api.expectThat

internal class DefaultUserInputTest {

    private val input: DefaultUserInput = DefaultUserInputImpl()

    @Test
    fun `Set term and read it again`() {
        input.term = Term.fromUserInput("test term")

        expectThat(input.term).userInputEquals("test term")
    }

    @Test
    fun `isEmpty() for an empty user input`() {
        expectThat(input).isEmpty()
    }

    @Test
    fun `isEmpty() for a non-empty user input`() {
        input.term = Term.fromUserInput("test term")
        expectThat(input).isNotEmpty()
    }
}