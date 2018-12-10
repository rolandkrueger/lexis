package org.jlexis.languagemodule.swedish.userinput

import org.jlexis.vocabulary.words.userinput.*
import org.jlexis.vocabulary.words.userinput.standard.StandardUserInputDecorator

class SwedishNounUserInput : StandardUserInputDecorator<UserInputImpl>(UserInputImpl(), "swedish") {

    enum class Gender {
        UTRUM, NEUTRUM, UNKNOWN;
    }

    enum class Group {
        GROUP1, GROUP2, GROUP3, GROUP4, GROUP5, UNKNOWN
    }

    val definiteSingularKey = RegisteredTermKey("swedish.noun.definiteSingular")
    val indefiniteSingularKey = RegisteredTermKey("swedish.noun.indefiniteSingular")
    val definitePluralKey = RegisteredTermKey("swedish.noun.definitePlural")
    val indefinitePluralKey = RegisteredTermKey("swedish.noun.indefinitePlural")

    val isIrregularKey = RegisteredDataKey("swedish.noun.isIrregular")

    val genderKey = RegisteredDataKey("swedish.noun.gender")
    val groupKey = RegisteredDataKey("swedish.noun.group")

    var isIrregular by FlagPropertyDelegate(isIrregularKey)

    var gender by EnumConfigurationPropertyDelegate(genderKey, { Gender.valueOf(it) }, {Gender.UNKNOWN})
    var group by EnumConfigurationPropertyDelegate(groupKey, { Group.valueOf(it) }, { Group.UNKNOWN })

    init {
        listOf(definiteSingularKey, indefiniteSingularKey, definitePluralKey, indefinitePluralKey).forEach {
            registerTermKey(it)
        }

        listOf(genderKey, groupKey).forEach { registerConfigurationKey(it) }

        registerFlagKey(isIrregularKey)

        connectInflectedTermWithWordStem(definiteSingularKey, indefiniteSingularKey)
        connectInflectedTermWithWordStem(definitePluralKey, indefiniteSingularKey)
        connectInflectedTermWithWordStem(indefinitePluralKey, indefiniteSingularKey)
    }

    fun getResolvedDefiniteSingular() = getResolvedInflectedTerm(definiteSingularKey)
    fun getResolvedDefinitePlural() = getResolvedInflectedTerm(definitePluralKey)
    fun getResolvedIndefinitePlural() = getResolvedInflectedTerm(indefinitePluralKey)
}