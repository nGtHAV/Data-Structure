package datastructure.linkedlist.doublylink;

// One link of DoublyLinkedList, holds the data and the next and previous links
class DoublyLink<T> {
    T data;
    DoublyLink<T> next;
    DoublyLink<T> previous;

    // O(1)
    DoublyLink(T data) {
        this(data, null, null);
    }

    // O(1)
    DoublyLink(T data, DoublyLink<T> next, DoublyLink<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}
