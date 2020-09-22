package org.lexis.vocabulary.words.formatter

import org.lexis.vocabulary.words.formatter.dsl.Formatter
import org.lexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields

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