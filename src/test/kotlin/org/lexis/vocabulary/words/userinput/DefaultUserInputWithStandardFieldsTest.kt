package org.lexis.vocabulary.words.userinput

import org.junit.jupiter.api.Test
import org.lexis.tests.assertions.userInputEquals
import org.lexis.vocabulary.terms.Term
import strikt.api.expectThat

internal class DefaultUserInputWithStandardFieldsTest {
    private val input = DefaultUserInputWithStandardFields(keyPrefix = "prefix")

    @Test
    internal fun testTerm() {
        input.term = Term.fromUserInput("term input")

        expectThat(input.term).userInputEquals("term input")
    }
}