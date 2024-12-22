import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    public static long PRUNE = 16777216L;
    //orig = prune(orig*64 mix orig)
    //secret/32
    //
    //
    static ArrayList<Long> secrets = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            secrets.add(Long.parseLong(nextLine));

            //Read Logic here

        }
        long sum = 0;
        for(long l : secrets){
            long secret = l;

            for(int i=0; i<2000; i++){
                //Step 1
                long result = secret*64;
                secret ^= result;
                secret %= PRUNE;

                //Step 2
                result = secret/32;
                secret ^=result;
                secret %= PRUNE;

                //Step 3
                result = secret*2048;
                secret ^= result;
                secret %= PRUNE;


            }
            sum += secret;
            //System.out.println(secret);
        }



        System.out.println(sum);
    }
}

