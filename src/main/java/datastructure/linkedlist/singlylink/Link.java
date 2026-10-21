package datastructure.linkedlist.singlylink;

// One link of LinkedList, holds the data and the next link
class Link<T> {
    T data;
    Link<T> next;

    // O(1)
    Link(T data) {
        this(data, null);
    }

    // O(1)
    Link(T data, Link<T> next) {
        this.data = data;
        this.next = next;
    }
}
