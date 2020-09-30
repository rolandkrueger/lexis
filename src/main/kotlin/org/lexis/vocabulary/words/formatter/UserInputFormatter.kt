package org.lexis.vocabulary.words.formatter

import org.lexis.vocabulary.words.formatter.dsl.Formatter

interface UserInputFormatter<T> {
    fun toShortRepresentation(input: T): String

    fun toFullRepresentation(input: T, formatter: Formatter)
}