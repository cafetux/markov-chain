package org.marpunk.infra.file;

import org.marpunk.core.word.Word;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class Splitter {

    public List<Word> split(String line) {

        return Arrays.stream(arrange(line).split(" "))
                .filter(this::notBlank)
                .map(Word::from)
                .collect(toList());
    }

    private String arrange(String line) {
        return line
                .replaceAll(",", " , ")
                .replaceAll("\\.", " . ")
                .replaceAll("!", " ! ")
                .replaceAll(":", " : ")
                .replaceAll("\\?", " ? ")
                .replaceAll(";", " ; ")
                .replaceAll("'", "' ");
    }

    private boolean notBlank(String l) {
        return l!=null && !l.isEmpty();
    }


}
