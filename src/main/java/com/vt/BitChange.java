package com.vt;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

import java.io.UnsupportedEncodingException;

public class BitChange {
  public static void main(String[] args) throws UnsupportedEncodingException {
    String s = new String("            ");
    byte[] bytes = s.getBytes();
    for (int i = 0; i < bytes.length; i++) {
      //            System.out.println("before " + i + ":" + String.format("%8s",
      // Integer.toString(Byte.toUnsignedInt(bytes[i]), 2)).replace(' ', '0'));
      bytes[i] = 0;
      //            System.out.println("after " + i + ":" + String.format("%8s",
      // Integer.toString(Byte.toUnsignedInt(bytes[i]), 2)).replace(' ', '0'));
    }
    String a = "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0000";
    System.out.println(a);
    System.out.println(a.equalsIgnoreCase(new String(bytes)));
    bytes[1] |= (1 << 3);
    bytes[1] |= (1 << 1);
    System.out.println("updated:" + printHexBinary(bytes));
    for (int i = 0; i < bytes.length; i++) {
      System.out.print(String.format("%c", bytes[i]));
    }

    /* byte my_byte = 0;
    System.out.println("before :" + String.format("%8s", Integer.toString(toUnsignedInt(my_byte), 2)).replace(' ', '0'));
    my_byte |=(1<<2);
    System.out.println("after :" + String.format("%8s", Integer.toString(toUnsignedInt(my_byte), 2)).replace(' ', '0'));*/
  }
}
