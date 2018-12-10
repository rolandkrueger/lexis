package org.jlexis.languagemodule.swedish.wordclasses

import org.jlexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields
import org.jlexis.vocabulary.words.userinput.UserInput
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass

class SwedishDefaultWordClass : AbstractWordClass(identifier = "swedish.wordclass.default") {

    override fun createUserInputObject(): UserInput = DefaultUserInputWithStandardFields("swedish")

}