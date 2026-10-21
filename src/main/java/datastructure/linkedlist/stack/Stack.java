package datastructure.linkedlist.stack;

import datastructure.linkedlist.singlylink.LinkedList;

// A generic LIFO stack built on LinkedList. The top of the stack is the head of the list,
// so push, pop and peek all work on the first link and are O(1).
public class Stack<T> {
    private final LinkedList<T> stack;

    // Time complexity: O(1), it just creates an empty list.
    public Stack() {
        this.stack = new LinkedList<>();
    }

    // Same constructor as the array version from Assignment 2, so code written for that still works.
    // A linked list has no fixed capacity and never needs resizing, so arraySize is only checked.
    // Throws IllegalArgumentException if arraySize is less than 1, like the Assignment 2 version.
    // Time complexity: O(1), it just creates an empty list.
    public Stack(int arraySize) {
        this();
        if (arraySize <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }
    }

    // Time complexity: O(1), the new item is added at the head of the list.
    public void push(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("New item must not be null");
        }

        stack.insertFirst(newItem);
    }

    // Removes and returns the top item, or null if the stack is empty.
    // Time complexity: O(1), the top item is the head of the list.
    public T pop() {
        T removed = stack.peekFirst();
        if (removed == null) return null;
        stack.deleteFirst();
        return removed;
    }

    // Returns the top item without removing it, or null if the stack is empty.
    // Time complexity: O(1), the top item is the head of the list.
    public T peek() {
        return stack.peekFirst();
    }

    // Returns the items from top to bottom, for example "Stack[3 -> 2 -> 1]".
    // Time complexity: O(n), every item is visited once.
    @Override
    public String toString() {
        return "Stack[" + stack + "]";
    }

    // Prints the items from top to bottom, for example "Top [3 -> 2 -> 1]".
    // Time complexity: O(n), every item is visited once.
    public void display() {
        System.out.print("Top [");
        System.out.print(stack);
        System.out.println("]");
    }
}
