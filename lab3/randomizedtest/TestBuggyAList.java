package randomizedtest;
import static org.junit.Assert.assertEquals;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  /*@Test
  public  void testThreeAddThreeRemove() {
      AListNoResizing<Integer> correct = new AListNoResizing<>();
      BuggyAList<Integer> broken = new BuggyAList<>();

      correct.addLast(5);
      correct.addLast(10);
      correct.addLast(15);

      broken.addLast(5);
      broken.addLast(10);
      broken.addLast(15);

      assertEquals(correct.size(), broken.size());

      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
      assertEquals(correct.removeLast(), broken.removeLast());
  }*/

      @Test
      public void randomizedTest() {
          AListNoResizing<Integer> L = new AListNoResizing<>();
          BuggyAList<Integer> broken = new BuggyAList<>();

          int N = 5000;
          for (int i = 0; i < N; i += 1) {
              int operationNumber = StdRandom.uniform(0, 4);

              switch (operationNumber) {
                  case 0:  // addLast
                      int randVal = StdRandom.uniform(0, 100);
                      L.addLast(randVal);
                      broken.addLast(randVal);
                      assertEquals((Integer)L.getLast(), (Integer)broken.getLast());
                      break;

                  case 1:  // size
                      assertEquals((long)L.size(), (long)broken.size());
                      break;

                  case 2:  // getLast
                      if (L.size() > 0) {
                          assertEquals((Integer)L.getLast(), (Integer)broken.getLast());
                      }
                      break;

                  case 3:  // removeLast
                      if (L.size() > 0) {
                          assertEquals((Integer)L.removeLast(), (Integer)broken.removeLast());
                      }
                      break;
              }

              // 每次操作后都检查size
              assertEquals((long)L.size(), (long)broken.size());
          }
      }
  }

