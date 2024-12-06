import java.util.*;

public class Day2{
    public static void main(String[] args) throws Exception{
        //part1();
        part2();
    }

    

    public static void part1() throws Exception{
        ArrayList<String> lines = ReadFile.getLines();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(String line : lines){
            ArrayList<Integer> row = new ArrayList<>();
            String[] strItems = line.split(" ");
            for(String strItem : strItems){
                row.add(Integer.parseInt(strItem));
            }
            list.add(row);
        }

        int safe = 0;
        int direction;
        for(ArrayList<Integer> row : list){
            int test = row.get(1)-row.get(0);
            if(test == 0){continue;}
            direction = test>0 ? 1 : -1;
            
            for(int i=0; i<row.size()-1; i++){
                int diff = (row.get(i+1)-row.get(i)) * direction;
               
                if(diff>3 || diff < 1){
                    safe--;
                    break;
                }
            }
            safe++;
        }

        System.out.println(safe);
    }

    public static void part2() throws Exception{
        ArrayList<String> lines = ReadFile.getLines();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(String line : lines){
            ArrayList<Integer> row = new ArrayList<>();
            String[] strItems = line.split(" ");
            for(String strItem : strItems){
                row.add(Integer.parseInt(strItem));
            }
            list.add(row);
        }
        boolean thisOneSafe = true;
        int safe = 0;
        int direction;
        for(ArrayList<Integer> row : list){
            for(int removal = 0; removal<row.size(); removal++){
                thisOneSafe = true;
                ArrayList<Integer> rowCopy = new ArrayList<>(row);
                rowCopy.remove(removal);
                int test = rowCopy.get(1)-rowCopy.get(0);
                if(test == 0){continue;}
                direction = test>0 ? 1 : -1;

                for(int i=0; i<rowCopy.size()-1; i++){
                    int diff = (rowCopy.get(i+1)-rowCopy.get(i)) * direction;
                
                    if(diff>3 || diff < 1){
                        thisOneSafe = false;
                        break;
                    }
                }
                if(thisOneSafe){
                    safe++;
                    break;
                }
            }
        }

        System.out.println(safe);

    }
}