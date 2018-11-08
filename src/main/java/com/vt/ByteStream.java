package com.vt;

import java.util.stream.Stream;

public class ByteStream {
  public static void main(String[] args) {
    Byte[] data = new Byte[] {65, 66, 67};
    Byte first = Stream.of(data).findFirst().get();
    System.out.println(first);
  }
}
