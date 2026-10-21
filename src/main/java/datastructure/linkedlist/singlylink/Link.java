package datastructure.linkedlist.singlylink;

// One link (node) of a LinkedList: the item it holds and a reference to the next link.
class Link<T> {
    T data;
    Link<T> next;

    // Time complexity: O(1), it only stores the item. next starts as null.
    Link(T data) {
        this(data, null);
    }

    // Time complexity: O(1), it only stores the item and the reference to the next link.
    Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }
}
