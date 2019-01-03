package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.formatter.dsl.Formatter
import org.jlexis.vocabulary.words.formatter.dsl.FormatterImplementor
import org.jlexis.vocabulary.words.userinput.DefaultUserInput

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