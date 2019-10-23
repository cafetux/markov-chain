package org.marpunk.infra.file;

import org.marpunk.core.Sentence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class SentenceFilesLoader {

    private final Path root;
    private final Splitter splitter;

    public SentenceFilesLoader(Path root) {
        this.root = root;
        splitter = new SimpleSplitter();
    }

    public SentenceFilesLoader(Path root, Splitter splitter) {
        this.root = root;
        this.splitter = splitter;
    }

    public List<Sentence> load() {

        try {
            try (Stream<Path> paths = Files.walk(root)) {
                List<String> lines = paths
                        .filter(Files::isRegularFile)
                        .map(this::readAllLines)
                        .flatMap(Collection::stream)
                        .filter(this::notBlank)
                        .map(String::toLowerCase)
                        .collect(toList());

                return lines.stream()
                        .map(splitter::split)
                        .map(Sentence::new)
                        .collect(toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean notBlank(String l) {
        return l!=null && !l.isEmpty();
    }

    private List<String> readAllLines(Path p) {
        try {
            return Files.readAllLines(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
