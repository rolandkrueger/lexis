package org.jlexis.vocabulary.language

import org.jlexis.quiz.abbreviation.AbbreviationAlternatives
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass
import org.jlexis.vocabulary.words.wordclass.WordClassEnum

/**
 * A language module integrates all required classes for one particular foreign (or native) language. It is the central
 * instance from which the complete configuration of a language can be obtained. It provides the following data:
 *
 * * All available word classes (see [WordClassEnum], [AbstractWordClass]).
 * * The set of abbreviation alternatives (see [AbbreviationAlternatives]).
 * * *tbc.*
 *
 * When the user interface is built, vocabulary data is entered or vocabulary quizzes are performed, the language module
 * is queried to provide the necessary language-specific classes for handling and rendering the user input data or
 * creating and verifying vocabulary quizzes.
 *
 * @property[targetLanguage] The [Language] used by this module.
 * @property[description] An optional description for this module.
 * @property[iconId] A resource ID for the icon used by this module. This is typically a flag icon.
 * @property[isDefault] `true` for the default language module. The default module is used by default for languages which
 * don't have an own [LanguageModule] implementation.
 */
abstract class LanguageModule(val targetLanguage: Language,
                              var description: String = "",
                              var iconId: String = "",
                              val isDefault: Boolean = false) {

    private val wordClasses: MutableMap<WordClassEnum, AbstractWordClass> = HashMap()

    /**
     * Adds a word class implementation to the set of available word classes for this module. Language module
     * implementations have to register each of their [AbstractWordClass] implementations to make them available to the
     * application.
     *
     * At least one word class must be defined by a language module to be able to use it: the default word class with
     * enum [WordClassEnum.DEFAULT]. If no word classes are defined for a language module at all, the module cannot be used.
     *
     * @param[wordClass] The word class implementation to be added to this module.
     */
    protected fun registerWordClass(wordClass: AbstractWordClass) {
        wordClasses[wordClass.wordClassEnum] = wordClass
    }

    /**
     * Returns the [AbstractWordClass] implementation for the given [WordClassEnum] if it is available. If no word class
     * implementation has been registered for the given word class enum with [registerWordClass], the default word class
     * is returned. In order to be able to return a default word class, an implementation of [AbstractWordClass] must
     * have been registered for the enum value [WordClassEnum.DEFAULT].
     */
    fun getWordClass(wordClassEnum: WordClassEnum) = wordClasses[wordClassEnum] ?: wordClasses[WordClassEnum.DEFAULT]

    /**
     * Returns the set of available abbreviation alternatives for this language module.
     *
     * @see AbbreviationAlternatives
     */
    abstract fun getAbbreviationAlternatives(): AbbreviationAlternatives
}