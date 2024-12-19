import java.util.*;
import java.io.*;



public class Code{
    public static String fileName = "input.txt";
    public static ArrayList<char[]> availableTowels = new ArrayList<>();
    public static ArrayList<char[]> patterns = new ArrayList<>();
    public static TreeMap<Integer,Long> map = new TreeMap<>();

    public static void main(String[] args) throws Exception{
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        for(String str : myReader.nextLine().split(", ")){
            availableTowels.add(str.toCharArray());
        }

        myReader.nextLine();
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            patterns.add(nextLine.toCharArray());
        }

        long numPossible = 0;
        for(char[] pattern : patterns){
            map.clear();
            //Starting out
            for(char[] available : availableTowels){
                if(atIndex(pattern,available,0)){
                    int nextIndex = available.length;
                    if(!map.containsKey(nextIndex)){
                        map.put(nextIndex, 0L);
                    }
                    map.put(nextIndex,map.get(nextIndex)+1);
                }
            }

            //Push Forward
            for(int cIndex = 1; cIndex<pattern.length; cIndex++){
                if(!map.containsKey(cIndex)){
                    continue;
                }

                long quant = map.get(cIndex);
                map.remove(cIndex);

                for(char[] available : availableTowels){
                    //Starting out
                    if(atIndex(pattern,available,cIndex)){
                        int nextIndex = cIndex+available.length;
                        if(!map.containsKey(nextIndex)){
                            map.put(nextIndex, 0L);
                        }
                        map.put(nextIndex,map.get(nextIndex)+quant);
                    }
                }
                //System.out.println(map.size());
            }
            if(map.containsKey(pattern.length)){
                numPossible+=map.get(pattern.length);
            }
        }
        System.out.println(numPossible);
    }

    public static boolean atIndex(char[] bigger, char[] smaller, int index){

        try{
            for(int i=0; i<smaller.length; i++){
                if(bigger[index+i] != smaller[i]){
                    return false;
                }
            }
            return true;
        }
        catch (IndexOutOfBoundsException e){
            return false;
        }
    }
}

