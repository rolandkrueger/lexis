package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.*

interface StandardAdjectiveUserInputDecorator {
    val positiveKey: RegisteredTermKey
    val comparativeKey: RegisteredTermKey
    val superlativeKey: RegisteredTermKey
    val isNotComparableKey: RegisteredDataKey
    val isIrregularKey: RegisteredDataKey

    var positive: Term
    var comparative: Term
    var superlative: Term

    var isNotComparable: Boolean?
    var isIrregular: Boolean?

    fun getResolvedComparative(): Term
    fun getResolvedSuperlative(): Term
}

class StandardAdjectiveUserInputDecoratorImpl(val userInputData: UserInputData, keyPrefix: String) : StandardAdjectiveUserInputDecorator {
    override val positiveKey = RegisteredTermKey("$keyPrefix.adjective.positive")
    override val comparativeKey = RegisteredTermKey("$keyPrefix.adjective.comparative")
    override val superlativeKey = RegisteredTermKey("$keyPrefix.adjective.superlative")
    override val isNotComparableKey = RegisteredDataKey("$keyPrefix.adjective.is_not_comparable")
    override val isIrregularKey = RegisteredDataKey("$keyPrefix.adjective.is_irregular")

    override var positive: Term by TermPropertyDelegate(userInputData, positiveKey)
    override var comparative: Term by TermPropertyDelegate(userInputData, comparativeKey)
    override var superlative: Term by TermPropertyDelegate(userInputData, superlativeKey)

    override var isNotComparable: Boolean? by FlagPropertyDelegate(userInputData, isNotComparableKey)
    override var isIrregular: Boolean? by FlagPropertyDelegate(userInputData, isIrregularKey)

    init {
        listOf(positiveKey, comparativeKey, superlativeKey).forEach { userInputData.registerTermKey(it) }
        listOf(isNotComparableKey, isIrregularKey).forEach { userInputData.registerFlagKey(it) }

        userInputData.connectInflectedTermWithWordStem(comparativeKey, positiveKey)
        userInputData.connectInflectedTermWithWordStem(superlativeKey, positiveKey)
    }

    override fun getResolvedComparative() = userInputData.getResolvedInflectedTerm(comparativeKey)
    override fun getResolvedSuperlative() = userInputData.getResolvedInflectedTerm(superlativeKey)
}