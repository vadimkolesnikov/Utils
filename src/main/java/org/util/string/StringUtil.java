package org.util.string;

import com.sun.org.apache.xerces.internal.util.MessageFormatter;

import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;

public class StringUtil implements Cloneable, Comparable<StringUtil>, Iterable<Character>, Comparator<StringUtil> {

    private final StringBuilder builder;

    public enum Equality {
        REFERENCE, // only reference should match
        LENGTH, // strings are equal if length same
        VALUE, // strings are equal if value is the same -- default one
        IGNORE_CASE // string are equal if value is equal but ignoring case differences

        // placeholder
        ;
    }

    public enum Case {
        LOWER,// "abc xyz"
        UPPER, // "ABC XYZ"
        CAMEL, // "Abc Xyz"
        TITLE, // "Abc xyz."

        // placeholder
        ;
    }

    /**
     * StringUtil constructor.
     * Initialize "StringBuilder builder" by concatenate "String data"
     *
     * @param data
     * @returns new StringUtil (String data)
     */
    public StringUtil(String data) {
        this.builder = new StringBuilder(data);
    }

    /**
     * StringUtil constructor.
     * Initialize "StringBuilder builder" by concatenate "StringBuffer data"
     *
     * @param data
     * @returns new StringUtil (StringBuffer data)
     */

    public StringUtil(StringBuffer data) {
        this.builder = new StringBuilder(data);

    }

    /**
     * StringUtil constructor.
     * Initialize "StringBuilder builder" by concatenate "StringBuilder data"
     * new StringUtil (StringBuilder data)
     *
     * @param data
     * @returns void
     */

    public StringUtil(StringBuilder data) {
        this.builder = new StringBuilder(data);
    }

    /**
     * StringUtil constructor.
     * Initialize "StringBuilder builder" by concatenate "char [] data"
     *
     * @param data
     * @returns new StringUtil (char [] data)
     */

    public StringUtil(char[] data) {
        this.builder = new StringBuilder().append(data);
    }


    /**
     * StringUtil constructor.
     * Initialize "StringBuilder builder" by concatenate "byte [] data"
     *
     * @param data
     * @returns new StringUtil (byte [] data)
     */


    public StringUtil(byte[] data, Charset charset) {
        this(new String(data, charset));
    }

    /// ALL METHODS MUST RETURN NEW EXEMPLAR OF StringUtil which replace old StringUtil (other object)

    /**
     * void append StringBuffer data
     * this method append StringBuffer data to field StringBuilder builder
     *
     * @param data
     * @returns void
     */
    public StringUtil append(StringBuffer data) {
        return new StringUtil(new StringBuilder(builder).append(data));
    }

    /**
     * void append String data
     * this method append String data to field StringBuilder builder
     *
     * @param data
     * @returns void
     */
    public StringUtil append(String data) {
        // wrong - inefficient
        return new StringUtil(builder.toString().concat(data));
    }

    /**
     * void append StringBui
     * lder data
     * this method append StringBuilder data to field StringBuilder builder
     *
     * @param data
     * @returns void
     */
    public StringUtil append(StringBuilder data) {
        // wrong - inefficient
        //this.builder.append(data);
//        return new StringUtil(this.builder.toString() + data);
        return new StringUtil(builder.toString().concat(String.valueOf(data)));
    }

    /**
     * void append  data
     * this method append StringBuilder data to field StringBuilder builder
     *
     * @param data
     * @returns void
     */
    public StringUtil append(char[] data) {
        // wrong - inefficient
        return new StringUtil(builder.toString().concat(String.valueOf(data)));
    }

    /**
     * void append data from 'byte[] data'  with 'Charset charset' over call of constructor with
     * parameters types (byte[] data, Charset charset)
     *
     * @param data
     * @returns void
     */
    public StringUtil append(byte[] data, Charset charset) {
        return new StringUtil(builder.toString() + new StringUtil(data, charset).toString());
    }


    /**
     * void prepend StringBuffer data
     * this method prepend data to "StringBuffer data" from parameters
     *
     * @param data
     * @returns void
     */
    public StringUtil prepend(StringBuffer data) {
        // wrong
//        return new StringUtil(data.append(builder));
        return new StringUtil(String.valueOf(data).concat(builder.toString()));
    }

