/** Implements APIS for linked list based Deque.
 * @author Jiaxian GU on 9/14/2021
 */
public class LinkedListDeque<T> {

    /* Class definition of Node. */
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(T i, StuffNode next, StuffNode prev) {
            item = i;
            this.next = next;
            this.prev = prev;
        }
    }

    /** the first item is at sentinel.next.
      * the last item is at BackSenti.prev. */
    private StuffNode sentiFront;
    private StuffNode sentiBack;
    private int size;

    /* Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentiFront = new StuffNode(null, null, null);
        sentiBack = new StuffNode(null, null, null);
        sentiFront.next = sentiBack;
        sentiFront.prev = sentiBack;
        sentiBack.next = sentiFront;
        sentiBack.prev = sentiFront;
        size = 0;
    }

    /** Add item to the front of LinkedListDeque. */
    public void addFirst(T item) {
        StuffNode p = new StuffNode(item, sentiFront.next, sentiFront);
        sentiFront.next.prev = p;
        sentiFront.next = p;
        size += 1;
    }

    /** Add item to the back of LinkedListDeque. */
    public void addLast(T item) {
        StuffNode p = new StuffNode(item, sentiBack, sentiBack.prev);
        sentiBack.prev.next = p;
        sentiBack.prev = p;
        size += 1;
    }

    /** Remove the first item of LinkedListDeque and return the item.
     * If no such item, return null. */
    public T removeFirst() {
        if (sentiFront.next == sentiBack) {
            return null;
        }
        StuffNode p = sentiFront.next;
        sentiFront.next.next.prev = sentiFront;
        sentiFront.next = sentiFront.next.next;
        size -= 1;
        return p.item;
    }

    /** Remove the last item of LinkedListDeque and return the item.
     * If no such item, return null. */
    public T removeLast() {
        if (sentiBack.prev == sentiFront) {
            return null;
        }
        StuffNode p = sentiBack.prev;
        sentiBack.prev.prev.next = sentiBack;
        sentiBack.prev = sentiBack.prev.prev;
        size -= 1;
        return p.item;
    }

    /** Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Returns True if deque is empty, otherwise false. */
    public boolean isEmpty() {
        if (sentiFront.next == sentiBack) {
            return true;
        }
        return false;
    }

    /** Print the items in the deque from first to last,
      * seperated by a space. */
    public void printDeque() {
        StuffNode p = sentiFront.next;
        while (p != sentiBack) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println();
    }

    /** Get the item at the given index. */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        StuffNode p = sentiFront.next;
        while (index > 0 ) {
            index -= 1;
            p = p.next;
        }
        return p.item;

    }

    /** Get the item at given index, using recrsion */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentiFront.next, index);
    }

    /** Helper method of getRecursive. */
    private T getRecursiveHelper(StuffNode L, int index) {
        if (L.next == null && index != 0) {
            return null;
        } else if (index == 0) {
            return L.item;
        }
        return getRecursiveHelper(L.next, index - 1);

    }
}
