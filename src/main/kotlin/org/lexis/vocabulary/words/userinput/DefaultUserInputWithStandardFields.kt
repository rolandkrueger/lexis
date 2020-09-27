package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class DefaultUserInputWithStandardFields(keyPrefix: String) : StandardUserInputDecorator<DefaultUserInput>(DefaultUserInput(), keyPrefix) {
    var term by TermPropertyDelegate(DefaultUserInput.DEFAULT_TERM_KEY)
}