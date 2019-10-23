package org.marpunk.infra.word;

import org.junit.Test;
import org.marpunk.core.Sentence;
import org.marpunk.core.GrammarWord;
import org.marpunk.core.word.SimpleWord;
import org.marpunk.core.word.Word;
import org.marpunk.core.word.Words;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class InMemoryWordsTest {

    private InMemoryWords words = new InMemoryWords();

    @Test
    public void should_save_correctly_a_sentence() {
        given_a_sentence(asList(word("je"), word("suis"), word("là")));
        assertThat(words.getStartingWords()).containsExactly(word("je"));
        assertThat(words.findCandidatFor(word("je"))).as("'je' doit avoir comme candidat 'suis'").containsExactly(word("suis"));
        assertThat(words.findCandidatFor(word("suis"))).as("'suis' doit avoir comme candidat 'là'").containsExactly(word("là"));
    }

    @Test
    public void should_not_confuse_ajective_with_noun() {
        given_a_sentence(asList(word("pronoun","je"), word("verb","vis"), word("preposition","à"), word("noun","Paris")));
        given_a_sentence(asList(word("verb","passes"), word("pronoun","moi"), word("adverb","la"), word("noun","vis")));
        assertThat(words.findCandidatFor(word("noun","vis"))).as("le nom 'vis' ne doit pas avoir de candidats").containsExactly(Words.END);
        assertThat(words.findCandidatFor(word("verb","vis"))).as("le verbe 'vis' doit avoir comme candidat 'à'").containsExactly(word("preposition","à"));
    }

    private Word word(String key, String value) {
        return GrammarWord.from(GrammarWord.GrammarType.from(key), value);
    }

    @Test
    public void should_save_correctly_multiple_sentence() {
        given_a_sentence(asList(word("je"), word("suis"), word("là")));
        given_a_sentence(asList(word("tu"), word("es"), word("là")));
        given_a_sentence(asList(word("envahir"), word("les"), word("pensées")));

        assertThat(words.getStartingWords()).containsExactly(word("je"),word("tu"),word("envahir"));
        assertThat(words.findCandidatFor(word("je"))).as("'je' doit avoir comme candidat 'suis'").containsExactly(word("suis"));
        assertThat(words.findCandidatFor(word("les"))).as("'les' doit avoir comme candidat 'pensées'").containsExactly(word("pensées"));
    }

    @Test
    public void should_save_correctly_multiple_sentence_with_same_words() {
        given_a_sentence(asList(word("je"), word("suis"), word("là")));
        given_a_sentence(asList(word("je"), word("suis"), word("partis")));
        given_a_sentence(asList(word("je"), word("serais"), word("mort")));
        given_a_sentence(asList(word("tu"), word("est"), word("là")));

        assertThat(words.getStartingWords()).containsExactly(word("je"),word("je"),word("je"),word("tu"));
        assertThat(words.findCandidatFor(word("je"))).as("'je' doit avoir comme candidat 'suis'").containsExactly(word("suis"),word("suis"),word("serais"));
        assertThat(words.findCandidatFor(word("suis"))).containsExactly(word("là"),word("partis"));
    }

    @Test
    public void should_retrieve_end_word_when_no_candidat() {
        given_a_sentence(asList(word("je"), word("suis"), word("là")));

        assertThat(words.findCandidatFor(word("là"))).as("should find is end of sentence")
                .containsExactly(Words.END);
    }

    private void given_a_sentence(List<Word> words) {
        this.words.save(new Sentence(words));
    }

    private SimpleWord word(String là) {
        return SimpleWord.from(là);
    }

}