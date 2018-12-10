package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.Term

/**
 * Interface for defining the vocabulary input for one particular language.
 */
interface UserInput {
    fun setTerm(key: RegisteredTermKey, input: Term)
    fun getTerm(key: RegisteredTermKey): Term
    fun isEmpty(): Boolean
    fun setFlag(forKey: RegisteredDataKey, flag: Boolean?)
    fun getFlag(forKey: RegisteredDataKey): Boolean?
    fun setConfiguration(forKey: RegisteredDataKey, value: String?)
    fun getConfiguration(forKey: RegisteredDataKey): String?
    fun registerTermKey(key: RegisteredTermKey)
    fun registerFlagKey(key: RegisteredDataKey)
    fun registerConfigurationKey(key: RegisteredDataKey)
    fun getResolvedInflectedTerm(inflectedTermKey: RegisteredInflectedTermKey): Term
    fun removeTerm(key: RegisteredTermKey)
    fun isTermEmpty(key: RegisteredTermKey): Boolean
    fun connectInflectedTermWithWordStem(inflectedTermKey: RegisteredInflectedTermKey, wordStemKey: RegisteredWordStemTermKey)
}