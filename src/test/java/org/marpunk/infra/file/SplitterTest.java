package org.marpunk.infra.file;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.marpunk.core.word.SimpleWord.from;

/**
 *
 */
public class SplitterTest {


    private SimpleSplitter splitter = new SimpleSplitter();

    @Test
    public void should_split_on_spaces(){
        assertThat(splitter.split("il était une fois"))
                .containsExactly(from("il"), from("était"), from("une"), from("fois"));
    }

    @Test
    public void should_be_punctuation_proof(){
        assertThat(splitter.split("il était, une fois"))
                .containsExactly(from("il"), from("était"),from(","), from("une"), from("fois"));
    }

    @Test
    public void should_be_apostrophe_letter_a_word(){
        assertThat(splitter.split("il l'était! méchant"))
                .containsExactly(from("il"),from("l'"), from("était"),from("!"), from("méchant"));
    }
}