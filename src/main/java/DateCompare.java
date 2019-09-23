import java.util.function.Predicate;

public class DateCompare {
  public static void main(String[] args) {
    String fromDate = "2019-01-01";
    String toDate = "2019-12-31";
    String[] dates =
        new String[] {"2019-02-02", "1999-01-01", "2017-02-02", "2018-01-01", "2019-12-31"};

    String from = "A";
    String to = "R";

    String[] strings = new String[] {"B", "ab", "a", "Q", "5"};

    // write tests here for isInRange

    // print small date from dates

    // print all the dates between from and to date

    // print sorted dates array in descending
  }

  // complete this function
  private static Predicate<String> isInRange(String from, String to) {
    return date -> false;
  }
}
