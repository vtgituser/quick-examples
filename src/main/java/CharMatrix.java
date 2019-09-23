import java.util.Arrays;

public class CharMatrix {
  public static void main(String[] args) {
    char[][] array = new char[5][5];
    char num = 'A';
    int row = array.length;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = num++;
      }
      System.out.println(Arrays.toString(array[i]));
    }
    System.out.println();
    for (int i = 0; i < row; i++) {
      int col = array[i].length;
      for (int j = 0; j < col; j++) {
        if (i == j) {
          char temp = array[i][j];
          array[i][j] = array[i][col - i - 1];
          array[i][col - i - 1] = temp;
          break;
        }
      }
      System.out.println(Arrays.toString(array[i]));
    }
  }
}
