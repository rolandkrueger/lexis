package org.lexis.tests

internal interface Testable<T> {
    fun createValue() : T
}