package org.marpunk.core.word;

import org.marpunk.core.Sentence;

import java.util.List;

/**
 *
 */
public interface Words {
    Word END = SimpleWord.from("-END-");
    Word START = SimpleWord.from("-START-");

    List<Word> getStartingWords();

    List<Word> findCandidatFor(Word word);

    void save(Sentence words);
}
