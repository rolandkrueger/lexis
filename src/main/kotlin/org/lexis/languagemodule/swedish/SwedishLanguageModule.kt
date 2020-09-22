package org.lexis.languagemodule.swedish

import org.lexis.languagemodule.swedish.wordclasses.SwedishAdjectiveWordClass
import org.lexis.languagemodule.swedish.wordclasses.SwedishDefaultWordClass
import org.lexis.quiz.abbreviation.AbbreviationAlternatives
import org.lexis.vocabulary.language.Language
import org.lexis.vocabulary.language.LanguageModule
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