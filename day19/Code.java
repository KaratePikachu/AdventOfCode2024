import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "test.txt";
    public static ArrayList<String> availableTowels = new ArrayList<>();
    public static ArrayList<String> patterns = new ArrayList<>();
    public static int numPossible = 0;
    public static HashMap<Integer,ArrayList<>>

    public static void main(String[] args) throws Exception{
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        availableTowels.addAll(List.of(myReader.nextLine().split(", ")));
        myReader.nextLine();
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            patterns.add(nextLine);

            //Read Logic here

        }

        int numPossible = 0;
        for(String pattern : patterns){
            //Get the indexOf for each availtowel in the pattern
            //feed into LinkedList of sizes
        }


        System.out.println(numPossible);
    }

    public static void isPossible(String pattern){
        boolean foundPossible = false;
        for(String available : availableTowels){
            if(pattern.indexOf(available) == 0){
                if(pattern.length() == available.length()){
                    Code.numPossible++;
                    return;
                }
                isPossible(pattern.substring(available.length()));
            }
        }
    }
}

