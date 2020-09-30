package org.lexis.languagemodule.swedish.userinput

import org.junit.jupiter.api.Test
import org.lexis.vocabulary.words.userinput.UserInputDataImpl
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SwedishNounUserInputTest {

    val input: SwedishNounUserInput = SwedishNounUserInput(UserInputDataImpl())

    @Test
    fun `test gender and group`() {
        input.gender = SwedishNounUserInput.Gender.UTRUM
        input.group = SwedishNounUserInput.Group.GROUP5

        expectThat(input) {
            get { gender }.isEqualTo(SwedishNounUserInput.Gender.UTRUM)
            get { group }.isEqualTo(SwedishNounUserInput.Group.GROUP5)
        }
    }
}