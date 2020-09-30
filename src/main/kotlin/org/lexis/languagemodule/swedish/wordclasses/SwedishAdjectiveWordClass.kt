package org.lexis.languagemodule.swedish.wordclasses

import org.lexis.languagemodule.swedish.formatter.SwedishAdjectiveUserInputFormatter
import org.lexis.languagemodule.swedish.userinput.SwedishAdjectiveUserInput
import org.lexis.vocabulary.words.formatter.UserInputFormatter
import org.lexis.vocabulary.words.wordclass.AbstractWordClass
import org.lexis.vocabulary.words.wordclass.WordClassEnum

class SwedishAdjectiveWordClass : AbstractWordClass<SwedishAdjectiveUserInput>(WordClassEnum.ADJECTIVE, "swedish.wordclass.adjective") {

    override fun getUserInputFormatter(): UserInputFormatter<SwedishAdjectiveUserInput> = SwedishAdjectiveUserInputFormatter()

    override fun createUserInputObject(): SwedishAdjectiveUserInput = SwedishAdjectiveUserInput()
}