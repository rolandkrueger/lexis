package org.lexis.languagemodule.swedish.userinput

import org.lexis.data.HasValues
import org.lexis.vocabulary.words.userinput.*
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecorator
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecoratorImpl

class SwedishNounUserInput(private val userInputData: UserInputData = UserInputDataImpl()) : StandardUserInputDecorator by StandardUserInputDecoratorImpl("swedish", userInputData), HasValues by userInputData {

    enum class Gender {
        UTRUM, NEUTRUM, UNKNOWN;
    }

    enum class Group {
        GROUP1, GROUP2, GROUP3, GROUP4, GROUP5, UNKNOWN
    }

    private companion object {
        private val definiteSingularKey = RegisteredTermKey("swedish.noun.definiteSingular")
        private val indefiniteSingularKey = RegisteredTermKey("swedish.noun.indefiniteSingular")
        private val definitePluralKey = RegisteredTermKey("swedish.noun.definitePlural")
        private val indefinitePluralKey = RegisteredTermKey("swedish.noun.indefinitePlural")

        private val isIrregularKey = RegisteredDataKey("swedish.noun.isIrregular")
        private val genderKey = RegisteredDataKey("swedish.noun.gender")
        private val groupKey = RegisteredDataKey("swedish.noun.group")
    }

    var definiteSingular by TermPropertyDelegate(userInputData, definiteSingularKey)
    var indefiniteSingular by TermPropertyDelegate(userInputData, indefiniteSingularKey)
    var definitePlural by TermPropertyDelegate(userInputData, definitePluralKey)
    var indefinitePlural by TermPropertyDelegate(userInputData, indefinitePluralKey)

    var isIrregular by FlagPropertyDelegate(userInputData, isIrregularKey)

    var gender by EnumConfigurationPropertyDelegate(userInputData, genderKey, { Gender.valueOf(it) }, { Gender.UNKNOWN })
    var group by EnumConfigurationPropertyDelegate(userInputData, groupKey, { Group.valueOf(it) }, { Group.UNKNOWN })

    init {
        listOf(definiteSingularKey, indefiniteSingularKey, definitePluralKey, indefinitePluralKey).forEach {
            userInputData.registerTermKey(it)
        }

        listOf(genderKey, groupKey).forEach { userInputData.registerConfigurationKey(it) }

        userInputData.registerFlagKey(isIrregularKey)

        userInputData.connectInflectedTermWithWordStem(definiteSingularKey, indefiniteSingularKey)
        userInputData.connectInflectedTermWithWordStem(definitePluralKey, indefiniteSingularKey)
        userInputData.connectInflectedTermWithWordStem(indefinitePluralKey, indefiniteSingularKey)
    }

    fun getResolvedDefiniteSingular() = userInputData.getResolvedInflectedTerm(definiteSingularKey)
    fun getResolvedDefinitePlural() = userInputData.getResolvedInflectedTerm(definitePluralKey)
    fun getResolvedIndefinitePlural() = userInputData.getResolvedInflectedTerm(indefinitePluralKey)
}