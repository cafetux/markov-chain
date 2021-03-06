package org.marpunk.core;

import org.marpunk.core.word.Word;
import org.marpunk.core.word.Words;
import org.marpunk.infra.SystemRandom;

import java.util.List;

import static org.marpunk.infra.word.InMemoryWords.END;

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

    public SentenceGenerator(Words words) {
        this(words, new SystemRandom());
    }

    public Sentence generateSentence() {
        Word nextWord = chooseFirstWordOfSentence();
        Sentence sentence = new Sentence(nextWord);
        int count = 0;
        while(count++<LENGTH_LIMIT) {
            nextWord = chooseNextWord(nextWord);
            if (nextWord == END) {
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
