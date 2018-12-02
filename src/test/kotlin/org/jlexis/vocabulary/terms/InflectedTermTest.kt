package org.jlexis.vocabulary.terms

import com.sun.org.glassfish.gmbal.Description
import org.jlexis.tests.KotlinParameterizedTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import java.util.stream.Stream

@KotlinParameterizedTest
internal class InflectedTermTest : TermContract<Term> {
    override fun createEmptyTerm(): Term = Term.fromUserInput("")
    override fun createFromUserInput(input: String): Term = Term.fromUserInput(input)
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
        val inflectedTerm = Term.fromUserInput(inflectedTermInput)

        expectThat(inflectedTerm) {
            get { getResolvedInflectedTerm(Term.fromUserInput(wordStemInput)) }
                    .isA<Term>()
                    .and {
                        get { getDisplayString() }.isEqualTo(expectedDisplayString)
                    }
        }
    }

    @ParameterizedTest
    @ValueSource(strings = arrayOf("foo", "bar"))
    fun `test isBlank() works as expected`(testedValue: String) {
        Assertions.assertFalse(testedValue.isBlank())
    }

    fun provideTestArguments2(): Stream<Arguments> =
            Stream.of(
                    Arguments.of("", 0),
                    Arguments.of("foo", 3),
                    Arguments.of("kotlin", 6)
            )

    @ParameterizedTest
    @MethodSource("provideTestArguments2")
    fun `test length()`(input: String, expectedLength: Int) {
        Assertions.assertEquals(expectedLength, input.length)
    }
}