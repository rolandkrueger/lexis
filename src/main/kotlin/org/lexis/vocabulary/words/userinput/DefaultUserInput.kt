package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.terms.Term

/**
 * Default user input class which contains exactly one [Term].
 */
class DefaultUserInput : UserInputImpl() {

    companion object {
        val DEFAULT_TERM_KEY = RegisteredTermKey("default.term")
    }

    init {
        registerTermKey(DEFAULT_TERM_KEY)
    }

    /**
     * The single [Term] element for this default user input. This term has no
     * predefined meaning and can be used for arbitrary input.
     */
    var term: Term by TermPropertyDelegate(DEFAULT_TERM_KEY)
}