package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.formatter.dsl.Formatter
import org.jlexis.vocabulary.words.formatter.dsl.FormatterImplementor
import org.jlexis.vocabulary.words.userinput.UserInput

interface UserInputFormatter<T : UserInput> {
    fun toShortRepresentation(input: T): String

    fun toFullRepresentation(input: T, formatter: Formatter)
}