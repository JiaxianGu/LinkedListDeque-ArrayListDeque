/**
 * Implement APIS for array based deque.
 * @author Jiaxian Gu on 9/18/2021
 */
public class ArrayDeque<Item> {

    /**
     * actualLength is the length of deque.
     * capacity is the length of the available
     * nextFirst is the position where addFirst() will add element.
     * nextLast is the position where addLast() will add element.
     * items is the pointer points to the array object.
     */
    private int actualLength;
    private int capacity;
    private int nextFirst;
    private int nextLast;
    private Item[] items;

    /** Create an empty array deque. */
    public ArrayDeque() {
        capacity = 8;
        actualLength = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (Item[]) new Object[8];
    }

    /** Resize the capacity of array when array usage factor is less than 25%. */
    private void resize(int cap) {
        Item[] a = (Item[]) new Object[cap];
        if (nextFirst <= nextLast) {
            int newNextFirst = cap / 2 - actualLength / 2;
            int newNextLast = newNextFirst + actualLength;
            System.arraycopy(items, nextFirst + 1, a, newNextFirst + 1, actualLength);
            nextFirst = newNextFirst;
            nextLast = newNextLast;
        } else {
            int newNextLast = nextLast;
            int newNextFirst = cap - 1 - (actualLength - nextLast);
            System.arraycopy(items, 0, a, 0, newNextLast);
            System.arraycopy(items, nextFirst + 1, a, newNextFirst + 1, actualLength - nextLast);
            nextFirst = newNextFirst;
            nextLast = newNextLast;
        }
        capacity = cap;
        items = a;
    }

    /** Print the items in the array deque from first to last. */
    public void printDeque() {
        if (nextFirst <= nextLast) {
            int p = nextFirst + 1;
            while (p < nextLast) {
                System.out.print(items[p]);
                System.out.print(" ");
                p += 1;
            }
        } else {
            for (int p = nextFirst + 1; p <= capacity - 1; p += 1) {
                System.out.print(items[p]);
                System.out.print(" ");
            }
            for (int p = 0; p < nextLast; p += 1) {
                System.out.print(items[p]);
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    /** Add item to the front of deque. */
    public void addFirst(Item x) {
        double rate = Double.valueOf(actualLength + 1) / Double.valueOf(capacity);
        if (rate > 0.9) {
            resize(capacity * 2);
        }
        if (nextFirst <= nextLast) {
            items[nextFirst] = x;
            actualLength += 1;
            if (nextFirst == 0) {
                nextFirst = capacity - 1;
            } else {
                nextFirst -= 1;
            }
        } else {
            items[nextFirst] = x;
            actualLength += 1;
            nextFirst -= 1;
        }
    }

    /** Add item to the back of the deque. */
    public void addLast(Item x) {
        double rate = Double.valueOf(actualLength + 1) / Double.valueOf(capacity);
        if (rate > 0.8) {
            resize(capacity * 2);
        }
        items[nextLast] = x;
        actualLength += 1;
        if (nextFirst <= nextLast) {
            if (nextLast == capacity - 1) {
                nextLast = 0;
            } else {
                nextLast += 1;
            }
        } else {
            nextLast += 1;
        }
    }

    /** Remove and return the item at the front of the deque. If no such item, return null. */
    public Item removeFirst() {
        double rate = Double.valueOf(actualLength - 1) / Double.valueOf(capacity);
        if (rate < 0.25) {
            resize(capacity / 2);
        }
        if (actualLength == 0) {
            System.out.println("Array already empty");
            return null;
        }
        if (nextFirst == capacity - 1) {
            items[0] = null;
            nextFirst = 0;
        }
        Item x = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst += 1;
        actualLength -= 1;
        return x;
    }

    /** Remove and return the item at the back of the deque. If no such item, return null. */
    public Item removeLast() {
        double rate = Double.valueOf(actualLength - 1) / Double.valueOf(capacity);
        if (rate < 0.25) {
            resize(capacity / 2);
        }
        if (actualLength == 0) {
            System.out.println("Array already empty");
            return null;
        }
        if (nextLast == 0) {
            items[capacity - 1] = null;
            nextLast = capacity - 1;
        }
        Item x = items[nextFirst + 1];
        items[nextLast - 1] = null;
        nextLast -= 1;
        actualLength -= 1;
        return x;
    }

    /** Return the number of items in the deque. */
    public int size() {
        return actualLength;
    }

    /** Get the item at the given index. */
    public Item get(int index) {
        if (index >= actualLength) {
            System.out.println("Index out of range.");
            return null;
        }
        if (nextFirst <= nextLast) {
            return items[nextFirst + index + 1];
        } else {
            int newIndex = (nextFirst + index + 1) % (capacity);
            return items[newIndex];
        }
    }

}
