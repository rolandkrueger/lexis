package org.jlexis.quiz.abbreviation

/**
 * Defines a set of abbreviation alternatives that can be used by a language learner when answering vocabulary quizzes.
 * This is useful to avoid falsely registering a given answer as incorrect when the only difference of the given answer
 * and the expected answer is an abbreviation that is written differently.
 *
 * Let's look at an example. A vocabulary quiz asks for the translation of the German word "*jemanden einladen*" in English.
 * The expected answer as stored in the corresponding [UserInput] object is "*invite sb.*". Now if the user answers this
 * question with "*invite somebody*", this answer would be registered as incorrect, since it doesn't exactly match the data
 * from the [UserInput] object even though the answer is technically correct. Only the abbreviated word *somebody* was
 * written in full.
 *
 * In order to avoid this situation, a set of equivalent abbreviation alternatives can be defined for the English language
 * such as ["somebody", "sb.", "sb", "someone", "so.", "so"]. This set can be added to an [AbbreviationAlternatives] objects with
 * [AbbreviationAlternatives.addSetOfAlternatives]. Another set of equivalent abbreviations can
 */
class AbbreviationAlternatives {
    private val alternatives: MutableSet<Set<String>> = HashSet()

    /**
     * Returns all available sets of abbreviation alternatives as an unmodifiable [Set] of sets.
     */
    fun getAlternatives(): Set<Set<String>> = alternatives

    /**
     * Adds a set of abbreviation alternatives which are equivalent.
     *
     * Example call:
     * ```
     * myAbbreviationAlternatives.addSetOfAlternatives("somebody", "sb.", "sb", "someone", "so.")
     * ```
     */
    fun addSetOfAlternatives(vararg alternatives: String) {
        this.alternatives.add(setOf(*alternatives))
    }
}