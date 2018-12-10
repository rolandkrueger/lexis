package org.jlexis.vocabulary.language

import java.util.*

/**
 * Data class defining the language for a [LanguageModule].
 *
 * @property locale The [Locale] used by this language.
 * @property languageNameKey Translation key for the language's name.
 * @property id The language's identifier. Is initialized with a random [UUID] by default.
 */
data class Language(val locale: Locale, val languageNameKey: String, val id: String = UUID.randomUUID().toString()) {
}