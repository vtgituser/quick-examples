public class Swap {
  public static void main(String[] args) {
    int a = -1;
    int y = 5;

    int originalA = a;
    int originalY = y;

    // swap

    a = a + y;
    y = a - y;
    a = a - y;

    System.out.println(a == originalY);
    System.out.println(y == originalA);
  }
}
