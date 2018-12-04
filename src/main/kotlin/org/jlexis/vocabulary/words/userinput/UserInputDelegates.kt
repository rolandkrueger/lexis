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


