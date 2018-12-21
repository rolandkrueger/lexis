package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.userinput.UserInput

interface UserInputFormatter<T : UserInput> {
    fun toShortRepresentation(input : T) : String
}