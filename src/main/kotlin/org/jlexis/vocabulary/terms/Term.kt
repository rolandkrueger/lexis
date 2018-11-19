package org.jlexis.vocabulary.terms

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
 * derived terms. See the description of the classes implementing the [Term] interface for more details on this.
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
 * @see AbstractTerm.RegularTerm
 * @see AbstractTerm.WordStemTerm
 * @see AbstractTerm.InflectedTerm
 * @see AbstractTerm.EmptyTerm
 */
interface Term {

    /**
     * Returns true if this [Term] represents the empty string.
     */
    fun isEmpty(): Boolean = getCanonicalRepresentation().isEmpty()

    /**
     * Returns the term data where the word stem placeholder is replaced with the word stem. This method simply returns
     * `this` by default except for [InflectedTerm][AbstractTerm.InflectedTerm]s. For an [InflectedTerm][AbstractTerm.InflectedTerm]
     * the word stem is retrieved from the corresponding [WordStemTerm][AbstractTerm.WordStemTerm]. The word stem
     * placeholder is then replaced with this word stem.
     */
    fun getResolvedWordStem(): Term = this

    /**
     * Returns the canonical representation of this [Term] which is derived from the user input by replacing all control
     * characters with their canonical (invariant) versions. This representation is used to store the term data in the database.
     * By this, the control characters can later be changed through the configuration without invalidating the data stored
     * in the database.
     *
     * For example, the following user input "`(to) <under>go`" will be transformed into its canonical representation
     * "`(to) #{<}under#{>}go`".
     */
    fun getCanonicalRepresentation(): String

    /**
     * Returns a representation for this [Term] which will be used to display the term on a user interface. Most notably,
     * the word stem placeholder is represented by a tilde.
     */
    fun getDisplayString(): String

    /**
     * Extracts a word stem from this term. This word stem can be used by an [InflectedTerm][AbstractTerm.InflectedTerm]
     * to form a new term. For all [Term]s except the [WordStemTerm][AbstractTerm.WordStemTerm] the complete user input
     * is returned as the word stem. For a [WordStemTerm][AbstractTerm.WordStemTerm] the word stem is extracted from the
     * term data as described in the class.
     */
    fun getWordStem(): String

    /**
     * Returns the term data as originally entered by the user.
     */
    fun getUserInput(): String
}