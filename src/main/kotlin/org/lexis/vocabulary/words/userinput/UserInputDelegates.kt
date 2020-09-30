package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.terms.Term
import kotlin.reflect.KProperty

/**
 * Property delegate class for term properties. This delegate class is used by the individual [Term] properties of a
 * [UserInputData] to map the term data on the [UserInputData.setTerm] and [UserInputData.getTerm] methods respectively.
 */
class TermPropertyDelegate(private val userInputData: UserInputData, private val forKey: RegisteredTermKey) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): Term {
        return userInputData.getTerm(forKey)
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Term) {
        userInputData.setTerm(forKey, value)
    }
}

/**
 * Property delegate class for flag properties.
 */
class FlagPropertyDelegate(private val userInputData: UserInputData, private val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): Boolean? {
        return userInputData.getFlag(forKey)
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean?) {
        userInputData.setFlag(forKey, value)
    }
}

/**
 * Property delegate class for textual configuration properties.
 */
class ConfigurationPropertyDelegate(private val userInputData: UserInputData, private val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): String? {
        return userInputData.getConfiguration(forKey)
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        userInputData.setConfiguration(forKey, value)
    }
}

/**
 * Property delegate class for enum configuration properties.
 */
class EnumConfigurationPropertyDelegate<T : Enum<T>>(private val userInputData: UserInputData, val forKey: RegisteredDataKey, val convertToEnum: (String) -> T, val defaultValue: () -> T? = { null }) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return convertToEnum(userInputData.getConfiguration(forKey) ?: return defaultValue())
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        userInputData.setConfiguration(forKey, value.toString())
    }
}