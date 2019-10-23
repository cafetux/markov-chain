package org.marpunk.core;

import org.marpunk.core.word.Word;

import java.util.Objects;

/**
 *
 */
public class GrammarWord implements Word {

    public enum GrammarType {
        PRONOUN,VERB,ADVERB,NOUN,PREPOSITION;

        public static GrammarType from(String name) {
            return valueOf(name.toUpperCase());
        }
    }

    private final GrammarType type;
    private final String value;

    private GrammarWord(GrammarType type, String value) {
        this.type = type;
        this.value = value;
    }


    public static GrammarWord from(GrammarType type,String value) {
        if(value.contains(" ")){
            throw new IllegalArgumentException("["+value+"]:spaces not allowed");
        }
        return new GrammarWord(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrammarWord that = (GrammarWord) o;
        return type == that.type &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String getKey() {
        return value+"-"+type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GrammarWord{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
