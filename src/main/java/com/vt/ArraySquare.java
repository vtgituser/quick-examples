package com.vt;

public class ArraySquare {

  public static void main(String[] args) {
    int[][] array = new int[5][5];
    int length = array.length;
    System.out.println(length);
    boolean isSquare = length == array[length - 1].length;
    System.out.println("Is it a square: " + isSquare);
    if (isSquare) {
      System.out.println("diagonal length: " + Math.sqrt(2)*length);
    }
  }
}
