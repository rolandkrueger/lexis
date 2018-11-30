package org.jlexis.vocabulary.terms

import org.junit.jupiter.api.Assumptions.assumeFalse
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue


internal interface TermContract<T : Term> {
    fun createEmptyTerm(): T

    fun createFromUserInput(input: String): T

    fun createFromCanonicalInput(input: String): T

    @Test
    fun `test isEmpty`() {
        val emptyTerm = createEmptyTerm()
        expectThat(emptyTerm.isEmpty())
                .isTrue()
    }

    @Test
    fun `user input is properly transformed`() {
        val term = createFromUserInput("-- # < > |")
        assumeFalse { term is EmptyTerm }

        expectThat(term) {
            get { getCanonicalRepresentation() }.isEqualTo("#{-} #{#} #{<} #{>} #{|}")
            get { getDisplayString() }.isEqualTo("~ # < > |")
        }
    }

    @Test
    fun `canonical input is properly transformed`() {
        val term = createFromCanonicalInput("#{-} #{#} #{<} #{>} #{|}")
        assumeFalse { term is EmptyTerm }

        expectThat(term) {
            get { getUserInput() }.isEqualTo("-- # < > |")
            get { getDisplayString() }.isEqualTo("~ # < > |")
        }
    }
}