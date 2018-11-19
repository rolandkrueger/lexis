package org.jlexis.tests

internal interface Testable<T> {
    fun createValue() : T
}