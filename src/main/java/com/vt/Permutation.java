package com.vt;

import java.util.HashSet;
import java.util.Set;

public class Permutation {
    public static void main(String[] args) {
        /// 1 to N only once..
        System.out.println(allNumbers(new int[]{1,3,2}));//true
        System.out.println(allNumbers(new int[]{1,4,1}));
        System.out.println(allNumbers(new int[]{4,1,3}));
        System.out.println(allNumbers(new int[]{2,2,2}));
        System.out.println(allNumbers(new int[]{1}));//true
    }

    public static boolean allNumbers(int[] A) {
        Set<Integer> s = new HashSet<>();

        for (int e : A) {
            if (!s.add(e))
                return false;
        }
        return true;
    }
}
