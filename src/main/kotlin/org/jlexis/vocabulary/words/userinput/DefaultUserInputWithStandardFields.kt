package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class DefaultUserInputWithStandardFields(keyPrefix: String) : StandardUserInputDecorator<DefaultUserInput>(DefaultUserInput(), keyPrefix) {
    val term by DelegatedTermPropertyDelegate(DefaultUserInput.DEFAULT_TERM_KEY, delegatedUserInput.term)
}