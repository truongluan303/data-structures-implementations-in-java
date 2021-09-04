public class LinkedBag <T> implements BagInterface<T>
{

    private Node firstNode; // Reference to first node of chain
    private int numberOfEntries;


    //default constructor
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end default constructor



    // IMPLEMENTED METHODS:
    /** Gets the current number of entries in this bag.
     @return  The integer number of entries currently in the bag. */
    public int getCurrentSize() {
        return numberOfEntries;
    }



    /** Sees whether this bag is empty.
     @return  True if the bag is empty, or false if not. */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }



    /** Adds a new entry to this bag.
     @param newEntry  The object to be added as a new entry.
     @return  True if the addition is successful, or false if not. */
    public boolean add(T newEntry) {
        // adding to the beginning
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;

        firstNode = newNode;
        numberOfEntries++;
        return true;
    } // end add



    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal.
     was successful, or null. */
    public T remove() {
        if (isEmpty())
            return null;
        else {
            T temp = firstNode.data;
            // I decide to remove the first Node
            remove(firstNode.data);
            return temp;
        }
    }


    /** Removes one occurrence of a given entry from this bag.
     @param anEntry  The entry to be removed.
     @return  True if the removal was successful, or false if not. */
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if (nodeN != null) {
            nodeN.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove



    /** Removes all entries from this bag. */
    public void clear() {
        while (!isEmpty())
            remove();
    }


    /** Counts the number of times a given entry appears in this bag.
     @param anEntry  The entry to be counted.
     @return  The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int loopCounter = 0;
        Node current = firstNode;

        while (loopCounter < numberOfEntries && current != null) {
            if (anEntry.equals(current.data))
                frequency++;
            loopCounter++;
            current = current.next;
        } // end while
        return frequency;
    } // end getFrequencyOf



    /** Tests whether this bag contains a given entry.
     @param anEntry  The entry to locate.
     @return  True if the bag contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && currentNode != null) {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        } // end while
        return found;
    } // end contains



    /** Retrieves all entries that are in this bag.
     @return  A newly allocated array of all the entries in the bag.
     Note: If the bag is empty, the returned array is empty. */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
                T[] result = (T[])new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while (index < numberOfEntries && currentNode != null) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        } // end while
        return result;
    } // end toArray



//	public <T> T[] toArray();  // Alternate
//	public Object[] toArray(); // Alternate

    /** Creates a new bag that combines the contents of this bag
     and anotherBag.
     @param anotherBag  The bag that is to be added.
     @return  A combined bag. */
    //public BagInterface<T> union(BagInterface<T> anotherBag);

    /** Creates a new bag that contains those objects that occur
     in both this bag and anotherBag.
     @param anotherBag  The bag that is to be compared.
     @return  A combined bag. */
    //public BagInterface<T> intersection(BagInterface<T> anotherBag);

    /** Creates a new bag of objects that would be left in this bag
     after removing those that also occur in anotherBag.
    // @param anotherBag  The bag that is to be removed.
     @return  A combined bag. */


    // Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        } // end while

        return currentNode;
    } // end getReferenceTo




    private class Node
    {
        T data;
        Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end default constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end overloaded constructor
    }// end Node
}// end LinkedBag



/* TEST RUN:


        Creating an empty bag.
        The bag contains 0 string(s), as follows:

        Testing isEmpty with an empty bag:
        isEmpty finds the bag empty: OK.


        Testing the method getFrequencyOf:
        In this bag, the count of  is 0
        In this bag, the count of B is 0

        Testing the method contains:
        Does this bag contain ? false
        Does this bag contain B? false

        Removing a string from the bag:
        remove() returns null
        The bag contains 0 string(s), as follows:


        Removing "B" from the bag:
        remove("B") returns false
        The bag contains 0 string(s), as follows:

        Adding to the bag: A D B A C A D
        The bag contains 7 string(s), as follows:
        D A C A B D A
        Testing isEmpty with a bag that is not empty:
        isEmpty finds the bag not empty: OK.


        Testing the method getFrequencyOf:
        In this bag, the count of A is 3
        In this bag, the count of B is 1
        In this bag, the count of C is 1
        In this bag, the count of D is 2
        In this bag, the count of Z is 0

        Testing the method contains:
        Does this bag contain A? true
        Does this bag contain B? true
        Does this bag contain C? true
        Does this bag contain D? true
        Does this bag contain Z? false

        Removing a string from the bag:
        remove() returns D
        The bag contains 6 string(s), as follows:
        A C A B D A

        Removing "B" from the bag:
        remove("B") returns true
        The bag contains 5 string(s), as follows:
        C A A D A

        Removing "A" from the bag:
        remove("A") returns true
        The bag contains 4 string(s), as follows:
        C A D A

        Removing "C" from the bag:
        remove("C") returns true
        The bag contains 3 string(s), as follows:
        A D A

        Removing "Z" from the bag:
        remove("Z") returns false
        The bag contains 3 string(s), as follows:
        A D A

        Clearing the bag:
        Testing isEmpty with an empty bag:
        isEmpty finds the bag empty: OK.

        The bag contains 0 string(s), as follows:


        Process finished with exit code 0
*/