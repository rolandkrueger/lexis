package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.EmptyTerm
import org.jlexis.vocabulary.terms.Term

inline class RegisteredDataKey(val key: String) {
    fun validated(): RegisteredDataKey = if (key.isBlank()) throw IllegalArgumentException("data key must not be blank") else this
}

typealias RegisteredWordStemTermKey = RegisteredDataKey
typealias RegisteredInflectedTermKey = RegisteredDataKey
typealias RegisteredTermKey = RegisteredDataKey

abstract class AbstractUserInput() {

    private val terms by lazy { HashMap<RegisteredTermKey, Term>(8) }
    private val registeredTermKeys by lazy { HashSet<RegisteredTermKey>(8) }
    private val wordStemsForInflectedTerms by lazy { HashMap<RegisteredInflectedTermKey, RegisteredWordStemTermKey>(5) }

    private val flagKeys by lazy { HashSet<RegisteredDataKey>(4) }
    private val flags by lazy { HashMap<RegisteredDataKey, Boolean>(4) }

    private val configurationKeys by lazy { HashSet<RegisteredDataKey>(3) }
    private val configuration by lazy { HashMap<RegisteredDataKey, String>(3) }

    private fun HashMap<RegisteredTermKey, Term>.getOrDefaultToEmpty(key: RegisteredTermKey): Term {
        return this.getOrDefault(key, EmptyTerm)
    }

    protected fun removeTerm(key: RegisteredTermKey) {
        validateTermKey(key)
        terms.remove(key)
    }

    fun setTerm(key: RegisteredTermKey, input: Term) {
        validateTermKey(key)
        if (input.isEmpty()) {
            removeTerm(key)
        } else {
            terms[key] = input
        }
    }

    fun getTerm(key: RegisteredTermKey): Term {
        validateTermKey(key)
        return terms.getOrDefaultToEmpty(key)
    }

    protected fun getResolvedInflectedTerm(inflectedTermKey: RegisteredInflectedTermKey): Term {
        validateTermKey(inflectedTermKey)
        val wordStemKey = wordStemsForInflectedTerms[inflectedTermKey]
                ?: throw IllegalArgumentException("Given term key has not been connected with a word stem term.")

        return terms.getOrDefaultToEmpty(inflectedTermKey)
                .getResolvedInflectedTerm(terms.getOrDefaultToEmpty(wordStemKey))
    }

    fun isEmpty(): Boolean = terms.values.all { it.isEmpty() }

    protected fun isTermEmpty(key: RegisteredTermKey): Boolean = terms.getOrDefaultToEmpty(key).isEmpty()

    protected fun connectInflectedTermWithWordStem(inflectedTermKey: RegisteredInflectedTermKey, wordStemKey: RegisteredWordStemTermKey) {
        registerTermKey(inflectedTermKey)
        registerTermKey(wordStemKey)
        wordStemsForInflectedTerms[inflectedTermKey] = wordStemKey
    }

    protected fun registerTermKey(key: RegisteredTermKey) {
        registeredTermKeys.add(key.validated())
    }

    private fun validateTermKey(termKey: RegisteredTermKey) {
        termKey.validated()
        if (!registeredTermKeys.contains(termKey)) {
            throw IllegalArgumentException("Unknown term key: $termKey has not been registered with registerTermKey()")
        }
    }

    fun setFlag(forKey: RegisteredDataKey, flag: Boolean?) {
        validateFlagKey(forKey)
        when {
            flag != null -> flags[forKey] = flag
            else -> flags.remove(forKey)
        }
    }

    fun getFlag(forKey: RegisteredDataKey): Boolean? {
        validateFlagKey(forKey)
        return flags[forKey]
    }

    protected fun registerFlagKey(key: RegisteredDataKey) {
        flagKeys.add(key)
    }

    private fun validateFlagKey(key: RegisteredDataKey) {
        key.validated()
        if (!flagKeys.contains(key)) {
            throw java.lang.IllegalArgumentException("Unknown flag key: $key has not been registered with registerFlagKey()")
        }
    }

    fun setConfiguration(forKey: RegisteredDataKey, value: String?) {
        validateConfigurationKey(forKey)
        when {
            value != null -> configuration[forKey] = value
            else -> configuration.remove(forKey)
        }
    }

    fun getConfiguration(forKey: RegisteredDataKey): String? {
        validateConfigurationKey(forKey)
        return configuration[forKey]
    }

    protected fun registerConfigurationKey(key: RegisteredDataKey) {
        configurationKeys.add(key)

    }

    private fun validateConfigurationKey(key: RegisteredDataKey) {
        key.validated()
        if (!configurationKeys.contains(key)) {
            throw java.lang.IllegalArgumentException("Unknown configuration key: $key has not been registered with registerConfigurationKey()")
        }
    }
}