package com.vt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArrayStreamJoin {
    public static void main(String[] args) {
        Stream.<Emp>builder().add(new Emp("Emp1", new String[]{"a", "b", "aba"}))
                .add(new Emp("Emp2", new String[]{"c", "d", null, "ed"}))
                .add(new Emp("Emp3", new String[]{"e", "f", "gg", "hh"}))
                .build();
        //print all 1 letter books in one string - abcdef
    }
    static class Emp{
        public String name;
        public String[] books;

        public Emp(String name, String[] books) {
            this.name = name;
            this.books = books;
        }
    }
}
