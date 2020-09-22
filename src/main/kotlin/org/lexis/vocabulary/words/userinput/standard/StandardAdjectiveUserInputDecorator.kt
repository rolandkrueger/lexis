package org.lexis.vocabulary.words.userinput.standard

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.*

open class StandardAdjectiveUserInputDecorator<T : UserInput>(val delegatedUserInput: T, keyPrefix: String) : UserInput by delegatedUserInput {
    val positiveKey = RegisteredTermKey("$keyPrefix.adjective.positive")
    val comparativeKey = RegisteredTermKey("$keyPrefix.adjective.comparative")
    val superlativeKey = RegisteredTermKey("$keyPrefix.adjective.superlative")
    val isNotComparableKey = RegisteredDataKey("$keyPrefix.adjective.is_not_comparable")
    val isIrregularKey = RegisteredDataKey("$keyPrefix.adjective.is_irregular")

    var positive: Term by UserInputTermPropertyDelegate(positiveKey)
    var comparative: Term by UserInputTermPropertyDelegate(comparativeKey)
    var superlative: Term by UserInputTermPropertyDelegate(superlativeKey)

    var isNotComparable: Boolean? by FlagPropertyDelegate(isNotComparableKey)
    var isIrregular: Boolean? by FlagPropertyDelegate(isIrregularKey)

    init {
        listOf(positiveKey, comparativeKey, superlativeKey).forEach { registerTermKey(it) }
        listOf(isNotComparableKey, isIrregularKey).forEach { registerFlagKey(it) }

        delegatedUserInput.connectInflectedTermWithWordStem(comparativeKey, positiveKey)
        delegatedUserInput.connectInflectedTermWithWordStem(superlativeKey, positiveKey)
    }

    fun getResolvedComparative() = getResolvedInflectedTerm(comparativeKey)
    fun getResolvedSuperlative() = getResolvedInflectedTerm(superlativeKey)
}