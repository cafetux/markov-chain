package org.marpunk.infra.file;

import org.junit.Test;
import org.marpunk.core.Sentence;
import org.marpunk.core.word.Word;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class SentenceFilesLoaderTest {


    private SentenceFilesLoader loader;

    @Test
    public void should_load_all_lines_in_file() throws Exception {
        loader = new SentenceFilesLoader(path("text/victor_hugo"));
        List<Sentence> sentences = loader.load();
        assertThat(sentences.size()).isEqualTo(14);
        assertThat(sentences.get(0)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word(","), word("pénélope"), word("imbécile"), word(","))));
    }

    @Test
    public void should_be_ponctuation_proof() throws Exception {
        loader = new SentenceFilesLoader(path("text/ponct"));
        List<Sentence> sentences = loader.load();
        assertThat(sentences.get(0)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word(";"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(1)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word("!"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(2)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word("?"), word("pénélope"), word("imbécile"), word(","))));
        assertThat(sentences.get(3)).isEqualTo(new Sentence(asList(word("ouvrière"), word("sans"), word("yeux"), word(":"), word("pénélope"), word("imbécile"), word(","))));
    }

    private Word word(String imbécile) {
        return Word.from(imbécile);
    }

    private Path path(String path) throws URISyntaxException {
        return Paths.get(ClassLoader.getSystemResource(path).toURI());
    }
}