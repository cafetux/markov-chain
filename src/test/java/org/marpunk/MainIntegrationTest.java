package org.marpunk;

import org.junit.Test;
import org.marpunk.core.SentenceGenerator;
import org.marpunk.infra.file.SentenceFilesLoader;
import org.marpunk.infra.word.InMemoryWords;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class MainIntegrationTest {


    @Test
    public void should_find_some_sentences() throws URISyntaxException {

        Path path = Paths.get(ClassLoader.getSystemResource("text").toURI());
        SentenceFilesLoader loader = new SentenceFilesLoader(path);

        InMemoryWords words = new InMemoryWords();
        loader.load().forEach(words::save);

        SentenceGenerator generator = new SentenceGenerator(words);
        for (int i = 0; i < 20; i++) {
            System.out.println(generator.generateSentence().format());
        }

    }

}
