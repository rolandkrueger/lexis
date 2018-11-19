package org.jlexis.vocabulary.terms

import org.jlexis.vocabulary.terms.TermInput.TermCanonicalInput

/**
 * Sealed class for defining the set of available [Term] classes.
 *
 * @param input The term data given either as a [TermInput.TermUserInput] or as a [TermInput.TermCanonicalInput].
 */
sealed class AbstractTerm(input: TermInput) : Term {

    private val _canonicalRepresentation: String = input.toCanonicalInput()
    private val _userInput: String = input.toUserInput()
    private val _displayString: String = input.toDisplayString()

    override fun getUserInput(): String {
        return _userInput
    }

    override fun getCanonicalRepresentation(): String {
        return _canonicalRepresentation
    }

    override fun getDisplayString(): String {
        return _displayString
    }

    /**
     * Represents a regular term with no special handling of word stems. If the user adds any of the special characters
     * for [WordStemTerm]s to a regular term, then these will not get any special treatment but are left untouched instead.
     */
    open class RegularTerm(input: TermInput) : AbstractTerm(input) {
        /**
         * For a [RegularTerm], the complete user input is interpreted as the word stem.
         */
        override fun getWordStem(): String = getUserInput()
    }

    /**
     * A word stem term is used to define the word stem needed by an [InflectedTerm]. If no special character is used
     * for the word stem term data, the complete user input is used as the word stem as returned by [getWordStem]. The
     * following two additional variants are available to define a word stem:
     *
     * * Use the pipe symbol | to mark the end of the word stem. The word stem is then the prefix of the term data up
     * until the pipe symbol.
     * * If the word stem is a substring of the term input, it can be enclosed by < and >. For example, the term input
     * '(to) &lt;under&gt;go' defines the word stem 'under'.
     */
    class WordStemTerm(input: TermInput) : AbstractTerm(input) {
        override fun getWordStem(): String {
            val wordStemStrings = MarkerItems.MARKER_MAP[MarkerItems.WORD_STEM_MARKER]!!
            val wordStemBeginStrings = MarkerItems.MARKER_MAP[MarkerItems.WORD_STEM_MARKER_BEGIN]!!
            val wordStemEndStrings = MarkerItems.MARKER_MAP[MarkerItems.WORD_STEM_MARKER_END]!!

            if (getCanonicalRepresentation().contains(wordStemStrings.encodedReplacement)) {
                return getDisplayString().substringBefore(wordStemStrings.displayReplacement)
            }

            if (getCanonicalRepresentation().contains(wordStemBeginStrings.encodedReplacement) && getCanonicalRepresentation().contains(wordStemEndStrings.encodedReplacement)) {
                return getDisplayString().substring(
                        getDisplayString().indexOf(wordStemBeginStrings.displayReplacement) + wordStemBeginStrings.displayReplacement.length,
                        getDisplayString().indexOf(wordStemEndStrings.displayReplacement)
                )
            }

            return getDisplayString()
        }
    }

    /**
     * An inflected term is a term derived from a word stem. Such a term always needs a reference to a [WordStemTerm] instance to be
     * able to resolve its full version with [getResolvedWordStem]. An inflected term can contain the word stem placeholder
     * `--` which will be replaced by the word stem retrieved with [WordStemTerm.getWordStem]. Take, for instance,
     * the following two terms:
     *
     * * WordStemTerm: `klock|an`
     * * InflectedTerm: `--orna`
     *
     * When the inflected term was created using the word stem term, the method [InflectedTerm.getResolvedWordStem]
     * will yield `klockorna`.
     */
    class InflectedTerm(input: TermInput, private val wordStemTerm: WordStemTerm) : AbstractTerm(input) {
        override fun getWordStem(): String = getUserInput()

        override fun getResolvedWordStem(): RegularTerm {
            val wordStemPlaceholder = MarkerItems.MARKER_MAP[MarkerItems.WORD_STEM_PLACEHOLDER]!!
            return RegularTerm(TermCanonicalInput(getCanonicalRepresentation().replace(wordStemPlaceholder.encodedReplacement, wordStemTerm.getWordStem())))
        }
    }

