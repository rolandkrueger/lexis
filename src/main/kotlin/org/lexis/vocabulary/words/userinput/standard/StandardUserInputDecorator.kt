package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.formatter.StandardUserInputFormatter
import org.lexis.vocabulary.words.userinput.RegisteredTermKey
import org.lexis.vocabulary.words.userinput.TermPropertyDelegate
import org.lexis.vocabulary.words.userinput.UserInputData

interface StandardUserInputDecorator {
    var description: Term
    var example: Term
    var phonetics: Term

    fun getStandardUserInputFormatter(): StandardUserInputFormatter
}

open class StandardUserInputDecoratorImpl(keyPrefix: String, userInputData: UserInputData) : StandardUserInputDecorator {

    val descriptionKey = RegisteredTermKey("$keyPrefix.description")
    val exampleKey = RegisteredTermKey("$keyPrefix.example")
    val phoneticsKey = RegisteredTermKey("$keyPrefix.phonetics")

    override var description: Term by TermPropertyDelegate(userInputData, descriptionKey)
    override var example: Term by TermPropertyDelegate(userInputData, exampleKey)
    override var phonetics: Term by TermPropertyDelegate(userInputData, phoneticsKey)

    init {
        listOf(descriptionKey, exampleKey, phoneticsKey).forEach {
            userInputData.registerTermKey(it)
        }
    }

    override fun getStandardUserInputFormatter(): StandardUserInputFormatter {
        return StandardUserInputFormatter()
    }
}