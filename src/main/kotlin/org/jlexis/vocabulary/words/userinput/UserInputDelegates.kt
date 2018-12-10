package org.jlexis.vocabulary.words.userinput

import org.jlexis.vocabulary.terms.Term
import kotlin.reflect.KProperty

class UserInputTermPropertyDelegate(val forKey: RegisteredTermKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Term {
        return thisRef.getTerm(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Term) {
        thisRef.setTerm(forKey, value)
    }
}

class DelegatedTermPropertyDelegate(val key: RegisteredTermKey, val delegate: Term) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Term {
        return delegate
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Term) {
        thisRef.setTerm(key, value)
    }
}

class FlagPropertyDelegate(val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): Boolean? {
        return thisRef.getFlag(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: Boolean?) {
        thisRef.setFlag(forKey, value)
    }
}

class ConfigurationPropertyDelegate(val forKey: RegisteredDataKey) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): String? {
        return thisRef.getConfiguration(forKey)
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: String?) {
        thisRef.setConfiguration(forKey, value)
    }
}

class EnumConfigurationPropertyDelegate<T : Enum<T>>(val forKey: RegisteredDataKey, val convertToEnum: (String) -> T, val defaultValue: () -> T? = { null }) {
    operator fun getValue(thisRef: UserInput, property: KProperty<*>): T? {
        return convertToEnum(thisRef.getConfiguration(forKey) ?: return defaultValue())
    }

    operator fun setValue(thisRef: UserInput, property: KProperty<*>, value: T?) {
        thisRef.setConfiguration(forKey, value.toString())
    }
}