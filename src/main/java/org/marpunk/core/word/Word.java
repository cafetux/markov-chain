package org.marpunk.core.word;

/**
 *
 */
public class Word {

    private String value;

    private Word(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return !(value != null ? !value.equals(word.value) : word.value != null);

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Word from(String value) {
        if(value.contains(" ")){
            throw new IllegalArgumentException("["+value+"]:spaces not allowed");
        }
        return new Word(value);
    }
}
