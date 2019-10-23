package org.marpunk.core;

import org.junit.Test;
import org.marpunk.core.word.SimpleWord;
import org.marpunk.core.word.Word;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class SentenceTest {


    @Test
    public void should_get_formated_sentence(){
        Sentence sentence = $(asList(w("bonjour"), w(","), w("comment"), w("va"), w("l'"), w("artiste"),w("?")));

        assertThat(sentence.format()).isEqualTo("bonjour, comment va l'artiste ?");
    }

    private Sentence $(List<Word> words) {
        return new Sentence(words);
    }

    private SimpleWord w(String bonjour) {
        return SimpleWord.from(bonjour);
    }

}