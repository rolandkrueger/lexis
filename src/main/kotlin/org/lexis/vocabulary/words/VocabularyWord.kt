package org.lexis.vocabulary.words

import org.lexis.vocabulary.language.Language
import org.lexis.vocabulary.words.wordclass.AbstractWordClass

class VocabularyWord {
    private val translations = HashMap<Language, Pair<AbstractWordClass<*>, Any?>>(2)

    fun addTranslation(forLanguage: Language, wordClass: AbstractWordClass<*>) {
        translations[forLanguage] = Pair(wordClass, wordClass.createUserInputObject())
    }

    fun removeTranslation(forLanguage: Language) {
        translations.remove(forLanguage)
    }

    fun hasTranslationFor(language: Language) = translations.containsKey(language)

    fun getUserInput(forLanguage: Language) = translations[forLanguage]?.second

    fun getWordClass(forLanguage: Language) = translations[forLanguage]?.first

    fun isEmpty() = translations.values.all { it.second == null }

    fun getLanguages() = translations.keys.toSet()
}