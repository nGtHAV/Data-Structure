package datastructure.linkedlist.queue;

import datastructure.linkedlist.singlylink.LinkedList;

// Queue (FIFO) that uses LinkedList, items go in at the tail and come out at the head
public class Queue<T> {
    private final LinkedList<T> queue;

    // O(1)
    public Queue() {
        queue = new LinkedList<>();
    }

    // O(1): Kept from Assignment 2, a linked list never gets full so arraySize is only checked
    public Queue(int arraySize) {
        this();
        if (arraySize <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }
    }

    // O(1): Inserts at the tail of the list
    public void insert(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("New item must not be null");
        }

        queue.insertLast(newItem);
    }

    // O(1): Removes the head of the list, returns null if the queue is empty
    public T remove() {
        T removed = queue.peekFirst();
        if (removed == null) return null;
        queue.deleteFirst();
        return removed;
    }

    // O(1): Head of the list, null if the queue is empty
    public T peekFront() {
        return queue.peekFirst();
    }

    // O(1): Tail of the list, null if the queue is empty
    public T peekRear() {
        return queue.peekLast();
    }

    // O(n): Goes through every item once
    @Override
    public String toString() {
        return "Queue[" + queue + "]";
    }

    // O(n): Goes through every item once
    public void display() {
        System.out.print("Front [");
        System.out.print(queue);
        System.out.println("] Rear");
    }
}
