package org.lexis.languagemodule.swedish.userinput

import org.lexis.data.HasValues
import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.RegisteredTermKey
import org.lexis.vocabulary.words.userinput.TermPropertyDelegate
import org.lexis.vocabulary.words.userinput.UserInputData
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import org.lexis.vocabulary.words.userinput.standard.StandardAdjectiveUserInputDecorator
import org.lexis.vocabulary.words.userinput.standard.StandardAdjectiveUserInputDecoratorImpl
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecorator
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecoratorImpl

class SwedishAdjectiveUserInput(private val userInputData: UserInputData = UserInputDataImpl()) :
        StandardAdjectiveUserInputDecorator by StandardAdjectiveUserInputDecoratorImpl(userInputData, "swedish"),
        StandardUserInputDecorator by StandardUserInputDecoratorImpl("swedish", userInputData),
        HasValues by userInputData {

    private val neutrumKey = RegisteredTermKey("swedish.adjective.neutrum")
    private val pluralKey = RegisteredTermKey("swedish.adjective.plural")

    var neutrum: Term by TermPropertyDelegate(userInputData, neutrumKey)
    var plural: Term by TermPropertyDelegate(userInputData, pluralKey)

    init {
        listOf(neutrumKey, pluralKey).forEach { userInputData.registerTermKey(it) }

        userInputData.connectInflectedTermWithWordStem(neutrumKey, positiveKey)
        userInputData.connectInflectedTermWithWordStem(pluralKey, positiveKey)
    }

    fun getResolvedNeutrum() = userInputData.getResolvedInflectedTerm(neutrumKey)
    fun getResolvedPlural() = userInputData.getResolvedInflectedTerm(pluralKey)

//    fun getStandardUserInputFormatter() = userInput.getStandardUserInputFormatter()
}