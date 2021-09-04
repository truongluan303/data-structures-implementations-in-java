// Hoang Phuc Luan Truong (Luan)

import LinkedListSolution.EmptyStackException;

public final class LinkedStack<T> implements StackInterface<T>
{

    private Node topNode; // References the first node in the chain

    //default constructor
    public LinkedStack() {
        topNode = null;
    }


    ///////////////////// Implement the unimplemented methods ////////////////////////

    /** Adds a new entry to the top of this stack.
     @param newEntry  An object to be added to the stack. */
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    }


    /** Removes and returns this stack's top entry.
     @return  The object at the top of the stack.
     // @throws  EmptyStackException if the stack is empty before the operation. */
    public T pop() {
        T top = peek();
        assert topNode != null;
        topNode = topNode.getNextNode();
        return top;
    }


    /** Retrieves this stack's top entry.
     @return  The object at the top of the stack.
    // @throws  EmptyStackException if the stack is empty. */
    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    }

    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    public boolean isEmpty() {
        return topNode == null;
    }


    /** Removes all entries from this stack. */
    public void clear() {
        topNode = null;
    }


    //////// Node class ////////
    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node link) {
            data = dataPortion;
            next = link;
        }

        private void setData(T newData) {
            data = newData;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }

        private T getData() {
            return data;
        }

        private Node getNextNode() {
            return next;
        }
    }// end Node
}// end LinkedStack

/**************************************************************
 TEST RUN:
 =========

 Create a stack:
 isEmpty() returns true

 Add to stack to get
 Joe Jane Jill Jess Jim

 isEmpty() returns false

 Testing peek and pop:

 Joe is at the top of the stack.
 Joe is removed from the stack.

 Jane is at the top of the stack.
 Jane is removed from the stack.

 Jill is at the top of the stack.
 Jill is removed from the stack.

 Jess is at the top of the stack.
 Jess is removed from the stack.

 Jim is at the top of the stack.
 Jim is removed from the stack.

 The stack should be empty: isEmpty() returns true

 Add to stack to get
 Jim Jess Joe


 Testing clear:
 The stack should be empty:

 isEmpty() returns true

 myStack.peek() returns
 Exception in thread "main" LinkedListSolution.EmptyStackException
 at LinkedStack.peek(LinkedStack.java:41)
 at Driver.testStackOperations(Driver.java:58)
 at Driver.main(Driver.java:12)

 Process finished with exit code 1
 */