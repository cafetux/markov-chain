package org.marpunk.core;

import java.util.List;

/**
 *
 */
public class SentenceGenerator {

    private static final int LENGTH_LIMIT = 2000;

    private final Words words;
    private final RandomGenerator randomGenerator;

    public SentenceGenerator(Words words, RandomGenerator randomGenerator) {
        this.words=words;
        this.randomGenerator = randomGenerator;
    }

    public Sentence generateSentence() {
        Word nextWord = chooseFirstWordOfSentence();
        Sentence sentence = new Sentence(nextWord);
        int count = 0;
        while(count++<LENGTH_LIMIT) {
            nextWord = chooseNextWord(nextWord);
            if (nextWord == Word.END) {
                return sentence;
            }
            sentence.add(nextWord);
        }
        return sentence;
    }

    private Word chooseNextWord(Word nextWord) {
        List<Word> candidats = words.findCandidatFor(nextWord);
        nextWord = candidats.get(randomGenerator.getIntBetween(0,candidats.size()-1));
        return nextWord;
    }

    private Word chooseFirstWordOfSentence() {
        List<Word> startingWords = words.getStartingWords();
        int randomIndex = randomGenerator.getIntBetween(0, startingWords.size() - 1);
        return startingWords.get(randomIndex);
    }
}
