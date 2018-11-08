package com.vt;

import javax.xml.bind.DatatypeConverter;

public class Nibbles {
  public static void main(String[] args) {
    try {
      //      byte[] data = DatatypeConverter.parseHexBinary("A42002");
      // System.out.println(new String(DatatypeConverter.parseHexBinary("A42002")));
      byte[] data = DatatypeConverter.parseHexBinary("A4");
      // System.out.println(data.length);
      for (byte b : data) {
        int high = (b & 0xf0) >> 4;
        int low = b & 0xf;
        System.out.format("%x%n", high);
        System.out.format("%x%n", low);

        System.out.println(
            String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
