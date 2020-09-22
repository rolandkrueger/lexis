package org.lexis.languagemodule.swedish.wordclasses

import org.lexis.languagemodule.swedish.formatter.SwedishNounUserInputFormatter
import org.lexis.languagemodule.swedish.userinput.SwedishNounUserInput
import org.lexis.vocabulary.words.formatter.UserInputFormatter
import org.lexis.vocabulary.words.wordclass.AbstractWordClass

class SwedishNounWordClass : AbstractWordClass<SwedishNounUserInput>(identifier = "swedish.wordclass.noun") {

    override fun getUserInputFormatter(): UserInputFormatter<SwedishNounUserInput> = SwedishNounUserInputFormatter()

    override fun createUserInputObject(): SwedishNounUserInput = SwedishNounUserInput()

}