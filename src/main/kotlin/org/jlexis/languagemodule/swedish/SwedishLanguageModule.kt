package org.jlexis.languagemodule.swedish

import org.jlexis.languagemodule.swedish.wordclasses.SwedishAdjectiveWordClass
import org.jlexis.languagemodule.swedish.wordclasses.SwedishDefaultWordClass
import org.jlexis.quiz.abbreviation.AbbreviationAlternatives
import org.jlexis.vocabulary.language.Language
import org.jlexis.vocabulary.language.LanguageModule
import java.util.*

class SwedishLanguageModule : LanguageModule(
        Language(Locale("sv"), "language.swedish")
) {

    init {
        registerWordClass(SwedishDefaultWordClass())
        registerWordClass(SwedishAdjectiveWordClass())
    }

    override fun getAbbreviationAlternatives(): AbbreviationAlternatives {
        val abbreviationAlternatives = AbbreviationAlternatives()

        return with(abbreviationAlternatives) {
            addSetOfAlternatives("n\u00E5gon", "ngn.", "ngn")
            addSetOfAlternatives("n\u00E5got", "ngt.", "ngt")
            addSetOfAlternatives("n\u00E5gra", "nga.", "nga")
            addSetOfAlternatives("n\u00E5gons", "ngns.", "ngns")

            abbreviationAlternatives
        }
    }
}