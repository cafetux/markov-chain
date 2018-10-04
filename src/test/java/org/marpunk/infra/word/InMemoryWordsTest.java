package org.marpunk.infra.word;

import org.junit.Test;
import org.marpunk.core.Sentence;
import org.marpunk.core.word.Word;

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

    private void given_a_sentence(List<Word> words) {
        this.words.save(new Sentence(words));
    }

    private Word word(String là) {
        return Word.from(là);
    }

}