import java.util.*;
import java.io.*;

public class Day7 {
    static String fileName = "input.txt";

    public static void main(String[] args) throws Exception{
        part1();
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
            if(validEquation(row, row.get(1), 2)){
                sum+=row.getFirst();
            }
        }

        System.out.println(sum);

        

        //System.out.println(result);

        
    }

    public static boolean validEquation(ArrayList<Long> row, long total, int index){

        if(index == row.size()){
            return row.getFirst()==total;
        }

        return validEquation(row, total*row.get(index),index+1) || validEquation(row, total+row.get(index),index+1) || validEquation(row, Long.parseLong(""+total+""+row.get(index)),index+1);
    }
}
