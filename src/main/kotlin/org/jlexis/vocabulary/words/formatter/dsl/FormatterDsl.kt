package org.jlexis.vocabulary.words.formatter.dsl

import org.jlexis.vocabulary.terms.Term

@DslMarker
annotation class FormatterDsl

@FormatterDsl
abstract class Formatter(val implementor: FormatterImplementor) : Format by implementor {

    operator fun invoke(block: Formatter.() -> Unit) {
        implementor.begin()
        apply(block)
        implementor.end()
    }

    fun main(block: MAIN_FORMATTER.() -> Unit) {
        MAIN_FORMATTER(this).apply(block)
    }

    fun phonetics(block: PHONETICS_FORMATTER.() -> Unit) {
        PHONETICS_FORMATTER(this).apply(block)
    }

    fun extra(block: EXTRA_FORMATTER.() -> Unit) {
        implementor.divider()
        EXTRA_FORMATTER(this).apply(block)
    }

    override fun bold(block: Format.() -> Unit): Format {
        implementor.startBold()
        apply(block)
        implementor.endBold()
        return this
    }

    override fun emphasize(block: Format.() -> Unit): Format {
        implementor.startEmphasize()
        apply(block)
        implementor.endEmphasize()
        return this
    }

    override fun parenthesize(block: Format.() -> Unit): Format {
        +" ("
        apply(block)
        +")"
        return this
    }

    override fun squareBrackets(block: Format.() -> Unit): Format {
        +" ["
        apply(block)
        +"]"
        return this
    }

    override fun comma() {
        +", "
    }
}

interface FormatterImplementor : Format {
    fun begin()
    fun end()

    fun startBold()
    fun endBold()

    fun startEmphasize()
    fun endEmphasize()
    fun asString(): String
}

@FormatterDsl
interface Format {
    fun print(term: Term)
    fun println(term: Term)
    operator fun String.unaryPlus()
    fun newLine()
    fun divider()
    fun comma()
    fun emphasize(block: Format.() -> Unit) = apply(block)
    fun bold(block: Format.() -> Unit) = apply(block)
    fun parenthesize(block: Format.() -> Unit) = apply(block)
    fun squareBrackets(block: Format.() -> Unit) = apply(block)
    fun printBlock(block: Format.() -> Unit) = apply(block)
    fun whenGiven(term: Term, block: Format.() -> Unit) {
        if (!term.isEmpty()) {
            apply(block)
        }
    }

    fun whenAnyGiven(terms: Set<Term>, block: Format.() -> Unit) {
        if (terms.any { term -> !term.isEmpty() }) {
            apply(block)
        }
    }

    fun whenAllGiven(terms: Set<Term>, block: Format.() -> Unit) {
        if (terms.all { term -> !term.isEmpty() }) {
            apply(block)
        }
    }

    fun join(terms: List<Term>) {
        terms.map { term -> term.getDisplayString() }.joinToString()
    }
}

@FormatterDsl
class MAIN_FORMATTER(val formatter: Formatter) : Format by formatter {
    override fun print(term: Term) {
        bold {
            this@MAIN_FORMATTER.formatter.print(term)
        }
    }
}

@FormatterDsl
class EXTRA_FORMATTER(val formatter: Formatter) : Format by formatter

@FormatterDsl
class PHONETICS_FORMATTER(val formatter: Formatter) : Format by formatter {
    override fun printBlock(block: Format.() -> Unit): Format {
        squareBrackets(block)
        return this
    }

    override fun print(term: Term) {
        squareBrackets {
            this@PHONETICS_FORMATTER.formatter.print(term)
        }
    }

    override fun println(term: Term) {
        print(term)
        newLine()
    }
}