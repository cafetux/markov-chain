package org.marpunk.core.word;

import java.util.Objects;

/**
 *
 */
public class SimpleWord implements Word{

    private final String value;

    private SimpleWord(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Word{" +
                "value='" + value + '\'' +
                '}';
    }

    public static SimpleWord from(String value) {
        if(value.contains(" ")){
            throw new IllegalArgumentException("["+value+"]:spaces not allowed");
        }
        return new SimpleWord(value);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleWord that = (SimpleWord) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String getKey() {
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
