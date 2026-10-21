package datastructure.linkedlist.singlylink;

// A generic, double-ended singly linked list. It keeps a pointer to the first link (head)
// and the last link (tail), so adding at either end never has to walk the list.
// Null items are not allowed.
public class LinkedList<T> {
    private Link<T> head;
    private Link<T> tail;
    private int count;  // number of links, kept up to date so index checks don't need to walk the list

    // Time complexity: O(1), an empty list just has no links yet.
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
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

        head = new Link<>(newItem, head);

        if (tail == null) {
            tail = head;
        }
        count++;
    }

    // Time complexity: O(1), the tail pointer lets us attach the new link without walking the list.
    public void insertLast(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("Item must not be null");
        }

        Link<T> newLink = new Link<>(newItem);

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
    // Time complexity: O(n), we walk from the head to the link just before index.
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

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = new Link<>(newItem, current.next);
        count++;
    }

    // Removes the first item. Returns false if the list is empty.
    // Time complexity: O(1), head just moves to the second link.
    public boolean deleteFirst() {
        if (head == null) return false;

        Link<T> removedLink = head;
        head = head.next;

        removedLink.next = null;

        if (head == null) {
            tail = null;
        }
        count--;

        return true;
    }

    // Removes the last item. Returns false if the list is empty.
    // Time complexity: O(n), a singly linked list can't go backwards, so we have to walk
    // from the head to find the link before the tail.
    public boolean deleteLast() {
        if (head == null) return false;

        count--;

        if (head == tail) {
            head = null;
            tail = null;
            return true;
        }

        Link<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        tail = current;
        tail.next = null;

        return true;
    }

    // Removes the item at the given index. Returns false if index is out of range.
    // Time complexity: O(n), we walk from the head to the link just before index.
    public boolean deleteAt(int index) {
        if (index < 0 || index >= count) {
            return false;
        }

        if (index == 0) return deleteFirst();
        if (index == count - 1) return deleteLast();

        Link<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Link<T> removedLink = current.next;
        current.next = removedLink.next;
        removedLink.next = null;

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

        Link<T> previous = null;
        Link<T> current = head;

        while (!current.data.equals(key)) {
            previous = current;
            current = current.next;

            if (current == null) return false;
        }

        if (current == head) return deleteFirst();
        if (current == tail) return deleteLast();

        previous.next = current.next;

        current.next = null;

        count--;
        return true;
    }

    // Returns the index of the first link holding key, or -1 if it isn't in the list.
    // Time complexity: O(n), we may have to check every link.
    public int find(T key) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }

        Link<T> current = head;
        for (int i = 0; current != null; i++) {
            if (current.data.equals(key)) return i;
            current = current.next;
        }
        return -1;
    }

    // Returns the items from head to tail, for example "1 -> 2 -> 3".
    // Time complexity: O(n), each link is visited once.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Link<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // Prints the items from head to tail, for example "LinkedList: Head [1 -> 2 -> 3] Tail".
    // Time complexity: O(n), each link is visited once.
    public void display() {
        Link<T> current = head;
        System.out.print("LinkedList: Head [");
        while (current != null) {
            if (current.next == null) {
                System.out.print(current.data);
            } else {
                System.out.print(current.data + " -> ");
            }
            current = current.next;
        }
        System.out.println("] Tail");
    }
}
