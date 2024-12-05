import java.util.*;
import java.io.*;

public class Code{

    public static ArrayList<String> getLines() throws Exception{
        ArrayList<String> lines = new ArrayList<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){
        String nextLine = myReader.nextLine();
            //if(nextLine.trim().isEmpty()){
            //    continue;
            //}
            lines.add(nextLine);
        }

        return lines;
    }

    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        ArrayList<String> lines = getLines();
        

    }

    public static void part2() throws Exception{
        ArrayList<String> lines = getLines();

    }
}
