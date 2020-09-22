package org.lexis.languagemodule.swedish.userinput

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.DelegatedTermPropertyDelegate
import org.lexis.vocabulary.words.userinput.RegisteredTermKey
import org.lexis.vocabulary.words.userinput.UserInputImpl
import org.lexis.vocabulary.words.userinput.UserInputTermPropertyDelegate
import org.lexis.vocabulary.words.userinput.standard.StandardAdjectiveUserInputDecorator
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class SwedishAdjectiveUserInput :
        StandardAdjectiveUserInputDecorator<StandardUserInputDecorator<UserInputImpl>>(StandardUserInputDecorator(UserInputImpl(), "swedish"), "swedish") {

    val neutrumKey = RegisteredTermKey("swedish.adjective.neutrum")
    val pluralKey = RegisteredTermKey("swedish.adjective.plural")

    var neutrum: Term by UserInputTermPropertyDelegate(neutrumKey)
    var plural: Term by UserInputTermPropertyDelegate(pluralKey)

    var description by DelegatedTermPropertyDelegate(delegatedUserInput.descriptionKey)
    var example by DelegatedTermPropertyDelegate(delegatedUserInput.exampleKey)
    var phonetics by DelegatedTermPropertyDelegate(delegatedUserInput.phoneticsKey)

    init {
        listOf(neutrumKey, pluralKey).forEach { registerTermKey(it) }

        connectInflectedTermWithWordStem(neutrumKey, positiveKey)
        connectInflectedTermWithWordStem(pluralKey, positiveKey)
    }

    fun getResolvedNeutrum() = getResolvedInflectedTerm(neutrumKey)
    fun getResolvedPlural() = getResolvedInflectedTerm(pluralKey)

    fun getStandardUserInputFormatter() = delegatedUserInput.getStandardUserInputFormatter()
}