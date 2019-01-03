package org.jlexis.languagemodule.swedish.formatter

import org.jlexis.languagemodule.swedish.userinput.SwedishAdjectiveUserInput
import org.jlexis.vocabulary.words.formatter.UserInputFormatter
import org.jlexis.vocabulary.words.formatter.dsl.Formatter

class SwedishAdjectiveUserInputFormatter : UserInputFormatter<SwedishAdjectiveUserInput> {
    override fun toShortRepresentation(input: SwedishAdjectiveUserInput): String {
        return with(input) {
            listOf(positive.getDisplayString(),
                    getResolvedComparative().getDisplayString(),
                    getResolvedSuperlative().getDisplayString())
                    .joinToString()
        }
    }

    override fun toFullRepresentation(input: SwedishAdjectiveUserInput, formatter: Formatter) {
        formatter {
            main {
                print(input.positive)

                whenAnyGiven(setOf(input.neutrum, input.plural)) {
                    parenthesize {
                        join(listOf(input.neutrum, input.plural))
                    }
                }

                whenGiven(input.comparative) {
                    comma()
                    print(input.comparative)
                }

                whenGiven(input.superlative) {
                    comma()
                    print(input.superlative)
                }
            }
        }

        input.getStandardUserInputFormatter().toFullRepresentation(input.delegatedUserInput, formatter)
    }
}