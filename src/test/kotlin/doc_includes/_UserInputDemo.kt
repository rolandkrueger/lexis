package doc_includes

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.userinput.*

class DemoUserInput : UserInputImpl() {

    enum class Gender {
        MALE,
        FEMALE,
        NEUTRUM
    }

    private companion object {
        val termKey = RegisteredTermKey("the.term")
        val flagKey = RegisteredDataKey("flag.isIrregular")
        val configKey = RegisteredDataKey("some.configuration")
        val genderKey = RegisteredDataKey("gender")
    }

    init {
        registerTermKey(termKey)
        registerFlagKey(flagKey)
        registerConfigurationKey(configKey)
        registerConfigurationKey(genderKey)
    }

    var term by TermPropertyDelegate(termKey)
    var isIrregularFlag by FlagPropertyDelegate(flagKey)
    var configuration by ConfigurationPropertyDelegate(configKey)
    var gender by EnumConfigurationPropertyDelegate(genderKey,
            { Gender.valueOf(it) }, { Gender.NEUTRUM })
}

fun main() {
    val demoInput = DemoUserInput()

    demoInput.term = Term.fromUserInput("Status")
    demoInput.isIrregularFlag = true
    demoInput.configuration = "<some configuration value>"
    demoInput.gender = DemoUserInput.Gender.MALE

    println(demoInput.term.getDisplayString()) // => Status
    println(demoInput.isIrregularFlag)         // => true
    println(demoInput.configuration)           // => <some configuration value>
    println(demoInput.gender)                  // => MALE
}