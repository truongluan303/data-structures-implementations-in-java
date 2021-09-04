import java.util.*;
import java.util.function.Function;

/**
 * A class for generating statistical information hash table insertion and finds.
 *
 * @author Charles Hoot
 * @version 4.0
 */

public class HashPerformance
{



    public static void main(String[] args)
    {
        int insertCount;
        int insertionLinearProbes=0, insertionDoubleProbes=0, insertionPerfectProbes=0;
        int successLinearProbes=0, successDoubleProbes=0, successPerfectProbes=0;
        int failureLinearProbes=0, failureDoubleProbes=0, failurePerfectProbes=0;
        int trials;
        double load;
        int initialSize;
        String[] data;

        HashedDictionaryOpenAddressingLinearInstrumented<String,String> linearTable;
        HashedDictionaryOpenAddressingDoubleInstrumented<String,String> doubleTable;
        HashedDictionaryOpenAddressingPerfectInstrumented<String,String> perfectTable;

        System.out.println("How many items should be inserted into the hash tables?");
        insertCount = getInt();

        System.out.println("How many trials would you like?");
        trials = getInt();

        System.out.println("What should the maximum load factor be? ");
        load = getDoublePercent();

        System.out.println("What should the initial table size be? ");
        initialSize = getInt();

        for(int i = 0; i<trials; i++)
        {
            data = generateRandomData(insertCount * 2);

            // Set the Starting Capacity to 101 so that we don't rehash too soon
            linearTable = new HashedDictionaryOpenAddressingLinearInstrumented<>(initialSize);
            doubleTable = new HashedDictionaryOpenAddressingDoubleInstrumented<>(initialSize);
            perfectTable = new HashedDictionaryOpenAddressingPerfectInstrumented<>(initialSize);


            linearTable.setMaxLoadFactor(load);
            doubleTable.setMaxLoadFactor(load);
            perfectTable.setMaxLoadFactor(load);

            HashedDictionaryOpenAddressingLinearInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingDoubleInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingPerfectInstrumented.resetTotalProbes();

            System.out.println("The data is: " + getString(data));
            insertHalfData(linearTable, data);
            insertHalfData(doubleTable, data);
            insertHalfData(perfectTable, data);

            insertionLinearProbes += HashedDictionaryOpenAddressingLinearInstrumented.getTotalProbes();
            insertionDoubleProbes += HashedDictionaryOpenAddressingDoubleInstrumented.getTotalProbes();
            insertionPerfectProbes += HashedDictionaryOpenAddressingPerfectInstrumented.getTotalProbes();


            HashedDictionaryOpenAddressingLinearInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingDoubleInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingPerfectInstrumented.resetTotalProbes();

            searchFirstHalf(linearTable, data);
            successLinearProbes += HashedDictionaryOpenAddressingLinearInstrumented.getTotalProbes();

            searchFirstHalf(doubleTable, data);
            successDoubleProbes += HashedDictionaryOpenAddressingDoubleInstrumented.getTotalProbes();

            searchFirstHalf(perfectTable, data);
            successPerfectProbes += HashedDictionaryOpenAddressingPerfectInstrumented.getTotalProbes();


            HashedDictionaryOpenAddressingLinearInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingDoubleInstrumented.resetTotalProbes();
            HashedDictionaryOpenAddressingPerfectInstrumented.resetTotalProbes();

            searchSecondHalf(linearTable, data);
            failureLinearProbes += HashedDictionaryOpenAddressingLinearInstrumented.getTotalProbes();

            searchSecondHalf(doubleTable, data);
            failureDoubleProbes += HashedDictionaryOpenAddressingDoubleInstrumented.getTotalProbes();

            searchSecondHalf(perfectTable, data);
            failurePerfectProbes += HashedDictionaryOpenAddressingPerfectInstrumented.getTotalProbes();
        }


        System.out.println("Linear hashing");
        System.out.println("    Total probes for inserting data values: " + insertionLinearProbes);
        System.out.println("       Average probes made: " + insertionLinearProbes/(float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Successful searches: " + successLinearProbes / (float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Failed searches: " + failureLinearProbes / (float)(trials*insertCount));


        System.out.println("Double hashing");
        System.out.println("    Total probes for inserting data values: " + insertionDoubleProbes);
        System.out.println("       Average probes made: " + insertionDoubleProbes/(float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Successful searches: " + successDoubleProbes / (float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Failed searches: " + failureDoubleProbes / (float)(trials*insertCount));


        System.out.println("Perfect hashing");
        System.out.println("    Total probes for inserting data values: " + insertionPerfectProbes);
        System.out.println("       Average probes made: " + insertionPerfectProbes/(float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Successful searches: " + successPerfectProbes / (float)(trials*insertCount));
        System.out.println("            Average Number of Probes for Failed searches: " + failurePerfectProbes / (float)(trials*insertCount));

    }



    /**
     * Generate an array of random words.  Each would will be composed of three randomly
     * chosen syllables.
     *
     * @param   size    The number of strings to generate.
     * @return  The array of strings.
     */
    private static String[] generateRandomData(int size)
    {
        String[] result = new String[size];
        HashedDictionaryOpenAddressingLinear<String,String> checkTable = new HashedDictionaryOpenAddressingLinear<>();


        String[] firstSyl = {"ther", "fal","sol","cal","com","don", "gan", "tel", "fren", "ras", "tar", "men", "tri", "cap", "har"};
        String[] secondSyl = {"mo", "ta","ra","te","bo","bi","du","ca", "dan", "sen", "di", "no", "fe", "mi", "so"};
        String[] thirdSyl = {"tion", "ral", "tal","ly","nance","tor", "ing", "ger", "ten", "ful", "son", "dar", "der", "den", "ton"};

        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            String randomFirst = firstSyl[rand.nextInt(firstSyl.length)];
            String randomSecond = secondSyl[rand.nextInt(secondSyl.length)];
            String randomThird = thirdSyl[rand.nextInt(thirdSyl.length)];

            result[i] = randomFirst + randomSecond + randomThird;
        }

        return result;
    }





    /**
     * Insert all of the values in the array into the hash table.
     *
     * @param   dict The dictionary to insert all the words into.
     */
    private static void insertAllData(DictionaryInterface<String,String> dict, String[] data)
    {
        for (String myData : data) {
            dict.add(myData, myData);
        }
    }




    private static void insertHalfData(DictionaryInterface<String,String> dict, String[] data) {
        for (int i = 0; i < (data.length / 2); i++) {
            dict.add(data[i], data[i]);
        }
    }




    private static void searchFirstHalf(DictionaryInterface<String,String> dict, String[] data) {
        for (int i = 0; i < (data.length / 2); i++) {
            dict.contains(data[i]);
        }
    }




    private static void searchSecondHalf(DictionaryInterface<String,String> dict, String[] data) {
        for (int i = data.length / 2; i < data.length; i++) {
            dict.contains(data[i]);
        }
    }



    /**
     *  A displayable representation of an array of Objects where toString is
     *  applied on each object in the array.
     *
     * @param   data    The array to display.
     * @return  A printable string.
     */
    private static String getString(Object [] data)
    {
        StringBuilder result = new StringBuilder("[ ");

        for (Object datum : data) {
            result.append(datum.toString()).append(" ");
        }

        result.append("]");

        return result.toString();
    }





    /**
     * Get an integer value.
     *
     * @return     An integer.
     */
    private static int getInt()
    {
        Scanner input;
        int result = 10;        //Default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println("   It should be an integer value greater than or equal to 1.");
            result = input.nextInt();

        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;

    }




    /**
     * Get a floating point value greater than 0 and less than 1.
     *
     * @return     A double.
     */
    private static Double getDoublePercent()
    {
        Scanner input;
        double result = .5;        //Default value is .5
        try
        {
            input = new Scanner(System.in);
            System.out.println("   It should be a floating point value greater than 0 and less than 1.");

            result = input.nextDouble();

        }
        catch(NumberFormatException e)
        {
            System.out.println("Could not convert input to a double value");
            System.out.println(e.getMessage());
            System.out.println("Will use 0.5 as the default value");
        }
        catch(Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 0.5 as the default value");
        }

        return result;

    }
}
