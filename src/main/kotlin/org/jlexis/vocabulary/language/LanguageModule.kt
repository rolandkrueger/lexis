package org.jlexis.vocabulary.language

import org.jlexis.quiz.abbreviation.AbbreviationAlternatives
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass
import org.jlexis.vocabulary.words.wordclass.WordClassEnum

abstract class LanguageModule(val targetLanguage: Language,
                              var description: String = "",
                              var iconId: String = "",
                              val isDefault: Boolean = false) {

    private val wordClasses: MutableMap<WordClassEnum, AbstractWordClass> = HashMap()

    protected fun registerWordClass(wordClass: AbstractWordClass) {
        wordClasses[wordClass.wordClassEnum] = wordClass
    }

    fun getWordClass(wordClassEnum: WordClassEnum) = wordClasses[wordClassEnum] ?: wordClasses[WordClassEnum.DEFAULT]

    abstract fun getAbbreviationAlternatives() : AbbreviationAlternatives
}