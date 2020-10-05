package org.lexis.vocabulary.terms

import org.junit.jupiter.api.Assumptions.assumeFalse
import org.junit.jupiter.api.Test
import org.lexis.tests.assertions.canonicalRepresentationEquals
import org.lexis.tests.assertions.displayStringEquals
import org.lexis.tests.assertions.isEmpty
import org.lexis.tests.assertions.userInputEquals
import strikt.api.expectThat


internal interface TermContract<T : Term> {
    fun createEmptyTerm(): T

    fun createFromUserInput(input: String): T

    fun createFromCanonicalInput(input: String): T

    @Test
    fun `test isEmpty`() {
        val emptyTerm = createEmptyTerm()
        expectThat(emptyTerm).isEmpty()
    }

    @Test
    fun `user input is properly transformed`() {
        val term = createFromUserInput("-- # < > |")
        assumeFalse { term is EmptyTerm }

        expectThat(term)
                .canonicalRepresentationEquals("#{-} #{#} #{<} #{>} #{|}")
                .displayStringEquals("~ # < > |")
    }

    @Test
    fun `canonical input is properly transformed`() {
        val term = createFromCanonicalInput("#{-} #{#} #{<} #{>} #{|}")
        assumeFalse { term is EmptyTerm }

        expectThat(term)
                .userInputEquals("-- # < > |")
                .displayStringEquals("~ # < > |")
    }
}