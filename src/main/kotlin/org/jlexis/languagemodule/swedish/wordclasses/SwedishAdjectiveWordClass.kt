package org.jlexis.languagemodule.swedish.wordclasses

import org.jlexis.languagemodule.swedish.userinput.SwedishAdjectiveUserInput
import org.jlexis.vocabulary.words.userinput.UserInput
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass
import org.jlexis.vocabulary.words.wordclass.WordClassEnum

class SwedishAdjectiveWordClass : AbstractWordClass(WordClassEnum.ADJECTIVE, "swedish.wordclass.adjective") {

    override fun createUserInputObject(): UserInput = SwedishAdjectiveUserInput()

}