package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.terms.EmptyTerm
import org.lexis.vocabulary.terms.Term

/**
 * Class to define a key which uniquely identifies a piece of user input data in a [UserInputData].
 */
inline class RegisteredDataKey(val key: String) {
    /**
     * Validates the key.
     * @throws IllegalArgumentException In case the key is blank
     * @return The key itself if successfully validated
     */
    fun validated(): RegisteredDataKey = if (key.isBlank()) throw IllegalArgumentException("data key must not be blank") else this
}

typealias RegisteredWordStemTermKey = RegisteredDataKey
typealias RegisteredInflectedTermKey = RegisteredDataKey
typealias RegisteredTermKey = RegisteredDataKey

/**
 * Implementation of the [UserInputData] interface.
 */
open class UserInputDataImpl : UserInputData {

    private val terms by lazy { HashMap<RegisteredTermKey, Term>(8) }
    private val registeredTermKeys by lazy { HashSet<RegisteredTermKey>(8) }
    private val wordStemsForInflectedTerms by lazy { HashMap<RegisteredInflectedTermKey, RegisteredWordStemTermKey>(5) }

    private val flagKeys by lazy { HashSet<RegisteredDataKey>(4) }
    private val flags by lazy { HashMap<RegisteredDataKey, Boolean>(4) }

    private val configurationKeys by lazy { HashSet<RegisteredDataKey>(3) }
    private val configuration by lazy { HashMap<RegisteredDataKey, String>(3) }

    /**
     * Extension function for `HashMap<RegisteredTermKey, Term>` which returns an [EmptyTerm] by default in case no [Term] could
     * be found for any given key.
     * @receiver Hash map containing the [Term]s of this user input.
     */
    private fun HashMap<RegisteredTermKey, Term>.getOrDefaultToEmpty(key: RegisteredTermKey): Term {
        return this.getOrDefault(key, EmptyTerm)
    }

    override fun removeTerm(key: RegisteredTermKey) {
        validateTermKey(key)
        terms.remove(key)
    }

    override fun setTerm(key: RegisteredTermKey, input: Term) {
        validateTermKey(key)
        if (input.isEmpty()) {
            removeTerm(key)
        } else {
            terms[key] = input
        }
    }

    override fun getTerm(key: RegisteredTermKey): Term {
        validateTermKey(key)
        return terms.getOrDefaultToEmpty(key)
    }

    override fun getResolvedInflectedTerm(inflectedTermKey: RegisteredInflectedTermKey): Term {
        validateTermKey(inflectedTermKey)
        val wordStemKey = wordStemsForInflectedTerms[inflectedTermKey]
                ?: throw IllegalArgumentException("Given term key has not been connected with a word stem term.")

        return terms.getOrDefaultToEmpty(inflectedTermKey)
                .getResolvedInflectedTerm(terms.getOrDefaultToEmpty(wordStemKey))
    }

    override fun isEmpty(): Boolean = terms.values.all { it.isEmpty() }

    override fun isTermEmpty(key: RegisteredTermKey): Boolean = terms.getOrDefaultToEmpty(key).isEmpty()

    override fun connectInflectedTermWithWordStem(inflectedTermKey: RegisteredInflectedTermKey, wordStemKey: RegisteredWordStemTermKey) {
        registerTermKey(inflectedTermKey)
        registerTermKey(wordStemKey)
        wordStemsForInflectedTerms[inflectedTermKey] = wordStemKey
    }

    override fun registerTermKey(key: RegisteredTermKey) {
        registeredTermKeys.add(key.validated())
    }

    private fun validateTermKey(termKey: RegisteredTermKey) {
        termKey.validated()
        if (!registeredTermKeys.contains(termKey)) {
            throw IllegalArgumentException("Unknown term key: $termKey has not been registered with registerTermKey()")
        }
    }

    override fun setFlag(forKey: RegisteredDataKey, flag: Boolean?) {
        validateFlagKey(forKey)
        when {
            flag != null -> flags[forKey] = flag
            else -> flags.remove(forKey)
        }
    }

    override fun getFlag(forKey: RegisteredDataKey): Boolean? {
        validateFlagKey(forKey)
        return flags[forKey]
    }

    override fun registerFlagKey(key: RegisteredDataKey) {
        flagKeys.add(key)
    }

    private fun validateFlagKey(key: RegisteredDataKey) {
        key.validated()
        if (!flagKeys.contains(key)) {
            throw java.lang.IllegalArgumentException("Unknown flag key: $key has not been registered with registerFlagKey()")
        }
    }

    override fun setConfiguration(forKey: RegisteredDataKey, value: String?) {
        validateConfigurationKey(forKey)
        when {
            value != null -> configuration[forKey] = value
            else -> configuration.remove(forKey)
        }
    }

    override fun getConfiguration(forKey: RegisteredDataKey): String? {
        validateConfigurationKey(forKey)
        return configuration[forKey]
    }

    override fun registerConfigurationKey(key: RegisteredDataKey) {
        configurationKeys.add(key)
    }

    private fun validateConfigurationKey(key: RegisteredDataKey) {
        key.validated()
        if (!configurationKeys.contains(key)) {
            throw java.lang.IllegalArgumentException("Unknown configuration key: $key has not been registered with registerConfigurationKey()")
        }
    }
}