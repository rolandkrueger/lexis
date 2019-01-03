package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.formatter.dsl.Formatter
import org.jlexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields

class DefaultUserInputWithStandardFieldsFormatter : UserInputFormatter<DefaultUserInputWithStandardFields> {
    override fun toShortRepresentation(input: DefaultUserInputWithStandardFields): String =
            input.term.getDisplayString()

    override fun toFullRepresentation(input: DefaultUserInputWithStandardFields, formatter: Formatter) {
        formatter {
            main {
                print(input.term)
            }

            input.getStandardUserInputFormatter().toFullRepresentation(input, formatter)
        }
    }
}