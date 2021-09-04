// Hoang Phuc Luan Truong (Luan)
// LList.java

public class LList<T> implements ListInterface<T>

{
    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;


    // add a new entry to the end of the list
    // (this method is implemented, view ListInterface.java for more information)
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty())
            firstNode = newNode;
        else {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }
        numberOfEntries++;
    }


    // add a new entry to the given position within the list
    // (this method is implemented, view ListInterface.java for more information)
    public void add(int newPosition, T newEntry) {
        if (newPosition >= 1 && newPosition <= numberOfEntries + 1) {
            Node newNode = new Node(newEntry);
            if (newPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }
            numberOfEntries++;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
    }



    // remove the entry at the given position within the list
    // (this method is implemented, view ListInterface.java for more information)
    public T remove(int givenPosition) {
        T result = null;
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            assert !isEmpty();
            if (givenPosition == 1) {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation");
    }


    // Removes all entries from the list
    public void clear() {
        initializeDataFields();
    }



    // replace the entry at the given position within the list
    // (this method is implemented, view ListInterface.java for more information)
    public T replace(int givenPosition, T newEntry) {
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            assert !isEmpty();
            Node desireNode = getNodeAt(givenPosition);
            T originalEntry = desireNode.getData();
            desireNode.setData(newEntry);
            return originalEntry;
        } else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }



    // retrieve the entry at the given position within the list
    // (this method is implemented, view ListInterface.java for more information)
    public T getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            assert !isEmpty();
            return getNodeAt(givenPosition).getData();
        } else
            throw new IndexOutOfBoundsException("Illegal position to getEntry operation.");
    }



    // check if the list contains the given entry
    // (this method is implemented, view ListInterface.java for more information)
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && currentNode != null) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return found;
    }



    // get the length of the list
    // (this method is implemented, view ListInterface.java for more information)
    public int getLength() {
        return numberOfEntries;
    }



    // check if the list is empty
    // (this method is implemented, view ListInterface.java for more information)
    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0){
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    }



    // Retrieves all entries that are in this list in the order in which they occur in the list.
    // (this method is implemented, view ListInterface.java for more information)
    public T[] toArray() {
        @SuppressWarnings("unchecked")
                T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }



    // Initializes the class's data fields to indicate an empty list.
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end initializeDataFields



    // Returns a reference to the node at a given position.
    // Precondition: The chain is not empty;
    // 1 <= givenPosition <= numberOfEntries.
    private Node getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;

        // Traverse the chain to locate the desired node
        // (skipped if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();

        assert currentNode != null;

        return currentNode;
    } // end getNodeAt



    // Node class
    private class Node
    {
        T data; // the data of the node
        Node link; // the link to the next node in the list

        // Constructor
        private Node(T newEntry) {
            data = newEntry;
            link = null;
        }

        // set the next node for the current node
        private void setNextNode(Node nodeToSet) {
            this.link = nodeToSet;
        }

        // return the next node of the current node
        private Node getNextNode() {
            return this.link;
        }

        // set the data of the current node
        private void setData(T dataToSet) {
            this.data = dataToSet;
        }

        // return the data of the current node
        private T getData() {
            return this.data;
        }
    }
}

/* TEST RUN:

//////////////////////////////////////////////////////////////
Testing add to end: Add 15, 25, 35, 45


List should contain
15 25 35 45
The list contains 4 string(s), as follows:
15 25 35 45

Is List empty? false
Add more entries to end: Add 55, 65, 75, 85, 95


Is List empty? false
-------------------------



List should contain 15 25 35 45 55 65 75 85 95
The list contains 9 string(s), as follows:
15 25 35 45 55 65 75 85 95

------------------------

Testing clear()
List should be empty:
Is list empty? true
-------------------------

Create a new list.

Add 15 at position 1:
Add 25 at position 2:
Add 35 at position 3:


List should contain
15 25 35
The list contains 3 string(s), as follows:
15 25 35
Is List empty? false
Add 19 at position 1:
Add 39 at position 3:
Add 29 at position 2:
Add 55 at position 7:
Add 65 at position 8:


List should contain
19 29 15 39 25 35 55 65
The list contains 8 string(s), as follows:
19 29 15 39 25 35 55 65
Is List empty? false

-------------------------

Testing remove()
Removing 15 at position 3: returns 15
Removing 19 at position 1: returns 19
Removing 65 at position 6: returns 65


List should contain
29 39 25 35 55
The list contains 5 string(s), as follows:
29 39 25 35 55

-------------------------

Testing replace()
Replace 29 at position 1 with 92 : returns 29
Replace 39 at position 2 with 93 : returns 39
Replace 25 at position 3 with 52 : returns 25
Replace 35 at position 4 with 53 : returns 35
Replace 55 at position 5 with 50 : returns 55


List should contain
92 93 52 53 50
The list contains 5 string(s), as follows:
92 93 52 53 50
Is List empty? false

-------------------------

Testing getEntry()

The list contains 5 entries, as follows:
92 is entry 1
93 is entry 2
52 is entry 3
53 is entry 4
50 is entry 5


-------------------------

Testing contains() [results should be TRUE]
List contains 92: true
List contains 52: true
List contains 53: true
List contains 50: true


Testing contains() [results should be FALSE]
List contains 91 returns : false
List contains 55 returns : false
List contains 4  returns : false
List contains 12 returns : false


Done.

Process finished with exit code 0
*/