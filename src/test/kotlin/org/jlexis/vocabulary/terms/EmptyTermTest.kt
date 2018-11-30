package org.jlexis.vocabulary.terms


internal class EmptyTermTest : TermContract<EmptyTerm> {
    override fun createFromCanonicalInput(input: String): EmptyTerm = createEmptyTerm()
    override fun createFromUserInput(input: String): EmptyTerm = createEmptyTerm()
    override fun createEmptyTerm(): EmptyTerm = EmptyTerm
}