package org.lexis.languagemodule.swedish.formatter

import org.lexis.languagemodule.swedish.userinput.SwedishNounUserInput
import org.lexis.vocabulary.words.formatter.UserInputFormatter
import org.lexis.vocabulary.words.formatter.dsl.Formatter

class SwedishNounUserInputFormatter : UserInputFormatter<SwedishNounUserInput> {
    override fun toShortRepresentation(input: SwedishNounUserInput): String {
        return with(input) {
            if (indefiniteSingular.isEmpty()) {
                return ""
            }

            if (definiteSingular.isEmpty() && indefiniteSingular.isEmpty()) {
                return indefiniteSingular.getDisplayString()
            }

            "${indefiniteSingular.getDisplayString()}, ${definiteSingular.defaultIfEmpty()}, ${indefinitePlural.defaultIfEmpty()}"
        }
    }

    override fun toFullRepresentation(input: SwedishNounUserInput, formatter: Formatter) {
        formatter {
            main {
                whenGiven(input.indefiniteSingular) {
                    print(input.indefiniteSingular)
                }

                whenGiven(input.definiteSingular) {
                    comma()
                    print(input.definiteSingular)
                }

                whenGiven(input.indefinitePlural) {
                    comma()
                    print(input.indefinitePlural)
                }

                whenGiven(input.definitePlural) {
                    comma()
                    print(input.definitePlural)
                }
            }
        }

        input.getStandardUserInputFormatter().toFullRepresentation(input, formatter)
    }
}