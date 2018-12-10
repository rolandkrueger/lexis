package org.jlexis.vocabulary.words.wordclass

import org.jlexis.vocabulary.words.userinput.UserInput

abstract class AbstractWordClass(val wordClassEnum: WordClassEnum = WordClassEnum.DEFAULT, val identifier: String) {

    abstract fun createUserInputObject(): UserInput

    init {
        if (identifier.isBlank()) {
            throw IllegalArgumentException("identifier must not be blank")
        }
    }
}