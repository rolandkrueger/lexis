package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.Term

class DefaultUserInput() : UserInputImpl() {
    companion object {
        val DEFAULT_TERM_KEY = RegisteredTermKey("DEFAULT_TERM")
    }

    var term : Term by UserInputTermPropertyDelegate(DEFAULT_TERM_KEY)

    init {
        registerTermKey(DEFAULT_TERM_KEY)
    }
}