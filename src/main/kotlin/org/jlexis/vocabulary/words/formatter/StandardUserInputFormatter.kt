package org.jlexis.vocabulary.words.formatter

import org.jlexis.vocabulary.words.formatter.dsl.Formatter
import org.jlexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class StandardUserInputFormatter : UserInputFormatter<StandardUserInputDecorator<*>> {
    override fun toShortRepresentation(input: StandardUserInputDecorator<*>): String = ""

    override fun toFullRepresentation(input: StandardUserInputDecorator<*>, formatter: Formatter) {
        formatter {
            phonetics {
                whenGiven(input.phonetics) {
                    print(input.phonetics)
                }
            }
            extra {
                // TODO: i18n
                whenGiven(input.description) {
                    bold { +"Description:" }
                    println(input.description)
                }

                whenGiven(input.example) {
                    bold { +"Example:" }
                    println(input.example)
                }
            }
        }
    }
}