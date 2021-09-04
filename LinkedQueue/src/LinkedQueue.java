public final class LinkedQueue<T> implements QueueInterface<T>
{
    private Node firstNode;
    private Node lastNode;

    /** Adds a new entry to the back of this queue.
     @param newEntry  An object to be added. */
    public void enqueue(T newEntry) {
        Node newNode = new Node(newEntry, null);
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
    }

    /** Removes and returns the entry at the front of this queue.
     @return  The object at the front of the queue.
     @throws  EmptyQueueException if the queue is empty before the operation. */
    public T dequeue() {
        T front = getFront();

        assert firstNode != null;
        firstNode.setData();
        firstNode = firstNode.getNextNode();

        if (firstNode == null)
            lastNode = null;
        return front;
    }

    /**  Retrieves the entry at the front of this queue.
     @return  The object at the front of the queue.
     @throws  EmptyQueueException if the queue is empty. */
    public T getFront() {
        if (isEmpty())
            throw new EmptyQueueException();
        else {
            assert firstNode != null;
            return  firstNode.getData();
        }
    }

    /** Detects whether this queue is empty.
     @return  True if the queue is empty, or false otherwise. */
    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    /** Removes all entries from this queue. */
    public void clear() {
        firstNode = null;
        lastNode = null;
    }

    class Node{
        private T data;
        private Node next;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        // set the next node for the current node
        private void setNextNode(Node nodeToSet) {
            this.next = nodeToSet;
        }

        // return the next node of the current node
        private Node getNextNode() {
            return this.next;
        }

        // set the data of the current node
        private void setData() {
            this.data = null;
        }

        // return the data of the current node
        private T getData() {
            return this.data;
        }
    } // end Node
} // end Linkedqueue

/**********************************************************
 * TEST RUN:
 * =========
 *
 * Create a queue:
 *
 *
 * isEmpty() returns true
 *
 * Add to queue to get
 * Joe Jess Jim Jill Jane Jerry
 *
 *
 * isEmpty() returns false
 *
 *
 *
 * Testing getFront and dequeue:
 *
 * Joe is at the front of the queue.
 * Joe is removed from the front of the queue.
 *
 * Jess is at the front of the queue.
 * Jess is removed from the front of the queue.
 *
 * Jim is at the front of the queue.
 * Jim is removed from the front of the queue.
 *
 * Jill is at the front of the queue.
 * Jill is removed from the front of the queue.
 *
 * Jane is at the front of the queue.
 * Jane is removed from the front of the queue.
 *
 * Jerry is at the front of the queue.
 * Jerry is removed from the front of the queue.
 *
 *
 * The queue should be empty: isEmpty() returns true
 *
 *
 * Add to queue to get
 * Joe Jess Jim
 *
 *
 * Testing clear:
 *
 *
 * isEmpty() returns true
 *
 *
 * Add to queue to get
 * Joe Jess Jim
 *
 * Joe is at the front of the queue.
 * Joe is removed from the front of the queue.
 *
 * Jess is at the front of the queue.
 * Jess is removed from the front of the queue.
 *
 * Jim is at the front of the queue.
 * Jim is removed from the front of the queue.
 *
 *
 *
 * The queue should be empty: isEmpty() returns true
 *
 * The next calls will throw an exception.
 *
 * Exception in thread "main" EmptyQueueException
 * 	at LinkedQueue.getFront(LinkedQueue.java:37)
 * 	at Driver.testQueueOperations(Driver.java:76)
 * 	at Driver.main(Driver.java:12)
 *
 * Process finished with exit code 1
 */