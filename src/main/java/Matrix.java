import java.util.Arrays;

public class Matrix {
  public static void main(String[] args) {
    int[][] array = new int[6][6];
    //        String[][] array = new String[4][4];
//    int num = 1;
    int row = array.length;
    for (int i = 0; i <row; i++) {
      int col = array[i].length;
      //            if(i%2==0)
      for (int j = 0; j < col; j++) {
        //                if (i==j)
        //                array[i][j] = String.format("%02d", num++);
        array[i][j] = (i*row)+(j+1);
      }
      System.out.println(Arrays.toString(array[i]));
    }
    System.out.println();
    for (int i = 0; i < row; i++) {
      int col = array[i].length;
      for (int j = 0; j < col; j++) {
        if (i == j) {
          int temp = array[i][j];
          array[i][j] = array[i][col - i - 1];
          array[i][col - i - 1] = temp;
          break;
        }
      }
      System.out.println(Arrays.toString(array[i]));
    }
  }
}
