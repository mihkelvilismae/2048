package Mihkel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mihkel on 5.03.2015.
 */
public class Common {
    public static ArrayList<String> readLinesFromFile(String filename) {
        java.util.Scanner sc;
        java.io.File file = new java.io.File(filename);
        ArrayList<String> rowsInFile = new ArrayList<String>();
        try {
            sc = new java.util.Scanner(file, "UTF-8");
        } catch (FileNotFoundException unused) {
            System.out.println("Faili ei leitud");
            return new ArrayList<String>();
        }

        while (sc.hasNextLine()) {
            String row = sc.nextLine();
            rowsInFile.add(row);
        }
        return rowsInFile;
    }

    // Taken from here:
    // http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
