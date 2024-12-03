import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
public class FileInput{
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
}
