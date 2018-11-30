package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.EmptyTerm
import org.jlexis.vocabulary.terms.Term
import org.jlexis.vocabulary.terms.TermInput.TermUserInput

inline class RegisteredTermKey(val key: String) {
    fun validated(): RegisteredTermKey = if (key.isBlank()) throw IllegalArgumentException("term key must not be blank") else this
}

typealias RegisteredWordStemTermKey = RegisteredTermKey
typealias RegisteredInflectedTermKey = RegisteredTermKey

abstract class AbstractUserInput {

    private val terms by lazy { HashMap<RegisteredTermKey, Term>() }
    private val registeredTermKeys by lazy { HashSet<RegisteredTermKey>(8) }
    private val wordStemsForInflectedTerms by lazy { HashMap<RegisteredInflectedTermKey, RegisteredWordStemTermKey>(5) }

    private fun HashMap<RegisteredTermKey, Term>.getOrDefaultToEmpty(key: RegisteredTermKey): Term {
        return this.getOrDefault(key, EmptyTerm)
    }

    protected fun removeTerm(key: RegisteredTermKey) {
        validateTermKey(key)
        terms.remove(key)
    }

    protected fun addUserInput(key: RegisteredTermKey, input: String) {
        validateTermKey(key)
        if (input.isBlank()) {
            removeTerm(key)
        } else {
            terms[key] = Term(TermUserInput(input))
        }
    }

    protected fun getTerm(key: RegisteredTermKey): Term {
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

    fun isEmpty(): Boolean = terms.values.any { it.isEmpty() }

    protected fun isTermEmpty(key: RegisteredTermKey) : Boolean = terms.getOrDefaultToEmpty(key).isEmpty()

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
}


