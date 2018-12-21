package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.userinput.DefaultUserInput

class DefaultUserInputFormatter : UserInputFormatter<DefaultUserInput> {
    override fun toShortRepresentation(input: DefaultUserInput): String = input.term.getDisplayString()
}