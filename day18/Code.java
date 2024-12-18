import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "test.txt";
    
    public static void main(String[] args) throws Exception{
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            System.out.println(nextLine);

            //Read Logic here

        }



        //System.out.println(result);
    }
}

