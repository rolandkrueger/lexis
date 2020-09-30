package org.lexis.vocabulary.words.userinput

import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecorator
import org.lexis.vocabulary.words.userinput.standard.StandardUserInputDecoratorImpl

class DefaultUserInputWithStandardFields(userInputData: UserInputData = UserInputDataImpl(), keyPrefix: String) :
        DefaultUserInput by DefaultUserInputImpl(userInputData),
        StandardUserInputDecorator by StandardUserInputDecoratorImpl(keyPrefix, userInputData)