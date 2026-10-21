package datastructure.linkedlist.singlylink;

// Double-ended singly linked list, keeps a reference to both the head and the tail
public class LinkedList<T> {
    private Link<T> head;
    private Link<T> tail;
    private int count;  // Number of links in the list

    // O(1)
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
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

        head = new Link<>(newItem, head);

        if (tail == null) {
            tail = head;
        }
        count++;
    }

    // O(1): We have the tail so there is no need to go through the list
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

    // Best case O(1): Inserting at index 0 or at the end
    // Worst case O(n): Walking to the link before the index
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

    // O(1): Head just moves to the next link
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

    // O(n): There is no previous reference, so we walk from the head to find the link before the tail
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

    // Best case O(1): Deleting at index 0
    // Worst case O(n): Walking to the link before the index
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

    // Best case O(1): Key is at the head
    // Worst case O(n): Key is at the end or not in the list
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

    // Best case O(1): Key is at the head
    // Worst case O(n): Key is at the end or not in the list
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

    // O(n): Goes through every link once
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

    // O(n): Goes through every link once
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
