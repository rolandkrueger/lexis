package doc_includes

import org.lexis.vocabulary.terms.Term

//
//
//
//
//
// DO NOT CHANGE THE POSITION OF THE CODE BELOW THIS LINE
// -------------------------------------------------------------------------------------------

fun wordStemDemo() {
    val wordStemTerm = Term.fromUserInput("(to) <under>go")
    val wordStem = wordStemTerm.getWordStem() // => "under"
}

fun inflectedTermDemo() {
    val wordStemTerm = Term.fromUserInput("klock|a")
    val inflectedTerm = Term.fromUserInput("--orna")

    val resolvedInflectedTerm: Term = inflectedTerm.getResolvedInflectedTerm(wordStemTerm)
    val resolvedString = resolvedInflectedTerm.getDisplayString() // => "klockorna"
}

fun inflectedTermWithFullWordStemDemo() {
    val wordStemTerm = Term.fromUserInput("big")
    val wordStem = wordStemTerm.getWordStem() // => big

    val inflectedComparative = Term.fromUserInput("--ger")
    val inflectedSuperlative = Term.fromUserInput("--gest")

    val resolvedComparative = inflectedComparative.getResolvedInflectedTerm(wordStemTerm)
    val resolvedSuperlative = inflectedSuperlative.getResolvedInflectedTerm(wordStemTerm)

    val adjective = wordStemTerm.getUserInput()          // => big
    val comparative = resolvedComparative.getUserInput() // => bigger
    val superlative = resolvedSuperlative.getUserInput() // => biggest
}

fun main() {
    inflectedTermWithFullWordStemDemo()
}