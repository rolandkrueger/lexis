package org.lexis.vocabulary.words.wordclass

import org.lexis.vocabulary.words.VocabularyWord

/**
 * Enum to define the word class of a single [VocabularyWord]. A word class (or part of speech) is a category of words
 * which have similar grammatical properties.
 *
 * See [Wikipedia](https://en.wikipedia.org/wiki/Part_of_speech).
 *
 * @property translationKey The key used to find a translation for the word class.
 */
enum class WordClassEnum(val translationKey: String) {
    /**
     * Default value: to be used if no other word class is applicable.
     */
    DEFAULT("word.class.default"),
    ADJECTIVE("word.class.adjective"),
    ADPOSITION("word.class.adposition"),
    ADVERB("word.class.adverb"),
    CARDINAL_NUMBER("word.class.cardinal"),
    CLITIC("word.class.clitic"),
    COLLOCATION("word.class.collocation"),
    CONJUNCTION("word.class.conjunction"),
    CONTRACTION("word.class.contraction"),
    COVERB("word.class.coverb"),
    DETERMINER("word.class.determiner"),
    IDIOM("word.class.idiom"),
    INTERJECTION("word.class.interjection"),
    NOUN("word.class.noun"),
    PARTICLE("word.class.particle"),
    PHRASAL_VERB("word.class.phrasal"),
    PREVERB("word.class.preverb"),
    PRONOUN("word.class.pronoun"),
    VERB("word.class.verb"),
    EXTRA("word.class.extra")
}