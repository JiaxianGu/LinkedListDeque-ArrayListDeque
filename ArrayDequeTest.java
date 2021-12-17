import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ArrayDequeTest {

    @Test
    public void testArrayDeque1() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        ad1.addFirst(6);
        ad1.addFirst(5);
        ad1.addFirst(4);
        ad1.addFirst(3);
        ad1.addFirst(2);
        ad1.addFirst(1);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.printDeque();

    }

    @Test
    public void testArrayDeque2() {
        ArrayDeque<Integer> ad2 = new ArrayDeque<Integer>();
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);
        ad2.addLast(4);
        ad2.addLast(5);
        ad2.addLast(6);
        ad2.addLast(7);
        ad2.addFirst(0);
        ad2.addFirst(-1);
        ad2.printDeque();
        ad2.removeFirst();
        ad2.removeFirst();
        ad2.printDeque();
        ad2.removeLast();
        ad2.removeLast();
        ad2.printDeque();
        assertEquals("The 2nd element is now", (Integer)3, ad2.get(2));
    }

}
