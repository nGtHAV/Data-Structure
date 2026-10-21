package datastructure.linkedlist.doublylink;

// Double-ended doubly linked list, every link has a reference to the next and the previous link
public class DoublyLinkedList<T> {
    private int count;  // Number of links in the list
    private DoublyLink<T> head;
    private DoublyLink<T> tail;

    // O(1)
    public DoublyLinkedList() {
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    // O(n): Starts from whichever end is closer, so it takes at most n/2 steps
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

    // O(1): Head is the first link
    public T peekFirst() {
        if (head == null) return null;
        return head.data;
    }

    // O(1): Tail is the last link
    public T peekLast() {
        if (tail == null) return null;
        return tail.data;
    }

    // O(1)
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // O(1): count is updated on every insert and delete
    public int size() {
        return count;
    }

    // O(1): The new link just becomes the head
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

    // O(1): The new link just becomes the tail
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

    // Best case O(1): Inserting at index 0 or at the end
    // Worst case O(n): Walking to the index, from whichever end is closer
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

    // O(1): Head just moves to the next link
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

    // O(1): tail.previous is the new last link, so no walking is needed
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

    // Best case O(1): Deleting the first or the last link
    // Worst case O(n): Walking to the index, from whichever end is closer
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

    // Best case O(1): Key is at the head
    // Worst case O(n): Key is at the end or not in the list
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

    // Best case O(1): Key is at the head
    // Worst case O(n): Key is at the end or not in the list
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

    // O(n): Goes through every link once
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

    // O(n): Follows next from the head to the tail
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

    // O(n): Follows previous from the tail to the head
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
