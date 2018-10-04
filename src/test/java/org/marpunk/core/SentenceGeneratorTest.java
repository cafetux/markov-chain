package org.marpunk.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class SentenceGeneratorTest {
    private Words words = mock(Words.class);
    private RandomGenerator randomGenerator = mock(RandomGenerator.class);
    private SentenceGenerator generator = new SentenceGenerator(words, randomGenerator);

    private Sentence result;

    @Before
    public void init(){
        when(randomGenerator.getIntBetween(eq(0),anyInt())).thenReturn(0);
        when(words.findCandidatFor(any(Word.class))).thenReturn(Arrays.asList(Word.END));
    }

    @Test
    public void should_generate_a_unique_word_sentence_from_starting_word() {
        given_a_starting_words(asList(word("je")));
        when_we_generate_sentence();
        assertThat(result).isNotNull().isEqualTo(sentence("je"));
    }

    @Test
    public void should_use_some_starting_words_when_have_choice() {
        when(randomGenerator.getIntBetween(0,1)).thenReturn(1);
        given_a_starting_words(asList(word("je"),word("tu")));
        when_we_generate_sentence();
        assertThat(result).isNotNull().isEqualTo(sentence("tu"));
    }

    @Test
    public void should_find_another_word_until_find_end_of_line(){
        given_a_starting_words(asList(word("je"),word("tu")));
        and("je").is_followed_by("suis");
        and("suis").is_followed_by(".");
        and(".").is_followed_by(Word.END);
        when_we_generate_sentence();
        assertThat(result).isNotNull().isEqualTo(sentence("je","suis","."));
    }

    @Test
    public void should_choose_next_word_randomly(){
        given_a_starting_words(asList(word("je"), word("tu")));
        and("je").is_followed_by("suis", "vais", "rentre");
        and_choose_index(1);
        and("vais").is_followed_by(Word.END);
        when_we_generate_sentence();
        assertThat(result).isNotNull().isEqualTo(sentence("je","vais"));
    }

    private void and_choose_index(int index) {
        when(randomGenerator.getIntBetween(0,2)).thenReturn(index);
    }

    @Test
    public void should_end_sentence_when_loop_seems_infinite(){
        given_a_starting_words(asList(word("je")));
        and("je").is_followed_by("je");
        when_we_generate_sentence();
        //then is not infinite
    }

    private TreeBuilder and(String word) {
        return new TreeBuilder(words,word);
    }

    private static class TreeBuilder {
        private final Words words;
        private final Word word;

        private TreeBuilder(Words words,String value) {
            this.words = words;
            this.word = Word.from(value);
        }

        public void is_followed_by(String... candidats){
            when(words.findCandidatFor(word)).thenReturn(Arrays.stream(candidats).map(Word::from).collect(toList()));
        }

        public void is_followed_by(Word word) {
            when(words.findCandidatFor(this.word)).thenReturn(Arrays.asList(word));
        }
    }
    private Sentence sentence(String... value) {
        List<Word> words = Arrays.stream(value).map(Word::from).collect(toList());
        return new Sentence(words);
    }

    private void when_we_generate_sentence() {
        result = generator.generateSentence();
    }

    private void given_a_starting_words(List<Word> words) {
        when(this.words.getStartingWords()).thenReturn(words);
    }

    private Word word(String value) {
        return Word.from(value);
    }

}