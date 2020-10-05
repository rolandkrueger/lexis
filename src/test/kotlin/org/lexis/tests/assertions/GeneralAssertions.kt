package org.lexis.tests.assertions

import org.lexis.data.HasValues
import strikt.api.Assertion

fun <T : HasValues> Assertion.Builder<T>.isEmpty() : Assertion.Builder<T> =
        assert("is empty") {
            if (it.isEmpty()) {
                pass()
            } else {
                fail(it)
            }
        }

fun <T : HasValues> Assertion.Builder<T>.isNotEmpty() : Assertion.Builder<T> =
        assert("is empty") {
            if (it.isEmpty()) {
                fail(it)
            } else {
                pass()
            }
        }
