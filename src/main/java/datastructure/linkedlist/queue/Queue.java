package datastructure.linkedlist.queue;

import datastructure.linkedlist.singlylink.LinkedList;

// A generic FIFO queue built on LinkedList. Items join at the tail (rear) and leave from the
// head (front). Both ends are O(1) because the list keeps head and tail pointers, and we never
// need deleteLast, which is the slow end of a singly linked list.
public class Queue<T> {
    private final LinkedList<T> queue;

    // Time complexity: O(1), it just creates an empty list.
    public Queue() {
        queue = new LinkedList<>();
    }

    // Same constructor as the array version from Assignment 2, so code written for that still works.
    // A linked list has no fixed capacity and never needs resizing, so arraySize is only checked.
    // Throws IllegalArgumentException if arraySize is less than 1, like the Assignment 2 version.
    // Time complexity: O(1), it just creates an empty list.
    public Queue(int arraySize) {
        this();
        if (arraySize <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }
    }

    // Time complexity: O(1), the new item is added at the tail of the list.
    public void insert(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("New item must not be null");
        }

        queue.insertLast(newItem);
    }

    // Removes and returns the front item, or null if the queue is empty.
    // Time complexity: O(1), the front item is the head of the list.
    public T remove() {
        T removed = queue.peekFirst();
        if (removed == null) return null;
        queue.deleteFirst();
        return removed;
    }

    // Returns the front item without removing it, or null if the queue is empty.
    // Time complexity: O(1), the front item is the head of the list.
    public T peekFront() {
        return queue.peekFirst();
    }

    // Returns the rear item without removing it, or null if the queue is empty.
    // Time complexity: O(1), the rear item is the tail of the list.
    public T peekRear() {
        return queue.peekLast();
    }

    // Returns the items from front to rear, for example "Queue[1 -> 2 -> 3]".
    // Time complexity: O(n), every item is visited once.
    @Override
    public String toString() {
        return "Queue[" + queue + "]";
    }

    // Prints the items from front to rear, for example "Front [1 -> 2 -> 3] Rear".
    // Time complexity: O(n), every item is visited once.
    public void display() {
        System.out.print("Front [");
        System.out.print(queue);
        System.out.println("] Rear");
    }
}
