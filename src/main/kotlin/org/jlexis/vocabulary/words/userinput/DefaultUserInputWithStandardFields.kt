package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class DefaultUserInputWithStandardFields(keyPrefix: String) : StandardUserInputDecorator<DefaultUserInput>(DefaultUserInput(), keyPrefix) {
    var term by DelegatedTermPropertyDelegate(DefaultUserInput.DEFAULT_TERM_KEY)
}