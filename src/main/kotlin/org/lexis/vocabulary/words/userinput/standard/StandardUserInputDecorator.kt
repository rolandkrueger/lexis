package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.formatter.StandardUserInputFormatter
import org.lexis.vocabulary.words.userinput.RegisteredTermKey
import org.lexis.vocabulary.words.userinput.UserInput
import org.lexis.vocabulary.words.userinput.UserInputTermPropertyDelegate

open class StandardUserInputDecorator<T : UserInput>(val delegatedUserInput: T, keyPrefix: String) : UserInput by delegatedUserInput {
    val descriptionKey = RegisteredTermKey("$keyPrefix.description")
    val exampleKey = RegisteredTermKey("$keyPrefix.example")
    val phoneticsKey = RegisteredTermKey("$keyPrefix.phonetics")

    var description: Term by UserInputTermPropertyDelegate(descriptionKey)
    var example: Term by UserInputTermPropertyDelegate(exampleKey)
    var phonetics: Term by UserInputTermPropertyDelegate(phoneticsKey)

    init {
        listOf(descriptionKey, exampleKey, phoneticsKey).forEach {
            registerTermKey(it)
        }
    }

    fun getStandardUserInputFormatter(): StandardUserInputFormatter {
        return StandardUserInputFormatter()
    }
}