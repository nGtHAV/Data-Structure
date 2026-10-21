package datastructure.linkedlist.doublylink;

// A generic, double-ended doubly linked list. Every link knows both its next and previous link,
// and the list keeps pointers to the first link (head) and the last link (tail), so it can
// add or remove at either end without walking the list. Null items are not allowed.
public class DoublyLinkedList<T> {
    private int count;  // number of links, kept up to date so index checks don't need to walk the list
    private DoublyLink<T> head;
    private DoublyLink<T> tail;

    // Time complexity: O(1), an empty list just has no links yet.
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    // Returns the link at the given index, which must be in range. It starts from whichever
    // end is closer.
    // Time complexity: O(n), but at most n / 2 steps because it starts from the nearer end.
    private DoublyLink<T> traverseByIndex(int index) {
        DoublyLink<T> current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = count - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    // Returns the first item without removing it, or null if the list is empty.
    // Time complexity: O(1), head points straight at the first link.
    public T peekFirst() {
        if (head == null) return null;
        return head.data;
    }

    // Returns the last item without removing it, or null if the list is empty.
    // Time complexity: O(1), tail points straight at the last link.
    public T peekLast() {
        if (tail == null) return null;
        return tail.data;
    }

    // Time complexity: O(1), it only checks the head and tail pointers.
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // Returns the number of items in the list.
    // Time complexity: O(1), count is updated on every insert and delete.
    public int size() {
        return count;
    }

    // Time complexity: O(1), the new link becomes the head and nothing else has to move.
    public void insertFirst(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("Item must not be null");
        }

        DoublyLink<T> newLink = new DoublyLink<>(newItem, head, null);

        if (head == null) {
            tail = newLink;
        } else {
            head.previous = newLink;
        }

        head = newLink;
        count++;
    }

    // Time complexity: O(1), the tail pointer lets us attach the new link without walking the list.
    public void insertLast(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("Item must not be null");
        }

        DoublyLink<T> newLink = new DoublyLink<>(newItem, null, tail);

        if (tail == null) {
            head = newLink;
        } else {
            tail.next = newLink;
        }

        tail = newLink;
        count++;
    }

    // Inserts newItem so it ends up at the given index. index == size() adds it at the end.
    // Throws IndexOutOfBoundsException if index is negative or greater than size().
    // Time complexity: O(n), we walk to the link currently at index (from the nearer end).
    public void insertAt(int index, T newItem) {
        if (newItem == null) {
            throw new NullPointerException("Item must not be null");
        }
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Index out of bounds. Valid range: 0 to " + count);
        }

        if (index == 0) {
            insertFirst(newItem);
            return;
        }

        if (index == count) {
            insertLast(newItem);
            return;
        }

        DoublyLink<T> current = traverseByIndex(index);

        DoublyLink<T> newLink = new DoublyLink<>(newItem, current, current.previous);

        current.previous.next = newLink;
        current.previous = newLink;

        count++;
    }

    // Removes the first item. Returns false if the list is empty.
    // Time complexity: O(1), head just moves to the second link.
    public boolean deleteFirst() {
        if (head == null) return false;

        DoublyLink<T> removedLink = head;
        head = head.next;

        if (head == null) {
            tail = null;
        } else {
            head.previous = null;
        }

        removedLink.next = null;
        count--;
        return true;
    }

    // Removes the last item. Returns false if the list is empty.
    // Time complexity: O(1), tail.previous gives us the new last link straight away.
    public boolean deleteLast() {
        if (head == null) return false;

        DoublyLink<T> removedLink = tail;
        tail = tail.previous;

        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        removedLink.previous = null;
        count--;
        return true;
    }

    // Removes the item at the given index. Returns false if index is out of range.
    // Time complexity: O(n), we walk to the link at index (from the nearer end).
    public boolean deleteAt(int index) {
        if (index < 0 || index >= count) {
            return false;
        }

        if (index == 0) return deleteFirst();
        if (index == count - 1) return deleteLast();

        DoublyLink<T> removedLink = traverseByIndex(index);

        removedLink.previous.next = removedLink.next;
        removedLink.next.previous = removedLink.previous;

        removedLink.next = null;
        removedLink.previous = null;

        count--;
        return true;
    }

    // Removes the first link holding key. Returns false if key isn't in the list.
    // Time complexity: O(n), we may have to check every link before finding key.
    public boolean deleteKey(T key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }

        if (head == null) return false;

        DoublyLink<T> current = head;
        while (!current.data.equals(key)) {
            current = current.next;

            if (current == null) return false;
        }

        if (current == head) return deleteFirst();
        if (current == tail) return deleteLast();

        current.previous.next = current.next;
        current.next.previous = current.previous;

        current.next = null;
        current.previous = null;

        count--;
        return true;
    }

    // Returns the index of the first link holding key, or -1 if it isn't in the list.
    // Time complexity: O(n), we may have to check every link.
    public int find(T key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }

        DoublyLink<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;
            current = current.next;
        }
        return -1;
    }

    // Returns the items from head to tail, for example "1 <-> 2 <-> 3".
    // Time complexity: O(n), each link is visited once.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyLink<T> current = head;
        while (current != null) {
            if (current.next == null) {
                sb.append(current.data);
            } else {
                sb.append(current.data).append(" <-> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Prints the items from head to tail, for example "Displaying forward: Head [1 <-> 2 <-> 3] Tail".
    // Time complexity: O(n), each link is visited once by following next.
    public void displayForward() {
        DoublyLink<T> current = head;
        System.out.print("Displaying forward: Head [");
        while (current != null) {
            if (current.next != null) {
                System.out.print(current.data + " <-> ");
            } else {
                System.out.print(current.data);
            }
            current = current.next;
        }
        System.out.println("] Tail");
    }

    // Prints the items from tail to head, for example "Displaying backward: Tail [3 <-> 2 <-> 1] Head".
    // Time complexity: O(n), each link is visited once by following previous.
    public void displayBackward() {
        DoublyLink<T> current = tail;
        System.out.print("Displaying backward: Tail [");
        while (current != null) {
            if (current.previous != null) {
                System.out.print(current.data + " <-> ");
            } else {
                System.out.print(current.data);
            }
            current = current.previous;
        }
        System.out.println("] Head");
    }
}
