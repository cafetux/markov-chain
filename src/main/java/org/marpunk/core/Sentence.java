package org.marpunk.core;

import org.marpunk.core.word.Word;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 */
public class Sentence implements Iterable<Word>, Serializable {

    private List<Word> words = new ArrayList<>();

    private Sentence() {
    }

    public Sentence(Word value) {
        this.words.add(value);
    }

    public Sentence(List<Word> words) {
        this.words.addAll(words);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;

        return sentence.words.equals(this.words);
    }

    @Override
    public int hashCode() {
        return words != null ? words.hashCode() : 0;
    }

    public void add(Word nextWord) {
        this.words.add(nextWord);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                '}';
    }

    public String format() {
        return words.stream().map(Word::getValue).collect(Collectors.joining(" "))
                .replace("' ", "'")
                .replace(" ;",";")
                .replace(" :",":")
                .replace(" .",".")
                .replace(" ,", ",");
    }

    public int size(){
        return words.size();
    }

    @Override
    public Iterator<Word> iterator() {
        return words.iterator();
    }

    public Stream<Word> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
