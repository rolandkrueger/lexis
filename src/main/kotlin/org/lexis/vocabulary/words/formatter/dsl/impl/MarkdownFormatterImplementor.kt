package org.lexis.vocabulary.words.formatter.dsl.impl

import org.lexis.vocabulary.terms.Term
import org.lexis.vocabulary.words.formatter.dsl.Formatter
import org.lexis.vocabulary.words.formatter.dsl.FormatterImplementor

class MarkdownFormatter : Formatter(MarkdownFormatterImplementor()) {
    fun asString() = implementor.asString()
}

class MarkdownFormatterImplementor : FormatterImplementor {
    private val buffer = StringBuilder()

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

    override fun text(text: String) {
        buffer.append(text)
    }

    override fun newline() {
        buffer.append("\n\n")
    }

    override fun divider() {
        newline()
        buffer.append("---")
        newline()
    }
}