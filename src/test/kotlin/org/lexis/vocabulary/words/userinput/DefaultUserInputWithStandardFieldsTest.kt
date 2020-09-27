package org.lexis.vocabulary.words.userinput

import org.junit.jupiter.api.Test
import org.lexis.vocabulary.terms.Term
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DefaultUserInputWithStandardFieldsTest {
    private val input = DefaultUserInputWithStandardFields("prefix")

    @Test
    internal fun testTerm() {
        input.term = Term.fromUserInput("term input")

        expectThat(input) {
            get { term.getUserInput() }.isEqualTo("term input")
        }
    }
}