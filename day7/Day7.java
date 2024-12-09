import java.util.*;
import java.io.*;

public class Day7 {
    static String fileName = "input.txt";
    public static boolean foundPossibility = false;

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        long numTrials = 10;
        for(int i=0; i<numTrials; i++) {
            part1();
        }
        System.out.println((System.currentTimeMillis()-start)/(numTrials*1000.0));
        

        //part2();
    }

    public static void part1() throws Exception{
        ArrayList<ArrayList<Long>> eq = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            ArrayList<Long> row = new ArrayList<>();
            eq.add(row);
            String[] nextLine = myReader.nextLine().split(" ");
            //System.out.println(nextLine);
            row.add(Long.parseLong(nextLine[0].substring(0,nextLine[0].length()-1)));

            for(int i=1; i<nextLine.length; i++){
                row.add(Long.parseLong(nextLine[i]));
            }
            //Read Logic here
            
        }

        long sum = 0;
        for(ArrayList<Long> row : eq){
            validateEquation(row, row.get(1), 2);
            if(Day7.foundPossibility){
                sum+=row.getFirst();
                Day7.foundPossibility = false;
            }
        }

        System.out.println(sum);

        

        //System.out.println(result);

        
    }

    public static void validateEquation(ArrayList<Long> row, long total, int index){
        if(total > row.getFirst()){
            return;
        }
        if(foundPossibility){
            return;
        }

        if(index == row.size()){
            if(row.getFirst()==total){
                Day7.foundPossibility = true;
            }
            return;
        }

        validateEquation(row, total+row.get(index),index+1);
        validateEquation(row, total*row.get(index),index+1);
        validateEquation(row, Long.parseLong(""+total+row.get(index)),index+1);
    }
}
