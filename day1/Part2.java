import java.util.*;
import java.io.*;
public class Part2{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner myReader = new Scanner(new File("input.txt"));
        //
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            System.out.println(nextLine);
            String[] numStrings = nextLine.split("   ");
            
            hashMap.put(Integer.parseInt(numStrings[0]),0);
        }

        myReader = new Scanner(new File("input.txt"));

        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            if(nextLine.trim().isEmpty()){
                
            }
        }

        long sum = 0;
        for(int num : hashMap.keySet()){
            sum += num * hashMap.get(num);
        }

        System.out.println(sum);
    }
}