    /**
     * void prepend String data
     * this method prepend data from parameter to field builder
     *
     * @param data
     * @returns void
     */
    public StringUtil prepend(String data) {
        // wrong - inefficient
        return new StringUtil(data.concat(builder.toString()));
//        return new StringUtil(data.concat(String.valueOf(builder)));

    }

    /**
     * void prepend StringBuilder data
     * this method prepend StringBuilder data to field StringBuilder builder
     *
     * @param data
     * @returns void
     */
    public StringUtil prepend(StringBuilder data) {
        // wrong
//        data.append(builder);
//        return new StringUtil(data);
        return new StringUtil(data.toString().concat(builder.toString()));

    }


    /**
     * void prepend data
     * this method prepend 'char[] data' to field 'StringBuilder builder'
     * <p>
     * 1. old data 'StringBuilder builder' append to new data 'char[] data'
     * 1.1 convert 'char data' to type 'String'
     * 1.1.1 create variable 'String temp'
     * 1.1.2 to temp add new data from parameters
     * 1.2 temp + old data from field 'builder'
     * <p>
     * 2.
     *
     * @param data
     * @returns void
     */
    public StringUtil prepend(char[] data) {
        // wrong - inefficient
//        String temp = new String(data);
//        return new StringUtil(temp.concat(builder.toString()));
//        return new StringUtil(String.copyValueOf(data).concat(builder.toString()));
        return new StringUtil(String.valueOf(data).concat(builder.toString()));

    }

    /**
     * void append data from 'byte[] data'  with 'Charset charset' over call of constructor with
     * parameters types (byte[] data, Charset charset)
     * 1. prepend new data from 'byte[] data' to old data 'StringBuilder builder' and create new instance of
     * StringUtil
     * 1.1 take new data 'byte[] data' and concatenate with old data 'StringBuilder builder'
     * 1.1.1 'byte[] data' convert to type compatible with 'StringBuilder builder'
     * 1.1.2 concatenate new data with old data
     * 1.2 create new instance StringUtil by call constructor with new data as parameter
     *
     * @param data
     * @returns void
     */
    public StringUtil prepend(byte[] data, Charset charset) {
        // wrong - inefficient
        String valentina = new String(data, charset);
        return new StringUtil(valentina.concat(builder.toString()));
    }

    /**
     * returns new instance of StringUtil which have reversed order of its data(field builder)
     *
     * @returns StringUtil
     */
    public StringUtil reverse() {
        return new StringUtil(builder.reverse());
    }

    /**
     * returns char with index
     *
     * @returns StringUtil
     */
    // TODO: getCharacter
    public char get(int index) {
        return builder.charAt(index);
    }

    /**
     * in result of execute this method argument 'velue' will be set in field builder to 'index'
     * 1. set value in field builder with index
     * 1.1 builder
     *
     * @arg int index, char value
     * @returns void
     */
    // TODO: setCharacter
    public StringUtil set(int index, char value) {
        builder.toString().toCharArray()[index] = value;
        return new StringUtil(builder);
    }

    /**
     * in result of execution of this method will be returned char[] and which
     * will be contains all elements field builder from 'int index' to 'int to'
     *
     * @arg int from, int to
     * @returns char[]
     */
    // TODO: rename to characters
    public char[] characters(int from, int to) {
        return builder.toString().substring(from, to).toCharArray();
    }

    // TODO: implement newly added method
    public StringUtil range(int from, int to) {
        return new StringUtil(builder.toString().substring(from, to).toCharArray());
    }

    /**
     * in result of execution of this method will be insert value of argument 'int value'
     * in field 'builder' in place 'index' with create new instance StringUtil
     *
     * @arg int index, int value
     * @returns void
     */
    public StringUtil insert(int index, char value) {
        return new StringUtil(builder.insert(index, value));

    }

    public StringUtil insert(int index, String value) {
        return new StringUtil(builder.insert(index, value));
    }

    public StringUtil insert(int index, char[] value) {
        return new StringUtil(builder.insert(index, value));
    }

    public StringUtil insert(int index, StringBuilder value) {
        return new StringUtil(builder.insert(index, value));
    }

