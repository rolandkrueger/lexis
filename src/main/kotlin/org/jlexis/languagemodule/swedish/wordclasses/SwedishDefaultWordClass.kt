package org.jlexis.languagemodule.swedish.wordclasses

import org.jlexis.vocabulary.words.formatter.DefaultUserInputWithStandardFieldsFormatter
import org.jlexis.vocabulary.words.formatter.UserInputFormatter
import org.jlexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields
import org.jlexis.vocabulary.words.wordclass.AbstractWordClass

class SwedishDefaultWordClass : AbstractWordClass<DefaultUserInputWithStandardFields>(identifier = "swedish.wordclass.default") {

    override fun getUserInputFormatter(): UserInputFormatter<DefaultUserInputWithStandardFields> =
            DefaultUserInputWithStandardFieldsFormatter()

    override fun createUserInputObject(): DefaultUserInputWithStandardFields = DefaultUserInputWithStandardFields("swedish")

}