package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(17, 3, 2, 2, 1);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 9 -> 4 -> 4 -> 1", lst.toString());
        assertTrue(changed);
    }
}
