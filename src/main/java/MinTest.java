import java.util.Arrays;

public class MinTest {
  public static void main(String[] args) {
    Emp[] emps =
        new Emp[] {
          new Emp(6, "2", "F"),
          new Emp(13, "4", "A"),
          new Emp(3, "1", "C"),
          new Emp(4, "1", "D"),
          new Emp(10, "4", "J"),
          new Emp(5, "4", "E"),
          new Emp(7, "3", "G"),
          new Emp(11, "2", "K"),
          new Emp(2, "2", "B"),
          new Emp(8, "1", "H"),
          new Emp(9, "3", "I"),
          new Emp(12, "1", "L")
        };

    System.out.println(Arrays.stream(emps).findFirst().get());
    System.out.println(emps[0]);
    System.out.println("Done.");
  }
}
