package org.lexis.vocabulary.words.formatter.dsl

import org.lexis.vocabulary.terms.Term

@DslMarker
annotation class FormatterDsl

@FormatterDsl
abstract class Formatter(val implementor: FormatterImplementor) {

    operator fun invoke(block: Formatter.() -> Unit) {
        implementor.begin()
        apply(block)
        implementor.end()
    }

    fun print(term: Term) {
        whenGiven(term) {
            implementor.text(term.getDisplayString())
        }
    }

    fun println(term: Term) {
        print(term)
        implementor.newline()
    }

    fun main(block: Formatter.() -> Unit) {
        bold {
            apply(block)
        }
    }

    fun phonetics(phoneticsTerm: Term) {
        squareBrackets {
            print(phoneticsTerm)
        }
    }

    fun extra(block: Formatter.() -> Unit) {
        divider()
        apply(block)
    }

    fun bold(block: Formatter.() -> Unit): Formatter {
        implementor.startBold()
        apply(block)
        implementor.endBold()
        return this
    }

    fun emphasize(block: Formatter.() -> Unit): Formatter {
        implementor.startEmphasize()
        apply(block)
        implementor.endEmphasize()
        return this
    }

    operator fun String.unaryPlus() = implementor.text(this)

    fun parenthesize(block: Formatter.() -> Unit): Formatter {
        implementor.openParenthesis()
        apply(block)
        implementor.closeParenthesis()
        return this
    }

    fun squareBrackets(block: Formatter.() -> Unit): Formatter {
        implementor.openBracket()
        apply(block)
        implementor.closeBracket()
        return this
    }

    fun comma() = implementor.text(",")
    fun newline() = implementor.newline()
    fun divider() = implementor.divider()

    fun whenGiven(term: Term, block: Formatter.() -> Unit) {
        if (!term.isEmpty()) {
            apply(block)
        }
    }

    fun whenAnyGiven(terms: Set<Term>, block: Formatter.() -> Unit) {
        if (terms.any { term -> !term.isEmpty() }) {
            apply(block)
        }
    }

    fun whenAllGiven(terms: Set<Term>, block: Formatter.() -> Unit) {
        if (terms.all { term -> !term.isEmpty() }) {
            apply(block)
        }
    }

    fun join(terms: List<Term>): String =
            terms.map { term -> term.getDisplayString() }.joinToString()
}