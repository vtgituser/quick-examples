public class AddN {
  public static void main(String[] args) {
    //        while (true)
    try {
      AddN goodness = new AddN();
      // Tests
      System.out.println(goodness.compute(100));
    } catch (Throwable e) {
      System.err.println(e);
      ;
    }
  }

  long compute(long i) {
    if (i <= 1) return i;
    else return i + compute(i - 1);
  }
}
