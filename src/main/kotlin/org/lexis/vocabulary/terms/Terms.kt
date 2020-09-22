package org.lexis.vocabulary.terms

import org.lexis.vocabulary.terms.TermInput.TermCanonicalInput

/**
 * A term is a single piece of input for one particular vocabulary word. It is entered by the user when she creates her
 * vocabulary. One such word typically consists of more than one term. For example, a noun can be composed of the
 * singular and plural form, where each form is represented by its own term. Depending on the grammatical structure of a
 * foreign language, a vocabulary word may consist of a lot of terms. For example, an irregular verb in a language such
 * as French or German needs to be declined for every grammatical person and every possible tense in order to completely
 * cover this verb as a vocabulary word.
 *
 * # Mutability
 *
 * A term is immutable.
 *
 * # Word stem handling
 *
 * A term may contain a number of special characters which will be interpreted by the term classes in a particular way.
 * Using these special characters, the user can define part of a term's contents as a *word stem*. This word stem can
 * then be used by a different term to derive an inflected version of the term.
 *
 * Let's look at a simple example for this: the Swedish word for clock is 'klocka', 'the clock' is translated as 'klockan'
 * the plural form is 'klockor', and the definite plural ('the clocks') is 'klockorna'. As you can see, these four
 * versions share the same word stem 'klock'. To create a vocabulary entry for this word, you can make use of this
 * property by defining the following terms:
 *
 * * Singular form: 'klock|a' (mark word stem with the pipe symbol)
 * * Definite singular: '--an' (use -- as word stem placeholder)
 * * Plural: '--or'
 * * Definite plural: '--orna'
 *
 * Here the special character | marks the end of the word stem, while -- serves as a placeholder for the word stem in
 * derived terms.
 *
 * # Inflected terms
 *
 * An inflected term is a term derived from a word stem. Such a term always needs a reference to another [Term] instance to be
 * able to resolve its full version with [getResolvedInflectedTerm]. An inflected term can contain the word stem placeholder
 * `--` which will be replaced by the word stem retrieved with [getWordStem]. Take, for instance,
 * the user input for the following two terms:
 *
 * * Word stem term: `klock|an`
 * * Inflected term: `--orna`
 *
 * When this word stem term is passed to method [getResolvedInflectedTerm] it will yield `klockorna`.
 *
 * # Internal (canonical) representation
 *
 * The special characters described above are masked internally so that they can be stored in the database independently
 * from the concrete display representation that is visible on the UI. If at a later time the characters used by a user
 * to define a word stem or word stem placeholder are changed configuratively, this will not affect any vocabulary data
 * that is already available in the database and was created by using the old marker characters. So, for instance, the
 * pipe symbol is internally represented by the following code: '#{|]' even if it was entered by the user with a new
 * word stem separator such as '/'.
 *
 * This internal representation can be queried with [getCanonicalRepresentation].
 *
 * # Display representation
 *
 * When the vocabulary data is shown on a user interface, the special marker characters can be displayed differently.
 * For example, the word stem placeholder (entered by the user with --) is displayed as a tilde (~).
 *
 * This representation can be queried with [getDisplayString]. The original user input is queried with [getUserInput].
 *
 * @param input The term data given either as a [TermInput.TermUserInput] or as a [TermInput.TermCanonicalInput].
 */
open class Term protected constructor(input: TermInput) {

    private val _canonicalRepresentation: String = input.toCanonicalInput()
    private val _userInput: String = input.toUserInput()
    private val _displayString: String = input.toDisplayString()

    companion object {
        fun fromUserInput(input: String) = Term(TermInput.TermUserInput(input))
        fun fromCanonicalInput(canonicalInput: String) = Term(TermCanonicalInput(canonicalInput))
    }

    /**
     * Returns the term data as originally entered by the user.
     */
    fun getUserInput(): String = _userInput

    /**
     * Returns the canonical representation of this [Term] which is derived from the user input by replacing all control
     * characters with their canonical (invariant) versions. This representation is used to store the term data in the database.
     * By this, the control characters can later be changed through the configuration without invalidating the data stored
     * in the database.
     *
     * For example, the following user input "`(to) <under>go`" will be transformed into its canonical representation
     * "`(to) #{<}under#{>}go`".
     */
    fun getCanonicalRepresentation(): String = _canonicalRepresentation

    /**
     * Returns a representation for this [Term] which will be used to display the term on a user interface. Most notably,
     * the word stem placeholder is represented by a tilde.
     */
    fun getDisplayString(): String = _displayString

    /**
     * Returns true if this [Term] represents the empty string.
     */
    open fun isEmpty(): Boolean = getCanonicalRepresentation().isEmpty()

    fun defaultIfEmpty(defaultValue: String = "-"): String = when {
        isEmpty() -> defaultValue
        else -> getDisplayString()
    }

    /**
     * Extracts a word stem from this term. This word stem can be used by an inflected [Term] to form a new term. The
     * word stem is extracted as follows. If no special character is used for the word stem term data, the complete user
     * input is used as the word stem. The following two variants are available to define a word stem:
     *
     * * Use the pipe symbol | to mark the end of the word stem. The word stem is then the prefix of the term data up
     * until the pipe symbol.
     * * If the word stem is a substring of the term input, it can be enclosed by < and >. For example, the term input
     * '(to) &lt;under&gt;go' defines the word stem 'under'.
     */
    fun getWordStem(): String {
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

    /**
     * Returns the term data where the word stem placeholder `--` is replaced with the word stem. The word stem is
     * retrieved from the given [wordStemTerm]. The word stem placeholder is then replaced with this word stem.
     *
     * @param wordStemTerm A [Term] that provides the word stem which is used to resolve
     * @return A [Term] which consists of the data of this inflected [Term] with the word stem placeholder replaced
     * with the word stem retrieved from the given [wordStemTerm].
     */
    fun getResolvedInflectedTerm(wordStemTerm: Term): Term {
        val wordStemPlaceholder = MarkerItems.MARKER_MAP[MarkerItems.WORD_STEM_PLACEHOLDER]!!
        return Term(TermCanonicalInput(getCanonicalRepresentation().replace(wordStemPlaceholder.encodedReplacement, wordStemTerm.getWordStem())))
    }

    override fun toString(): String {
        return "Term(userInput='$_userInput')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Term

        if (_canonicalRepresentation != other._canonicalRepresentation) return false

        return true
    }

    override fun hashCode(): Int {
        return _canonicalRepresentation.hashCode()
    }
}

/**
 * Singleton object that represents an empty [Term] with no input.
 */
object EmptyTerm : Term(TermCanonicalInput("")) {
    override fun isEmpty() = true
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