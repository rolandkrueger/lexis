package org.jlexis.languagemodule.swedish.userinput

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SwedishNounUserInputTest {

    val input: SwedishNounUserInput = SwedishNounUserInput()

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