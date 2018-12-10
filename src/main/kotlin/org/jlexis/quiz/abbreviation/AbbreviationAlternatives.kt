package org.jlexis.quiz.abbreviation

class AbbreviationAlternatives {
    private val alternatives: MutableSet<Set<String>> = HashSet()

    fun getAlternatives(): Set<Set<String>> = alternatives

    fun addSetOfAlternatives(vararg alternatives: String) {
        this.alternatives.add(setOf(*alternatives))
    }
}