    public StringUtil insert(int index, StringBuffer value) {
        return new StringUtil(builder.insert(index, value));
    }

    public StringUtil insert(int index, StringUtil value) {
        return new StringUtil(builder.insert(index, value));
    }

    public <T> T convert(Function<StringUtil, T> function) {
        return function.apply(this);
    }

    public char[] characters() {
        // TODO: use builder.getChars
        return builder.toString().toCharArray();
    }

    @Override
    public StringUtil clone() {
        return new StringUtil(this.builder);
    }

    public String string() {
        return builder.toString();
    }

    public StringBuilder builder() {
        return new StringBuilder(builder);
    }

    public StringBuffer buffer() {
        return new StringBuffer(builder);
    }

    public boolean isNumber() {
        for (int i = 0; i < builder.length(); i++) {
            if (!Character.isDigit(builder.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isBool() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public StringUtil soundex() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compareTo(StringUtil o) {
        throw new UnsupportedOperationException();
    }

    public byte[] bytes() {
        return builder.toString().getBytes();
    }


    public byte[] bytes(Charset charset) {
        return builder.toString().getBytes(charset);
    }

    private static class BuilderIterator implements Iterator<Character> {

        private final StringBuilder builder;
        private final int cursor;


        public BuilderIterator(StringBuilder builder) {
            this.builder = builder;
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Character next() {
            return null;
        }
    }

    @Override
    public Iterator<Character> iterator() {
        return new BuilderIterator(builder);
    }

    public StringUtil remove(int from, int to) {
        throw new UnsupportedOperationException();
    }

    // returns amount of occurences
    public int find(char value) {
        throw new UnsupportedOperationException();
    }

    public int find(String value) {
        throw new UnsupportedOperationException();
    }

    public int find(char[] value) {
        throw new UnsupportedOperationException();
    }

    public int find(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public int find(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public int find(StringUtil value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(char value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(String value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(char[] value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public boolean contains(StringUtil value) {
        throw new UnsupportedOperationException();
    }


    public StringUtil format(Object... parameters) {
        return new StringUtil(String.format(builder.toString(), parameters));
    }

    public StringUtil messageFormat(Object... parameters) {
        return new StringUtil(MessageFormat.format(builder.toString(), parameters));
    }

    public Stream<Character> stream() {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return builder.length();
    }

    public StringUtil[] split(char value) {
        throw new UnsupportedOperationException();
    }

    public StringUtil[] split(String value) {
        throw new UnsupportedOperationException();
    }

    public StringUtil[] split(char[] value) {
        throw new UnsupportedOperationException();
    }

    public StringUtil[] split(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public StringUtil[] split(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public StringUtil[] split(StringUtil value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Split string util into parts which has size <= size
     * e.g. 3, 3, 3, 2
     *
     * @param size
     * @return
     */
    public StringUtil[] split(int size) {
        throw new UnsupportedOperationException();
    }

    public StringUtil left(int length) {
        throw new UnsupportedOperationException();
    }

    public StringUtil left(int length, int from) {
        throw new UnsupportedOperationException();
    }

    public StringUtil right(int length) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(char value) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(String value) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(char[] value) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public boolean begins(StringUtil value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(char value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(String value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(char[] value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public boolean ends(StringUtil value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return builder.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        throw new UnsupportedOperationException();
    }

    // this method group uses default equality type
    public boolean equals(String value) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(char[] value) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringBuilder value) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringBuffer value) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringUtil value) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(String value, Equality equality) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(char[] value, Equality equality) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringBuilder value, Equality equality) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringBuffer value, Equality equality) {
        throw new UnsupportedOperationException();
    }

    public boolean equals(StringUtil value, Equality equality) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int compare(StringUtil o1, StringUtil o2) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        StringUtil a = new StringUtil("abc");

        StringBuffer b = new StringBuffer().append("b");
        System.out.println(a); // a
        System.out.println(b); // b

        StringUtil ab = a.append(b);
        System.out.println(a); // a
        System.out.println(b); // b
        System.out.println(ab); // ab
        System.out.println(ab.reverse());
        System.out.println(ab.prepend("sd"));
        System.out.println(ab.get(1));
        System.out.println(a.insert(1, a));

    }
}