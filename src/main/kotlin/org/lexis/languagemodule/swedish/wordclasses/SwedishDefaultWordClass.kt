package org.lexis.languagemodule.swedish.wordclasses

import org.lexis.vocabulary.words.formatter.DefaultUserInputWithStandardFieldsFormatter
import org.lexis.vocabulary.words.formatter.UserInputFormatter
import org.lexis.vocabulary.words.userinput.DefaultUserInputWithStandardFields
import org.lexis.vocabulary.words.wordclass.AbstractWordClass

class SwedishDefaultWordClass : AbstractWordClass<DefaultUserInputWithStandardFields>(identifier = "swedish.wordclass.default") {

    override fun getUserInputFormatter(): UserInputFormatter<DefaultUserInputWithStandardFields> =
            DefaultUserInputWithStandardFieldsFormatter()

    override fun createUserInputObject(): DefaultUserInputWithStandardFields = DefaultUserInputWithStandardFields(keyPrefix = "swedish")

}