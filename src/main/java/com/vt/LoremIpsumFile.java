package com.vt;

import de.svenjacobs.loremipsum.LoremIpsum;
import java.io.RandomAccessFile;

public class LoremIpsumFile {

  public static void main(String[] args) throws Exception {
    LoremIpsum loremIpsum = new LoremIpsum();
    try (RandomAccessFile f = new RandomAccessFile("GCSNemoE1" + loremIpsum.getWords(1, 5), "rw")) {
      f.setLength(6 * 1400);
      f.writeBytes(loremIpsum.getWords(50000));
    }
  }
}
