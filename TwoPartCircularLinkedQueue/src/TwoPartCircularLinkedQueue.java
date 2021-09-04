public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>
{
    private Node queueNode; // References first node in queue
    private Node freeNode; // References node after back of queue

    public TwoPartCircularLinkedQueue()
    {
        freeNode = new Node(null, null);
        freeNode.setNextNode(freeNode);
        queueNode = freeNode;
    } // end default constructor


    @Override
    public void enqueue(T newEntry) {
        if (isEmpty()) {
            queueNode = new Node (newEntry);
            queueNode.setNextNode(freeNode);
            freeNode.setNextNode(queueNode);
        }
        else {
            Node newNode = new Node (null, null);
            freeNode.setData(newEntry);
            freeNode.setNextNode(newNode);
            freeNode = newNode;
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            throw new EmptyQueueException();

        T result = queueNode.getData();
        queueNode = queueNode.getNext();
        freeNode.setNextNode(queueNode);

        return result;
    }

    @Override
    public T getFront() {
        if (isEmpty())
            throw new EmptyQueueException();
        return queueNode.getData();
    }

    @Override
    public boolean isEmpty() {
        return queueNode == freeNode;
    }

    public void clear()
    {
        while (!isEmpty())
            dequeue();
    } // end clear



    private class Node{
        private T data;
        private Node next;

        public Node(T newData) {
            this(newData, null);
        }

        public Node(T newData, Node link) {
            data = newData;
            next = link;
        }

        private void setNextNode(Node myNode) {
            next = myNode;
        }

        private void setData(T newData) {
            data = newData;
        }

        private T getData() {
            return data;
        }

        private Node getNext() {
            return next;
        }
    } // end Node
} // end TwoPartCircularLinkedQueue


/*
TEST RUN:

C:\Users\truon\.jdks\openjdk-15.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\lib\idea_rt.jar=53266:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\truon\Desktop\Java Project\TwoPartCircularLinkedQueue\out\production\TwoPartCircularLinkedQueue" Driver
Create a queue:


isEmpty() returns true

Add to queue to get
Joe Jess Jim Jill Jane Jerry


isEmpty() returns false



Testing getFront and dequeue:

Joe is at the front of the queue.
Joe is removed from the front of the queue.

Jess is at the front of the queue.
Jess is removed from the front of the queue.

Jim is at the front of the queue.
Jim is removed from the front of the queue.

Jill is at the front of the queue.
Jill is removed from the front of the queue.

Jane is at the front of the queue.
Jane is removed from the front of the queue.

Jerry is at the front of the queue.
Jerry is removed from the front of the queue.


The queue should be empty: isEmpty() returns true


Add to queue to get
Joe Jess Jim


Testing clear:


isEmpty() returns true


Add to queue to get
Joe Jess Jim

Joe is at the front of the queue.
Joe is removed from the front of the queue.

Jess is at the front of the queue.
Jess is removed from the front of the queue.

Jim is at the front of the queue.
Jim is removed from the front of the queue.



The queue should be empty: isEmpty() returns true

The next calls will throw an exception.

Exception in thread "main" EmptyQueueException
	at TwoPartCircularLinkedQueue.getFront(TwoPartCircularLinkedQueue.java:45)
	at Driver.testQueueOperations(Driver.java:76)
	at Driver.main(Driver.java:12)

Process finished with exit code 1

 */