package org.jlexis.vocabulary.words.formatter.dsl.impl

import org.jlexis.vocabulary.terms.Term
import org.jlexis.vocabulary.words.formatter.dsl.Formatter
import org.jlexis.vocabulary.words.formatter.dsl.FormatterImplementor

class MarkdownFormatter : Formatter(MarkdownFormatterImplementor()) {
    fun asString() = implementor.asString()
}

class MarkdownFormatterImplementor : FormatterImplementor {
    val buffer = StringBuilder()

    override fun asString(): String = buffer.toString()

    override fun begin() {}
    override fun end() {}

    override fun startBold() {
        buffer.append("**")
    }

    override fun endBold() {
        startBold()
        buffer.append(" ")
    }

    override fun startEmphasize() {
        buffer.append("*")
    }

    override fun endEmphasize() {
        startEmphasize()
        buffer.append(" ")
    }

    override fun print(term: Term) {
        if (!term.isEmpty()) {
            buffer.append(term.getDisplayString())
        }
    }

    override fun println(term: Term) {
        print(term)
        newLine()
    }

    override fun String.unaryPlus() {
        buffer.append(this)
    }

    override fun newLine() {
        buffer.append("\n\n")
    }

    override fun divider() {
        newLine()
        buffer.append("---")
        newLine()
    }

    override fun comma() {
        buffer.append(", ")
    }
}