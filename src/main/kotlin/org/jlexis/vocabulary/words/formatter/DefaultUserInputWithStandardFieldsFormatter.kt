package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields

class DefaultUserInputWithStandardFieldsFormatter : UserInputFormatter<DefaultUserInputWithStandardFields> {
    override fun toShortRepresentation(input: DefaultUserInputWithStandardFields): String =
            input.term.getDisplayString()
}