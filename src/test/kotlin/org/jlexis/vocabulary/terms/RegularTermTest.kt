package org.jlexis.vocabulary.terms

import org.jlexis.vocabulary.terms.AbstractTerm.RegularTerm
import org.jlexis.vocabulary.terms.TermInput.TermUserInput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class RegularTermTest : TermContract<RegularTerm> {

    override fun createEmptyTerm(): RegularTerm = RegularTerm(TermUserInput(""))
    override fun createFromCanonicalInput(input: String): RegularTerm = RegularTerm(TermInput.TermCanonicalInput(input))
    override fun createFromUserInput(input: String): RegularTerm = RegularTerm(TermUserInput(input))

    @ParameterizedTest
    @ValueSource(strings = arrayOf("word|stem", "wordstem", "<word>stem"))
    @DisplayName("word stem of regular term is just the input")
    fun `word stem of regular term is just the input`(userInput: String) {
        expectThat(RegularTerm(TermUserInput(userInput))) {
            get { getWordStem() }.isEqualTo(userInput)
        }
    }
}