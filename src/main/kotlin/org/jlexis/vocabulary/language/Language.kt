package org.jlexis.vocabulary.language

import java.util.*

data class Language(val locale: Locale, val languageNameKey: String, val id: String = UUID.randomUUID().toString()) {
}