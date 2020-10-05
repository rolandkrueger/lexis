package org.lexis.vocabulary.terms

import com.sun.org.glassfish.gmbal.Description
import org.lexis.tests.KotlinParameterizedTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.lexis.tests.assertions.displayStringEquals
import org.lexis.vocabulary.terms.Term.Companion.fromUserInput
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import java.util.stream.Stream

@KotlinParameterizedTest
internal class InflectedTermTest : TermContract<Term> {
    override fun createEmptyTerm(): Term = fromUserInput("")
    override fun createFromUserInput(input: String): Term = fromUserInput(input)
    override fun createFromCanonicalInput(input: String): Term = Term.fromCanonicalInput(input)

    private fun provideTestArguments(): Stream<Arguments> =
            Stream.of(
                    Arguments.of("word", "--stem", "wordstem"),
                    Arguments.of("word", "stem", "stem"),
                    Arguments.of("word|stem", "--inflected", "wordinflected"),
                    Arguments.of("(..) <word>stem test", "some --inflected test", "some wordinflected test")
            )

    @ParameterizedTest
    @MethodSource("provideTestArguments")
    @Description("test proper resolution of inflected terms")
    fun `test proper resolution of inflected terms`(wordStemInput: String, inflectedTermInput: String, expectedDisplayString: String) {
        val inflectedTerm = fromUserInput(inflectedTermInput)

        expectThat(inflectedTerm.getResolvedInflectedTerm(fromUserInput(wordStemInput)))
                .displayStringEquals(expectedDisplayString)
    }
}