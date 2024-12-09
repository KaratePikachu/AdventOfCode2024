import java.util.*;
import java.io.*;
class Pair{
    public int i;
    public int j;
    public Pair(int i, int j){
        this.i = i;
        this.j = j;
    }
}

public class Code{

    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }



    public static void part1() throws Exception{
        int mapHeight = 0;
        int mapLength = 0;
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        HashMap<Character,ArrayList<Pair>> signals = new HashMap<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){
            ArrayList<Character> row = new ArrayList<>();
            map.add(row);

            char[] nextLine = myReader.nextLine().toCharArray();
            mapLength = nextLine.length;

            //Read Logic here
            for(int i=0; i<nextLine.length; i++){
                row.add(nextLine[i]);
                if(nextLine[i] != '.'){
                    if(!signals.containsKey(nextLine[i])){
                        signals.put(nextLine[i],new ArrayList<>());
                    }
                    signals.get(nextLine[i]).add(new Pair(mapHeight,i));
                }
            }
            mapHeight++;
        }


        for(char tower : signals.keySet()){
            ArrayList<Pair> single = signals.get(tower);

            for(int i=0; i<single.size(); i++){
                for(int j=i+1; j<single.size(); j++){
                    int heightDiff = single.get(j).i-single.get(i).i;
                    int lengthDiff = single.get(j).j-single.get(i).j;

                    try{
                        map.get(single.get(j).i+heightDiff).set(single.get(j).j+lengthDiff,'#');
                    }
                    catch(IndexOutOfBoundsException e){}
                    try{
                        map.get(single.get(i).i-heightDiff).set(single.get(i).j-lengthDiff,'#');
                    }
                    catch(IndexOutOfBoundsException e){}

                }
            }
        }

        int sum = 0;
        for(ArrayList<Character> row : map){
            for(char c : row){
                if(c == '#'){
                    sum++;
                }
            }
        }

        System.out.println(sum);


        

        //System.out.println(result);

        
    }
}
