package org.jlexis.languagemodule.swedish.wordclasses

import org.jlexis.languagemodule.swedish.formatter.SwedishAdjectiveUserInputFormatter
import org.jlexis.languagemodule.swedish.userinput.SwedishAdjectiveUserInput
import org.jlexis.vocabulary.words.formatter.UserInputFormatter
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass
import org.jlexis.vocabulary.words.wordclass.WordClassEnum

class SwedishAdjectiveWordClass : AbstractWordClass<SwedishAdjectiveUserInput>(WordClassEnum.ADJECTIVE, "swedish.wordclass.adjective") {

    override fun getUserInputFormatter(): UserInputFormatter<SwedishAdjectiveUserInput> = SwedishAdjectiveUserInputFormatter()
    override fun createUserInputObject(): SwedishAdjectiveUserInput = SwedishAdjectiveUserInput()

}