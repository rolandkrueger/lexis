package org.lexis.vocabulary.words.userinput

import org.lexis.data.HasValues
import org.lexis.vocabulary.terms.Term

interface DefaultUserInput : HasValues {
    var term: Term
}

/**
 * Default user input class which contains exactly one [Term].
 */
class DefaultUserInputImpl(userInputData: UserInputData = UserInputDataImpl()) : DefaultUserInput, HasValues by userInputData {

    companion object {
        val DEFAULT_TERM_KEY = RegisteredTermKey("default.term")
    }

    init {
        userInputData.registerTermKey(DEFAULT_TERM_KEY)
    }

    /**
     * The single [Term] element for this default user input. This term has no
     * predefined meaning and can be used for arbitrary input.
     */
    override var term: Term by TermPropertyDelegate(userInputData, DEFAULT_TERM_KEY)
}