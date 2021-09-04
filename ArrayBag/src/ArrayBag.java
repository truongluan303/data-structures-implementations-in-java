// Hoang Phuc Luan Truong (Luan)


import java.util.Random;
/**
A class of bags whose entries are stored in a fixed-size array.
@author Frank M. Carrano
 * This code is from Chapter 2 of
 * Data Structures and Abstractions with Java 4/e
 *      by Carrano 
 * 
 * The toString method is overwritten to give a nice display of the items in
 * the bag in this format Bag{Size:# [1] [2] [3] [4] }
 * //- * @version 4.0
 */

public final class ArrayBag<T> implements BagInterface<T> {

    private final T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    
    private boolean initialized;
    private static final int MAX_CAPACITY = 10000;

    /** Creates an empty bag whose initial capacity is 25. */
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /**
     * Creates an empty bag having a given initial capacity.
     *
     * @param desiredCapacity The integer capacity desired.
     */
    public ArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {

            // The cast is safe because the new array contains null entries.
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            initialized = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag " +
                                            "whose capacity exceeds " +
                                            "allowed maximum.");
    } // end constructor

    /** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry.
    @return True if the addition is successful, or false if not. */
    public boolean add(T newEntry) {
        checkInitialization();
        boolean result = true;
        if (isArrayFull()) {
            result = false;
        } else { // Assertion: result is true here
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        } // end if
        return result;
 
    } // end add

    /** Throws an exception if this object is not initialized.
     * 
     */
    private void checkInitialization()
    {
        if (!initialized)
             throw new SecurityException("ArrayBag object is not initialized " +
                                        "properly.");
   }
    
    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. */
    public T[] toArray() {
        
        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // unchecked cast
        // end for
        if (numberOfEntries >= 0) System.arraycopy(bag, 0, result, 0, numberOfEntries);
        return result;
    } // end toArray

    /** Sees whether this bag is full.
    @return True if the bag is full, or false if not. */
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    } // end isArrayFull

    /** Sees whether this bag is empty.
    @return True if the bag is empty, or false if not. */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    /** Gets the current number of entries in this bag.
    @return The integer number of entries currently in the bag. */
    public int getCurrentSize() {
        return numberOfEntries;
    } // end getCurrentSize

    /** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted.
    @return The number of times anEntry appears in the bag. */
    public int getFrequencyOf(T anEntry) {
        checkInitialization();
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            } // end if
        } // end for
        return counter;
    } // end getFrequencyOf


    /** Tests whether this bag contains a given entry.
    @param anEntry The entry to locate.
    @return True if the bag contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        checkInitialization();
        return getIndexOf(anEntry) > -1;
    } // end contains


    /** Removes all entries from this bag. */
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    } // end clear


    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful,
    or null if otherwise. */
    public T remove() {
        T result = null;
        checkInitialization();
        if (numberOfEntries > 0) {
            Random rand = new Random();
            int indexToRemove = rand.nextInt(numberOfEntries);
            result = removeEntry(indexToRemove);
        }
        return result;
    } // end remove


    /** Removes one occurrence of a given entry from this bag.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false if not. */
    public boolean remove(T anEntry) {
        checkInitialization();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    } // end remove


// Removes and returns the entry at a given array index within the array bag.
// If no such entry exists, returns null.
// Preconditions: 0 <= givenIndex < numberOfEntries;
//                  checkInitialization has been called.
    private T removeEntry(int givenIndex) {
        T result = null;
        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex];                   // entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null;            // remove last entry
           numberOfEntries--;
         } // end if
        return result;
    } // end removeEntry


// Locates a given entry within the array bag.
// Returns the index of the entry, if located, or -1 otherwise.
// Precondition: checkInitialization has been called.
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean stillLooking = true;
        int index = 0;
        while (stillLooking && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                stillLooking = false;
                where = index;
            } // end if
            index++;
        } // end for
// Assertion: If where > -1, anEntry is in the array bag, and it
// equals bag[where]; otherwise, anEntry is not in the array
        return where;
    } // end getIndexOf
    
    
    /** Override the equals method so that we can tell if two bags contain the same items
     * the contents in the bag.
     * @return a string representation of the contents of the bag */
    public String toString() {

        StringBuilder result = new StringBuilder("Bag{Size:" + numberOfEntries + " ");
        

        for (int index = 0; index < numberOfEntries; index++) {
            result.append("[").append(bag[index]).append("] ");
        } // end for

        result.append("}");
        return result.toString();
    } // end toArray

    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     * 
     ************************************************************************/



    /** Check to see if two bags are equals.  
     * @param aBag Another object to check this bag against.
     * @return True the two bags contain the same objects with the same frequencies.
     */
    public boolean equals(ArrayBag<T> aBag) {
        boolean result = true; // result of comparison of bags

        if (numberOfEntries == aBag.getCurrentSize()) {
            T[] otherBag = aBag.toArray();
            for (T t : otherBag)
                if (this.getFrequencyOf(t) != aBag.getFrequencyOf(t)) {
                    result = false;
                    break;
                }
        }

        else
            result = false;

        return result;
    }  // end equals



    /** Duplicate all the items in a bag.
     * @return True if the duplication is possible.
     */
    public boolean duplicateAll() {
        checkInitialization();
        boolean success = false;

        if (numberOfEntries == 0)
            success = true;

        else if (bag.length >= (numberOfEntries * 2)) {
            success = true;

            T[] array = this.toArray();
            for (T t : array) this.add(t);
        }

        return success;
    }  // end duplicateAll



    /** Remove all duplicate items from a bag*/
    public void removeDuplicates() {
        checkInitialization();

        T[] array = this.toArray();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < getFrequencyOf(array[i]) - 1; i++) {
               this.remove(array[i]);
            }
        }
    }  // end removeDuplicates

} // end ArrayBag

