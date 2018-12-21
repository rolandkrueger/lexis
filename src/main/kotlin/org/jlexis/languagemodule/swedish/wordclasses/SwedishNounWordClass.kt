package org.jlexis.languagemodule.swedish.wordclasses

import org.jlexis.languagemodule.swedish.formatter.SwedishNounUserInputFormatter
import org.jlexis.languagemodule.swedish.userinput.SwedishNounUserInput
import org.jlexis.vocabulary.words.formatter.UserInputFormatter
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass

class SwedishNounWordClass : AbstractWordClass<SwedishNounUserInput>(identifier = "swedish.wordclass.noun") {

    override fun getUserInputFormatter(): UserInputFormatter<SwedishNounUserInput> = SwedishNounUserInputFormatter()

    override fun createUserInputObject(): SwedishNounUserInput = SwedishNounUserInput()

}