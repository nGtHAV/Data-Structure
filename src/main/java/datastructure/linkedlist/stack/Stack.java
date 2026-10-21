package datastructure.linkedlist.stack;

import datastructure.linkedlist.singlylink.LinkedList;

// Stack (LIFO) that uses LinkedList, the top of the stack is the head of the list
public class Stack<T> {
    private final LinkedList<T> stack;

    // O(1)
    public Stack() {
        this.stack = new LinkedList<>();
    }

    // O(1): Kept from Assignment 2, a linked list never gets full so arraySize is only checked
    public Stack(int arraySize) {
        this();
        if (arraySize <= 0) {
            throw new IllegalArgumentException("arraySize must be greater than 0");
        }
    }

    // O(1): Inserts at the head of the list
    public void push(T newItem) {
        if (newItem == null) {
            throw new NullPointerException("New item must not be null");
        }

        stack.insertFirst(newItem);
    }

    // O(1): Removes the head of the list, returns null if the stack is empty
    public T pop() {
        T removed = stack.peekFirst();
        if (removed == null) return null;
        stack.deleteFirst();
        return removed;
    }

    // O(1): Returns the head of the list, or null if the stack is empty
    public T peek() {
        return stack.peekFirst();
    }

    // O(n): Goes through every item once
    @Override
    public String toString() {
        return "Stack[" + stack + "]";
    }

    // O(n): Goes through every item once
    public void display() {
        System.out.print("Top [");
        System.out.print(stack);
        System.out.println("]");
    }
}
