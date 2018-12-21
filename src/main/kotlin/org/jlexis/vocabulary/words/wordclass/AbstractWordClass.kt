package org.jlexis.vocabulary.words.wordclass

import org.jlexis.vocabulary.words.formatter.UserInputFormatter
import org.jlexis.vocabulary.words.userinput.UserInput

abstract class AbstractWordClass<T : UserInput>(val wordClassEnum: WordClassEnum = WordClassEnum.DEFAULT, val identifier: String) {

    abstract fun createUserInputObject(): T
    abstract fun getUserInputFormatter(): UserInputFormatter<T>

    init {
        if (identifier.isBlank()) {
            throw IllegalArgumentException("identifier must not be blank")
        }
    }
}