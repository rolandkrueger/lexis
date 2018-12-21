package org.jlexis.vocabulary.words

import org.jlexis.vocabulary.language.Language
import org.jlexis.vocabulary.words.userinput.UserInput
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass

class VocabularyWord {
    private val translations = HashMap<Language, Pair<AbstractWordClass<*>, UserInput>>(2)

    fun addTranslation(forLanguage: Language, wordClass: AbstractWordClass<*>) {
        translations[forLanguage] = Pair(wordClass, wordClass.createUserInputObject())
    }

    fun removeTranslation(forLanguage: Language) {
        translations.remove(forLanguage)
    }

    fun hasTranslationFor(language: Language) = translations.containsKey(language)

    fun getUserInput(forLanguage: Language) = translations[forLanguage]?.second

    fun getWordClass(forLanguage: Language) = translations[forLanguage]?.first

    fun isEmpty() = translations.values.all { it.second.isEmpty() }

    fun getLanguages() = translations.keys.toSet()
}