package datastructure.linkedlist.doublylink;

// One link (node) of a DoublyLinkedList: the item it holds and references to the next and previous links.
class DoublyLink<T> {
    T data;
    DoublyLink<T> next;
    DoublyLink<T> previous;

    // Time complexity: O(1), it only stores the item. next and previous start as null.
    DoublyLink(T data) {
        this(data, null, null);
    }

    // Time complexity: O(1), it only stores the item and the two references.
    DoublyLink(T data, DoublyLink<T> next, DoublyLink<T> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
}
