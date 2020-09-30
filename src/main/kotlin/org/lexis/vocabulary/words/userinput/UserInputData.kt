package org.lexis.vocabulary.words.userinput

import org.lexis.data.HasValues
import org.lexis.vocabulary.terms.EmptyTerm
import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.wordclass.AbstractWordClass

/**
 * Interface for defining the vocabulary input for one particular language. A [UserInputData] is associated with exactly
 * one word class (see [AbstractWordClass]). A user input can receive the different [Term] parts of a vocabulary word
 * (such as singular and plural forms or verb conjugations), boolean flags (such as the information whether or not
 *  a term is irregular), and textual configuration values (arbitrary data which word class might need, such as a
 *  particular enum value).
 *
 *  A [UserInputData] allows to store three types of data:
 *
 *  * Terms: a [Term] is one piece of textual data for a vocabulary word.
 *  * Boolean flags: Boolean values that indicate whether a particular piece of information is set or not set.
 *  * Textual configuration values: Any String that is not directly entered by the user and needs to be stored in a [UserInputData]
 *  object. E.g., this could be a particular enum value transformed into a String.
 *
 *  In order to simplify to define properties in sub-classes for these types, there are delegate classes available to
 *  handle the mapping on the respective getter and setter methods for these types of data. See classes
 *  [TermPropertyDelegate], [DelegatedTermPropertyDelegate], [FlagPropertyDelegate], [ConfigurationPropertyDelegate],
 *  and [EnumConfigurationPropertyDelegate] for details.
 */
interface UserInputData : HasValues {
    /**
     * Set the [Term] for the given [RegisteredTermKey].
     *
     * @throws IllegalArgumentException if the specified key has not been registered with [registerTermKey].
     */
    fun setTerm(key: RegisteredTermKey, input: Term)

    /**
     * Returns the [Term] available for the given [RegisteredTermKey]. If no [Term] is available for this key, the
     * [EmptyTerm] is returned.
     *
     * @throws IllegalArgumentException if the specified key has not been registered with [registerTermKey].
     */
    fun getTerm(key: RegisteredTermKey): Term

    /**
     * Returns `true` if this user input is empty, i.e. it only provides [EmptyTerm]s for every [RegisteredTermKey].
     */
    override fun isEmpty(): Boolean

    /**
     * Set a Boolean flag for the given [RegisteredDataKey]. If the given Boolean is `null`, the value is removed from
     * the user input, i. e. subsequently retrieving this flag with [getFlag] will return `null`.
     *
     * @throws IllegalArgumentException if the specified key has not been registered with [registerFlagKey].
     */
    fun setFlag(forKey: RegisteredDataKey, flag: Boolean?)

    /**
     * Returns the Boolean flag for the given [RegisteredDataKey].
     *
     * @return The Boolean value for the given [RegisteredDataKey] or `null` if no Boolean value is available for this
     * key.
     *
     * @throws IllegalArgumentException if the specified key has not been registered with [registerFlagKey].
     */
    fun getFlag(forKey: RegisteredDataKey): Boolean?

    /**
     * Set the given configuration [value] for the specified key. The key must have been registered with [registerConfigurationKey]
     * prior to using this method.
     *
     * A configuration value can be arbitrary data which is fully under control of the [UserInputData] that sets it.
     * @param[forKey] The key with which the value is associated
     * @param[value] The String value to be set
     */
    fun setConfiguration(forKey: RegisteredDataKey, value: String?)

    /**
     * Returns the configuration value associated with the given key.
     * @param[forKey] Key which was used to store the requested configuration value.
     */
    fun getConfiguration(forKey: RegisteredDataKey): String?

    /**
     * Register the given key as a key for [Term] data. Only keys which have been registered with this method can be used
     * as argument for methods such as [getTerm], [setTerm]. [removeTerm], etc.
     */
    fun registerTermKey(key: RegisteredTermKey)

    /**
     * Register the given key as a key for Boolean flag values. Only keys which have been registered with this method can
     * be used as arguments for [getFlag] and [setFlag].
     */
    fun registerFlagKey(key: RegisteredDataKey)

    /**
     * Register the given ke as a key for String configuration data. Only keys which have been registered with this
     * method can be used as arguments for [getConfiguration] and [setConfiguration].
     */
    fun registerConfigurationKey(key: RegisteredDataKey)

    /**
     * Remove the [Term] specified by the given key. After the term has been removed, a subsequent call to [getTerm]
     * with this key will return the [EmptyTerm].
     */
    fun removeTerm(key: RegisteredTermKey)

    /**
     * Returns `true` if the [Term] identified by the specified key is empty.
     *
     * @see [Term.isEmpty]
     */
    fun isTermEmpty(key: RegisteredTermKey): Boolean

    /**
     * This method sets up the relationship between a word stem term and an inflected term by connecting their respective
     * registered term keys. When this connection has been made, an inflected term can be resolved, i. e. its word stem
     * placeholder will be replaced with the word stem. This is achieved with method [getResolvedInflectedTerm].
     */
    fun connectInflectedTermWithWordStem(inflectedTermKey: RegisteredInflectedTermKey, wordStemKey: RegisteredWordStemTermKey)

    /**
     * Returns the resolved form of an inflected term specified by its corresponding key. Resolving an inflected term
     * means to replace any word stem placeholder with the word stem. The association between inflected term and its
     * corresponding word stem term is set up with [connectInflectedTermWithWordStem].
     *
     * Example: If an inflected term contains the Swedish user input "--an" and its corresponding word stem contains
     * "klock|a", the resolved inflected term is "klockan".
     *
     * @param[inflectedTermKey] the key with which an inflected term is associated
     * @see Term
     */
    fun getResolvedInflectedTerm(inflectedTermKey: RegisteredInflectedTermKey): Term
}