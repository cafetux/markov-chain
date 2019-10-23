package org.marpunk.infra.file;

import org.marpunk.core.word.Word;

import java.util.List;

public interface Splitter {
    List<Word> split(String line);
}
