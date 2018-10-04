package org.marpunk.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Sentence {

    private List<Word> words = new ArrayList<>();

    public Sentence(Word value) {
        this.words.add(value);
    }

    public Sentence(List<Word> words) {
        this.words.addAll(words);
    }

    @Override
    public String toString() {
        return words.stream().map(Word::toString).reduce("",(w,w2)-> w+" "+w2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;
        if(this.countWords() != sentence.countWords()){
            return false;
        }

        return sentence.words.equals(this.words);
    }

    public int countWords() {
        return words.size();
    }

    @Override
    public int hashCode() {
        return words != null ? words.hashCode() : 0;
    }

    public void add(Word nextWord) {
        this.words.add(nextWord);
    }
}