    /**
     * Represents an empty term with no input. Only one singleton instance obtainable with [EmptyTerm.INSTANCE] is
     * available.
     */
    class EmptyTerm private constructor() : RegularTerm(TermCanonicalInput("")) {
        companion object {
            /**
             * Singleton instance of the [EmptyTerm].
             */
            val INSTANCE = EmptyTerm()
        }
    }
}

/**
 * Defines the types of possible input for a [Term].
 *
 * @param input The actual input data. Encoded in its canonical form if the term input is of type [TermCanonicalInput].
 */
sealed class TermInput(val input: String) {

    /**
     * Convert the [TermInput] data into the canonical representation.
     */
    abstract fun toCanonicalInput(): String

    /**
     * Convert the [TermInput] data into the original user input.
     */
    abstract fun toUserInput(): String

    /**
     * Convert the [TermInput] data into the corresponding value for display on the UI.
     */
    fun toDisplayString(): String {
        return toCanonicalInput().replace(MarkerItems.findEncodedReplacementRegex) {
            MarkerItems.encodedReplacementToMarkerStrings[it.value]?.displayReplacement ?: it.value
        }
    }

    /**
     * A [TermInput] used for storing user-provided input data in a [Term].
     *
     * @param input The user-provided term input
     */
    class TermUserInput(input: String) : TermInput(input) {
        override fun toUserInput(): String = input
        override fun toCanonicalInput(): String = input.replace(MarkerItems.findUserDefinedMarkerRegex) {
            MarkerItems.userDefinedMarkerToEncodedReplacements[it.value] ?: it.value
        }
    }

    /**
     * A [TermInput] used for storing input in its canonical form in a [Term]. This kind of data typically comes from the
     * database, since term data is only stored in its canonical form there.
     *
     * @param input The input data represented in the canonical form
     */
    class TermCanonicalInput(input: String) : TermInput(input) {
        override fun toUserInput(): String = input.replace(MarkerItems.findEncodedReplacementRegex) {
            MarkerItems.encodedReplacementToMarkerStrings[it.value]?.userDefinedMarker ?: it.value
        }

        override fun toCanonicalInput(): String = input
    }
}

private object MarkerItems {
    data class MarkerStrings(val regexUserDefinedMarker: String, val userDefinedMarker: String, val regexEncodedReplacement: String, val encodedReplacement: String, val displayReplacement: String)

    const val HASH_SIGN = "HASH_SIGN"
    const val WORD_STEM_MARKER = "WORD_STEM_MARKER"
    const val WORD_STEM_PLACEHOLDER = "WORD_STEM_PLACEHOLDER"
    const val WORD_STEM_MARKER_BEGIN = "WORD_STEM_MARKER_BEGIN"
    const val WORD_STEM_MARKER_END = "WORD_STEM_MARKER_END"

    val MARKER_MAP = mapOf(
            HASH_SIGN to MarkerStrings("#", "#", "#\\{#}", "#{#}", "#"),
            WORD_STEM_PLACEHOLDER to MarkerStrings("--", "--", "#\\{-}", "#{-}", "~"),
            WORD_STEM_MARKER to MarkerStrings("\\|", "|", "#\\{\\|}", "#{|}", "|"),
            WORD_STEM_MARKER_BEGIN to MarkerStrings("<", "<", "#\\{<}", "#{<}", "<"),
            WORD_STEM_MARKER_END to MarkerStrings(">", ">", "#\\{>}", "#{>}", ">")
    )

    val userDefinedMarkerToEncodedReplacements = MARKER_MAP.values.map { it.userDefinedMarker to it.encodedReplacement }.toMap()
    val findUserDefinedMarkerRegex = Regex("(" +
            MARKER_MAP.values
                    .asSequence()
                    .map { it.regexUserDefinedMarker }
                    .joinToString(separator = "|")
            + ")")

    val encodedReplacementToMarkerStrings = MARKER_MAP.values.map { it.encodedReplacement to it }.toMap()
    val findEncodedReplacementRegex = Regex("(" +
            MARKER_MAP.values
                    .asSequence()
                    .map { it.regexEncodedReplacement }
                    .joinToString(separator = "|")
            + ")")

}