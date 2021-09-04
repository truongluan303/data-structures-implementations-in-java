// Hoang Phuc Luan Truong (Luan)

public class LinkedDeque<T> implements DequeInterface<T>
{
    private DLNode firstNode; // References node at front of deque
    private DLNode lastNode; // References node at back of deque

    public LinkedDeque()
    {
        firstNode = null;
        lastNode = null;
    } // end default constructor



    public T getBack()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return lastNode.getData();
    } // end getBack



    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    } // end getFront



    public void clear()
    {
        firstNode = null;
        lastNode = null;
    } // end clear



    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    } // end isEmpty




    @Override
    public void addToFront(T newEntry) {
        if (isEmpty()) {
            DLNode newNode = new DLNode(newEntry);
            firstNode = lastNode = newNode;
        }
        else {
            DLNode newNode = new DLNode(null, newEntry, firstNode);
            firstNode.setPreviousNode(newNode);
            firstNode = newNode;
        }
    }



    @Override
    public void addToBack(T newEntry) {
        if (isEmpty()) {
            DLNode newNode = new DLNode(newEntry);
            firstNode = lastNode = newNode;
        }
        else {
            DLNode newNode = new DLNode(lastNode, newEntry, null);
            lastNode.setNextNode(newNode);
            lastNode = newNode;
        }
    }



    @Override
    public T removeFront() {
        T result;
        if (firstNode != null) {
            DLNode temp = firstNode;
            result = temp.getData();
            firstNode = firstNode.next;
            if (firstNode == null)
                lastNode = null;
            else
                firstNode.setPreviousNode(null);
        }
        else
            throw new EmptyQueueException();

        return result;
    }



    @Override
    public T removeBack() {
        T result;
        if (lastNode != null) {
            DLNode temp = lastNode;
            result = temp.getData();
            lastNode = lastNode.previous;
            if (lastNode == null)
                firstNode = null;
            else
                lastNode.setNextNode(null);
        }
        else
            throw new EmptyQueueException();
        return result;
    }





    private class DLNode
    {
        private T data; // Deque entry
        private DLNode next; // Link to next node
        private DLNode previous; // Link to previous node

        private DLNode(T dataPortion)
        {
            data = dataPortion;
            next = null;
            previous = null;
        } // end constructor

        private DLNode(DLNode previousNode, T dataPortion, DLNode nextNode)
        {
            data = dataPortion;
            next = nextNode;
            previous = previousNode;
        } // end constructor

        private T getData()
        {
            return data;
        } // end getData

        private void setData(T newData)
        {
            data = newData;
        } // end setData

        private DLNode getNextNode()
        {
            return next;
        } // end getNextNode

        private void setNextNode(DLNode nextNode)
        {
            next = nextNode;
        } // end setNextNode

        private DLNode getPreviousNode()
        {
            return previous;
        } // end getPreviousNode

        private void setPreviousNode(DLNode previousNode)
        {
            previous = previousNode;
        } // end setPreviousNode
    } // end DLNode

} // end LinkedDeque

/*
TEST RUN:

C:\Users\truon\.jdks\openjdk-15.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\lib\idea_rt.jar=51854:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\truon\Desktop\Java Project\LinkedDeque\out\production\LinkedDeque" Driver
Create a deque:


isEmpty() returns true

Add to front and back of deque to get
Joe Jess Jim Jill Jane Jerry


isEmpty() returns false



Testing getFront, getBack, removeFront, removeBack:

Joe is at the front of the deque.
Jerry is at the back of the deque.
Joe is removed from the front of the deque.
Jerry is removed from the back of the deque.
Jess is at the front of the deque.
Jane is at the back of the deque.


Testing clear:


isEmpty() returns true


The next calls will throw an exception.

Exception in thread "main" EmptyQueueException
	at LinkedDeque.removeFront(LinkedDeque.java:95)
	at Driver.testDequeOperations(Driver.java:60)
	at Driver.main(Driver.java:12)

Process finished with exit code 1

 */