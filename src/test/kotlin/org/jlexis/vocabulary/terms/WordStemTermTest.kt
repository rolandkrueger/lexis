package org.jlexis.vocabulary.terms

import com.sun.org.glassfish.gmbal.Description
import org.jlexis.tests.KotlinParameterizedTest
import org.jlexis.vocabulary.terms.TermInput.TermCanonicalInput
import org.jlexis.vocabulary.terms.TermInput.TermUserInput
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.util.stream.Stream

@KotlinParameterizedTest
internal class WordStemTermTest : TermContract<Term> {
    override fun createEmptyTerm(): Term = Term.fromUserInput("")
    override fun createFromUserInput(input: String): Term = Term.fromUserInput(input)
    override fun createFromCanonicalInput(input: String): Term = Term.fromCanonicalInput(input)

    private fun provideWordStems(): Stream<Arguments> =
            Stream.of(
                    of("no word stem", "no word stem"),
                    of("the <word>stem", "word"),
                    of("word|stem", "word"))

    @ParameterizedTest
    @MethodSource("provideWordStems")
    @Description("`word stem is properly resolved`")
    fun `word stem is properly resolved`(input: String, expectedWordStem: String) {
        val wordStemTerm = createFromUserInput(input)

        expectThat(wordStemTerm) {
            get { getWordStem() }.isEqualTo(expectedWordStem)
        }
    }
}