package org.jlexis.languagemodule.swedish.formatter

import org.jlexis.languagemodule.swedish.userinput.SwedishAdjectiveUserInput
import org.jlexis.vocabulary.words.formatter.UserInputFormatter

class SwedishAdjectiveUserInputFormatter : UserInputFormatter<SwedishAdjectiveUserInput> {
    override fun toShortRepresentation(input: SwedishAdjectiveUserInput): String {
        return with(input) {
            listOf(positive.getDisplayString(),
                    getResolvedComparative().getDisplayString(),
                    getResolvedSuperlative().getDisplayString())
                    .joinToString()
        }
    }
}