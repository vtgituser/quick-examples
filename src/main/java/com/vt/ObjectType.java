package com.vt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectType {

  public static void main(String[] args) {
    int[] array = new int[] {1, 2};
    System.out.println(array.getClass().isArray());
    List<String> list = new ArrayList<>();
    System.out.println(Collection.class.isAssignableFrom(list.getClass()));
    Map<String, String> map = new HashMap<>();
    System.out.println(map.getClass());
    String string = "";
    System.out.println(string.getClass());
  }
}
