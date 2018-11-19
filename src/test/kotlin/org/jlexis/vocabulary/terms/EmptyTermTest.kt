package org.jlexis.vocabulary.terms

import org.jlexis.vocabulary.terms.AbstractTerm.EmptyTerm

internal class EmptyTermTest : TermContract<EmptyTerm> {
    override fun createFromCanonicalInput(input: String): EmptyTerm = createEmptyTerm()
    override fun createFromUserInput(input: String): EmptyTerm = createEmptyTerm()
    override fun createEmptyTerm(): EmptyTerm = EmptyTerm.INSTANCE
}