package org.marpunk.core;

import java.util.List;

/**
 *
 */
public interface Words {

    List<Word> getStartingWords();

    List<Word> findCandidatFor(Word word);
}
