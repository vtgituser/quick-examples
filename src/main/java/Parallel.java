public class Parallel {
  private static int anInt;

  public static void main(String[] args) {
    for (int i = 0; i <= 100; i++) {
      new Thread(getRunnable(i)).start();
    }
    System.out.println(anInt);
  }

  private static Runnable getRunnable(int i) {
    return () -> work(i);
  }

  private static void work(int i) {
    /* if(i % 2 == 1) {
        try {
            MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
    anInt = i;
  }
}
