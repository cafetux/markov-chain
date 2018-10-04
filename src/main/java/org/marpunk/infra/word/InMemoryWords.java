package org.marpunk.infra.word;

import org.marpunk.core.Sentence;
import org.marpunk.core.word.Word;
import org.marpunk.core.word.Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

/**
 *
 */
public class InMemoryWords implements Words {

    private Map<Word,List<Word>> links = new HashMap<>();

    @Override
    public List<Word> getStartingWords() {
        return unmodifiableList(links.getOrDefault(START, emptyList()));
    }

    @Override
    public List<Word> findCandidatFor(Word word) {
        return unmodifiableList(links.getOrDefault(word, emptyList()));
    }

    @Override
    public void save(Sentence words) {
        Word previous = START;
        for (Word current : words) {
            List<Word> wordLinks = links.getOrDefault(previous, new ArrayList<>());
            wordLinks.add(current);
            links.put(previous,wordLinks);
            previous=current;
        }
    }
}
