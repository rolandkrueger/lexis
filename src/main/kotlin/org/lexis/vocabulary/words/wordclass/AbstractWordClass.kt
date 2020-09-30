package org.lexis.vocabulary.words.wordclass

import org.lexis.vocabulary.words.formatter.UserInputFormatter

abstract class AbstractWordClass<T>(val wordClassEnum: WordClassEnum = WordClassEnum.DEFAULT, val identifier: String) {

    abstract fun createUserInputObject(): T
    abstract fun getUserInputFormatter(): UserInputFormatter<T>

    init {
        if (identifier.isBlank()) {
            throw IllegalArgumentException("identifier must not be blank")
        }
    }
}