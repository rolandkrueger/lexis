package org.lexis.vocabulary.words.formatter

import org.lexis.vocabulary.words.formatter.dsl.Formatter
import org.lexis.vocabulary.words.formatter.dsl.FormatterImplementor
import org.lexis.vocabulary.words.userinput.UserInput

interface UserInputFormatter<T : UserInput> {
    fun toShortRepresentation(input: T): String

    fun toFullRepresentation(input: T, formatter: Formatter)
}