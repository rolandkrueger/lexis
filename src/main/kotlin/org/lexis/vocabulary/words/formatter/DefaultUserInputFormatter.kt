package org.lexis.vocabulary.words.formatter

import org.lexis.vocabulary.words.formatter.dsl.Formatter
import org.lexis.vocabulary.words.formatter.dsl.FormatterImplementor
import org.lexis.vocabulary.words.userinput.DefaultUserInput

class DefaultUserInputFormatter : UserInputFormatter<DefaultUserInput> {
    override fun toShortRepresentation(input: DefaultUserInput): String = input.term.getDisplayString()

    override fun toFullRepresentation(input: DefaultUserInput, formatter: Formatter) {
        formatter {
            main {
                print (input.term)
            }
        }
    }
}