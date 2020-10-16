package org.lexis.vocabulary.words.formatter

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.formatter.dsl.impl.MarkdownFormatter
import org.lexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields
import org.junit.jupiter.api.Test
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class DefaultUserInputWithStandardFieldsFormatterTest {

    val formatter = DefaultUserInputWithStandardFieldsFormatter()
    val input = DefaultUserInputWithStandardFields(UserInputDataImpl(), "test")
    val markdownFormatter = MarkdownFormatter()

    @Test
    fun toShortRepresentation() {
        input.term = Term.fromUserInput("test term")
        val result = formatter.toShortRepresentation(input)

        expectThat(result).isEqualTo("test term")
    }

    @Test
    fun toFullRepresentation() {
        input.term = Term.fromUserInput("test term")
        input.phonetics = Term.fromUserInput("test term phonetics")
        input.example = Term.fromUserInput("test example")
        input.description = Term.fromUserInput("test description")

        formatter.toFullRepresentation(input, markdownFormatter)
        expectThat(markdownFormatter.asString()).isEqualTo("""
            **test term** [test term phonetics]

            ---

            **Description:** test description

            **Example:** test example


        """.trimIndent())
    }
}
