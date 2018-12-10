package org.jlexis.vocabulary.words.userinput.standard

import org.jlexis.vocabulary.terms.Term
import org.jlexis.vocabulary.words.userinput.RegisteredTermKey
import org.jlexis.vocabulary.words.userinput.UserInput
import org.jlexis.vocabulary.words.userinput.UserInputImpl
import org.jlexis.vocabulary.words.userinput.UserInputTermPropertyDelegate

open class StandardUserInputDecorator<T : UserInput>(val delegatedUserInput: T, keyPrefix: String) : UserInput by delegatedUserInput {
    val descriptionKey = RegisteredTermKey("$keyPrefix.DESCRIPTION")
    val exampleKey = RegisteredTermKey("$keyPrefix.EXAMPLE")
    val phoneticsKey = RegisteredTermKey("$keyPrefix.PHONETICS")
    val pronunciationHintKey = RegisteredTermKey("$keyPrefix.PRONUNCIATION_HINT")

    var description: Term by UserInputTermPropertyDelegate(descriptionKey)
    var example: Term by UserInputTermPropertyDelegate(exampleKey)
    var phonetics: Term by UserInputTermPropertyDelegate(phoneticsKey)
    var pronunciationHint: Term by UserInputTermPropertyDelegate(pronunciationHintKey)

    init {
        listOf(descriptionKey, exampleKey, phoneticsKey, pronunciationHintKey).forEach {
            registerTermKey(it)
        }
    }
}