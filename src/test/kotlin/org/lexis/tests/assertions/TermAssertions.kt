package org.lexis.tests.assertions

import org.lexis.vocabulary.terms.Term
import strikt.api.Assertion

fun <T : Term> Assertion.Builder<T>.isEmpty(): Assertion.Builder<T> =
        assert("is empty") {
            if (it.isEmpty()) {
                pass()
            } else {
                fail(it, "term $it is not empty")
            }
        }

fun <T : Term> Assertion.Builder<T>.userInputEquals(expectedValue: String): Assertion.Builder<T> =
        assert("user input equals '$expectedValue'") {
            if (it.getUserInput() == expectedValue) {
                pass()
            } else {
                fail(it.getUserInput(), "in fact it is `%s`")
            }
        }

fun <T : Term> Assertion.Builder<T>.displayStringEquals(expectedValue: String): Assertion.Builder<T> =
        assert("display String equals '$expectedValue'") {
            if (it.getDisplayString() == expectedValue) {
                pass()
            } else {
                fail(it.getDisplayString(), "in fact it is `%s`")
            }
        }

fun <T : Term> Assertion.Builder<T>.canonicalRepresentationEquals(expectedValue: String): Assertion.Builder<T> =
        assert("canonical representation equals '$expectedValue'") {
            if (it.getCanonicalRepresentation() == expectedValue) {
                pass()
            } else {
                fail(it.getCanonicalRepresentation(), "in fact it is `%s`")
            }
        }

fun <T : Term> Assertion.Builder<T>.wordStemEquals(expectedValue: String): Assertion.Builder<T> =
        assert("word equals '$expectedValue'") {
            if (it.getWordStem() == expectedValue) {
                pass()
            } else {
                fail(it.getWordStem(), "in fact it is `%s`")
            }
        }

