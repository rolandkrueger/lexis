package org.jlexis.languagemodule.swedish.formatter

import org.jlexis.languagemodule.swedish.userinput.SwedishNounUserInput
import org.jlexis.vocabulary.words.formatter.UserInputFormatter

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
}