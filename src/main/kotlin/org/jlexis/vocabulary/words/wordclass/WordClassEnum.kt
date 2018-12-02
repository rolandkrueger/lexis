package org.jlexis.vocabulary.words.wordclass

enum class WordClassEnum(val translationKey: String) {
    /**
     * Default value; to be used of no other word class is applicable.
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