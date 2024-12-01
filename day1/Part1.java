import java.util.*;
import java.io.*;
public class Part1{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner myReader = new Scanner(new File("input.txt"));
        //
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>();
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>();
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            System.out.println(nextLine);
            String[] numStrings = nextLine.split("   ");
            
            leftQueue.add(Integer.parseInt(numStrings[0]));
            rightQueue.add(Integer.parseInt(numStrings[1]));
        }

        long sum = 0;
        while(leftQueue.size()>0){
            sum += Math.abs(leftQueue.remove()-rightQueue.remove());
        }

        System.out.println(sum);
    }
}