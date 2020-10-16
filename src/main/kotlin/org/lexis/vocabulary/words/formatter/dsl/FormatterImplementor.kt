package org.lexis.vocabulary.words.formatter.dsl

interface FormatterImplementor {
    fun begin()
    fun end()

    fun startBold()
    fun endBold()

    fun startEmphasize()
    fun endEmphasize()

    fun text(text: String)

    fun openParenthesis() = text("(")
    fun closeParenthesis() = text(")")

    fun openBracket() = text("[")
    fun closeBracket() = text("]")

    fun newline()
    fun divider()

    fun asString(): String
}