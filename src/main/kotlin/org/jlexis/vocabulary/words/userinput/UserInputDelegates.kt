package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.Term
import kotlin.reflect.KProperty

/**
 * Property delegate class for term properties. This delegate class is used by the individual [Term] properties of a
 * [UserInput] to map the term data on the [UserInput.setTerm] and [UserInput.getTerm] methods respectively.
 */
class UserInputTermPropertyDelegate(private val forKey: RegisteredTermKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Term {
        return thisRef.getTerm(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Term) {
        thisRef.setTerm(forKey, value)
    }
}

/**
 * Property delegate class for delegated term properties.
 */
class DelegatedTermPropertyDelegate(private val forKey: RegisteredTermKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Term {
        return thisRef.getTerm(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Term) {
        thisRef.setTerm(forKey, value)
    }
}

/**
 * Property delegate class for flag properties.
 */
class FlagPropertyDelegate(private val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Boolean? {
        return thisRef.getFlag(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Boolean?) {
        thisRef.setFlag(forKey, value)
    }
}

/**
 * Property delegate class for textual configuration properties.
 */
class ConfigurationPropertyDelegate(private val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): String? {
        return thisRef.getConfiguration(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: String?) {
        thisRef.setConfiguration(forKey, value)
    }
}

/**
 * Property delegate class for enum configuration properties.
 */
class EnumConfigurationPropertyDelegate<T : Enum<T>>(val forKey: RegisteredDataKey, val convertToEnum: (String) -> T, val defaultValue: () -> T? = { null }) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): T? {
        return convertToEnum(thisRef.getConfiguration(forKey) ?: return defaultValue())
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: T?) {
        thisRef.setConfiguration(forKey, value.toString())
    }
}