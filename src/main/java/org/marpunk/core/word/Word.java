package org.marpunk.core.word;

import java.io.Serializable;

public interface Word extends Serializable {

    String getKey();

    String getValue();
}