/*
TEST RUN:

C:\Users\truon\.jdks\openjdk-15.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\lib\idea_rt.jar=50510:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Users\truon\Desktop\Java Project\ArrayBag\out\production\ArrayBag" BagExtensionsTest

TESTING EQUALS
Checking to see if bag 1 is equal to itself
    Passed test

Checking to see if bags 1 and 2 are equal (different bags, same values
    Passed test

Checking to see if bags 1 and 3 are equal (same items different order)
    Passed test

Checking to see if bags 1 and 4 are not equal (bag 4 is missing an item)
    Passed test

Checking to see if bags 4 and 1 are not equal (bag 4 is missing an item)
    Passed test

Checking to see if bags 1 and 5 are not equal (bag 5 is missing a duplicate item)
    Passed test

Checking to see if bags 5 and 1 are not equal (bag 5 is missing a duplicate item)
    Passed test

Checking to see if bags 1 and 6 are not equal (bag 6 has an extra duplicate item)
    Passed test

Checking to see if bags 6 and 1 are not equal (bag 6 has an extra duplicate item)
    Passed test

Checking to see if bags 1 and 7 are not equal (bag 7 has same number of items, but frequencies are different)
    Passed test

Checking to see if bags 7 and 1 are not equal (bag 7 has same number of items, but frequencies are different)
    Passed test

Checking to see if zero size bags are equal
    Passed test


TESTING REMOVE
Try to remove from an empty bag)
    Passed test

Try to remove from a bag with one item
    Passed test

Removing items from a bag with no duplicated one at a time.
Verify that each item is removed and the size is correct after the remove
    Passed test

Removing items from a bag with 5 duplicates of U, V, W, X, Y, and Z.
Add the removed item to a second bag.
Verify that the frequencies across bags still adds to 5
Got item W
Got item V
Got item U
Got item X
Got item Z
Got item W
Got item X
Got item Y
Got item Y
Got item W
Got item U
Got item X
Got item U
Got item X
Got item W
Got item Y
Got item V
Got item V
Got item Z
Got item Z
Got item V
Got item Z
Got item Z
Got item U
Got item V
Got item W
Got item X
Got item Y
Got item Y
Got item U
    Passed test

Try a sequence of 27 remove/adds and see if the bag equals the original
Different strings should be removed each time.  Strings are Ax1, Bx1, Cx1, Xx2, Yx2, and Zx2
On average we expect to see approximately 3 each of A, B, and C and 6 each of X, Y and Z.
   Removed string B
   Removed string B
   Removed string Z
   Removed string Z
   Removed string C
   Removed string A
   Removed string X
   Removed string Y
   Removed string X
   Removed string Y
   Removed string A
   Removed string Y
   Removed string Z
   Removed string X
   Removed string X
   Removed string C
   Removed string X
   Removed string X
   Removed string X
   Removed string Z
   Removed string Y
   Removed string A
   Removed string X
   Removed string X
   Removed string Y
   Removed string Y
   Removed string Z
    Passed test

Removing values from two identically constructed bags
We expect that the order of removal will be different for the two bags
   Removed strings P and Q
   Removed strings T and T
   Removed strings O and O
   Removed strings Q and N
   Removed strings S and R
   Removed strings M and P
   Removed strings L and S
   Removed strings N and L
   Removed strings R and M

TESTING DUPLICATE ALL
Checking to see if the duplicate of an empty bag is still empty
    Passed test
    Passed test - return value correct

Checking to see if the duplicate of a bag with one item works
    Passed test
    Passed test - return value correct

Checking to see if the duplicate of a bag with two items works
    Passed test
    Passed test - return value correct

Checking to see if the duplicate of a general bag of items works
    Passed test
    Passed test - return value correct

Checking to see if we can duplicate a bag at just under half capacity
    Passed test
    Passed test - return value correct

Checking to see if we can duplicate a bag at exactly half capacity
    Passed test
    Passed test - return value correct

Checking to see if we do not duplicate a bag at just over half capacity
    Passed test
    Passed test - return value correct


TESTING REMOVE DUPLICATES
Checking to see that we can remove duplicates on an empty bag and still have an empty bag
    Passed test

Checking to see that we can remove duplicates on a singleton bag and still have the same singleton bag
    Passed test

Checking to see a bag of two duplicates successfully becomes the correct singleton bag
    Passed test

Checking to see a bag of three duplicates successfully becomes the correct singleton bag
    Passed test

Checking to see a bag with no duplicates remains the same
    Passed test

Checking a bag with some items duplicated to see that duplicates are removed
    Passed test

Checking a bag with all items duplicated to see that duplicates are removed
    Passed test


Process finished with exit code 0

 */