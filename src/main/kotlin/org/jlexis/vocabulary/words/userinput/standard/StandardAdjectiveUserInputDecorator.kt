package org.jlexis.vocabulary.words.userinput.standard

import org.jlexis.vocabulary.terms.Term
import org.jlexis.vocabulary.words.userinput.*

open class StandardAdjectiveUserInputDecorator<T : UserInput>(val delegatedUserInput: T, keyPrefix: String) : UserInput by delegatedUserInput {
    val positiveKey = RegisteredTermKey("$keyPrefix.ADJECTIVE_POSITIVE")
    val comparativeKey = RegisteredTermKey("$keyPrefix.ADJECTIVE_COMPARATIVE")
    val superlativeKey = RegisteredTermKey("$keyPrefix.ADJECTIVE_SUPERLATIVE")
    val isNotComparableKey = RegisteredDataKey("$keyPrefix.ADJECTIVE_IS_NOT_COMPARABLE")
    val isIrregularKey = RegisteredDataKey("$keyPrefix.ADJECTIVE_IS_IRREGULAR")

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