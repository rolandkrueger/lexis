package org.jlexis.vocabulary.words.wordclass

import org.jlexis.vocabulary.words.userinput.UserInputImpl
import java.lang.IllegalArgumentException

abstract class AbstractWordClass(val wordClassEnum: WordClassEnum = WordClassEnum.DEFAULT, val identifier: String) {

    abstract fun createUserInputObject(): UserInputImpl

    init {
        if (identifier.isBlank()) {
            throw IllegalArgumentException("identifier must not be blank")
        }
    }
}