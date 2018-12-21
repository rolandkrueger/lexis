package org.jlexis.languagemodule.swedish.userinput

import org.jlexis.vocabulary.terms.Term
import org.jlexis.vocabulary.words.userinput.DelegatedTermPropertyDelegate
import org.jlexis.vocabulary.words.userinput.RegisteredTermKey
import org.jlexis.vocabulary.words.userinput.UserInputImpl
import org.jlexis.vocabulary.words.userinput.UserInputTermPropertyDelegate
import org.jlexis.vocabulary.words.userinput.standard.StandardAdjectiveUserInputDecorator
import org.jlexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class SwedishAdjectiveUserInput :
        StandardAdjectiveUserInputDecorator<StandardUserInputDecorator<UserInputImpl>>(StandardUserInputDecorator(UserInputImpl(), "swedish"), "swedish") {

    val neutrumKey = RegisteredTermKey("swedish.adjective.neutrum")
    val pluralKey = RegisteredTermKey("swedish.adjective.plural")

    var neutrum: Term by UserInputTermPropertyDelegate(neutrumKey)
    var plural: Term by UserInputTermPropertyDelegate(pluralKey)

    var description by DelegatedTermPropertyDelegate(delegatedUserInput.descriptionKey, delegatedUserInput.description)
    var example by DelegatedTermPropertyDelegate(delegatedUserInput.exampleKey, delegatedUserInput.example)
    var phonetics by DelegatedTermPropertyDelegate(delegatedUserInput.phoneticsKey, delegatedUserInput.phonetics)

    init {
        listOf(neutrumKey, pluralKey).forEach { registerTermKey(it) }

        connectInflectedTermWithWordStem(neutrumKey, positiveKey)
        connectInflectedTermWithWordStem(pluralKey, positiveKey)
    }

    fun getResolvedNeutrum() = getResolvedInflectedTerm(neutrumKey)
    fun getResolvedPlural() = getResolvedInflectedTerm(pluralKey)
}