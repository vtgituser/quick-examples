public class Array {
  public static void main(String[] args) {
    System.out.println(false == isSquare(null));
    System.out.println(true == isSquare(new int[4][4]));
    System.out.println(false == isSquare(new int[3][4]));
    System.out.println(false == isSquare(new int[0][0]));
    System.out.println(false == isSquare(new int[0][2]));
    System.out.println(false == isSquare(new int[2][0]));
    System.out.println(false == isSquare(new int[][] {{1, 2}, {1}, {}}));

    System.out.println(isSquare(new int[4][4]));
    System.out.println(isSquare(new int[3][4]));
  }

  public static boolean isSquare(int[][] a) {
    if (null != a && a.length > 0) {
      for (int i = 0; i < a.length; i++) {
        if (a.length != a[i].length) return false;
      }
      return true;
    }
    return false;
  }
}
