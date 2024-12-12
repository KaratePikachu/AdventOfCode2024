import java.util.*;
import java.io.*;

public class Code{
    public static int paths = 0;

    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        ArrayList<ArrayList<Integer>> map = createMap();

        ArrayList<Integer> startIs = new ArrayList<>();
        ArrayList<Integer> startJs = new ArrayList<>();
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                if(map.get(i).get(j) == 0){
                    startIs.add(i);
                    startJs.add(j);
                }
            }
        }

        for(int i=0; i<startIs.size(); i++){

            traverse(map,startIs.get(i),startJs.get(i),0);
            //map = createMap();
        }

        System.out.println(paths);

        

        //System.out.println(result);

        
    }

    public static void traverse(ArrayList<ArrayList<Integer>> map, int i, int j, int level){
        if(level == 9){
            Code.paths++;
            //map.get(i).set(j,-1);
            return;
        }

        boolean foundHigher = false;
        try{
            if(map.get(i-1).get(j)==level+1){
                traverse(map,i-1,j,level+1);
                foundHigher=true;
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        try{
            if(map.get(i+1).get(j)==level+1){
                traverse(map,i+1,j,level+1);
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        try{
            if(map.get(i).get(j-1)==level+1){
                traverse(map,i,j-1,level+1);
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        try{
            if(map.get(i).get(j+1)==level+1){
                traverse(map,i,j+1,level+1);
            }
        }
        catch(IndexOutOfBoundsException ignored){}
    }

    public static ArrayList<ArrayList<Integer>> createMap() throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> map = new ArrayList<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){
            ArrayList<Integer> row = new ArrayList<>();
            map.add(row);
            char[] nextLine = myReader.nextLine().toCharArray();

            for(char c : nextLine){
                if(c == '.'){
                    row.add(-1);
                }
                else{
                    row.add(Integer.parseInt(""+c));
                }
            }

            //Read Logic here

        }
        return map;
    }

    public static void printThing(ArrayList<ArrayList<Integer>> in){
        for(ArrayList<Integer> row : in){
            for(int i : row){
                if(i==-1){
                    System.out.print("  ");
                }
                else{
                    System.out.print(i);
                }
            }
            System.out.println();
        }
    }
}
