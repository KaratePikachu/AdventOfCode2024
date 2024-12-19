
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    static ArrayList<ArrayList<Position>> map = new ArrayList<>();
    static ArrayList<int[]> rocks = new ArrayList<int[]>();
    static int mapSize = 71;

    public static void main(String[] args) throws Exception{
        //Data Structure


        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            String[] coord = myReader.nextLine().split(",");
            rocks.add(new int[]{Integer.parseInt(coord[1]),Integer.parseInt(coord[0])});
        }

        for(int i=0; i<mapSize; i++){
            ArrayList<Position> row = new ArrayList<>();
            map.add(row);
            for(int j=0; j<mapSize; j++){
                if(i== 0 && j== 0){
                    row.add(new Position(0));
                }
                else{
                    row.add(new Position(Integer.MAX_VALUE));
                }
            }
        }


        for(int i=0; i<1024; i++){
            int[] rock = rocks.get(i);
            map.get(rock[0]).set(rock[1],Position.WALL);
        }

        //Pick smallest and mark as picked
        int[] pickedIndex = pickSmallest();
        Position picked = map.get(pickedIndex[0]).get(pickedIndex[1]);


        while(true){
            //Update Neighbors
            int[][] directions = new int[][]{
                    {-1,0},
                    {0,1},
                    {1,0},
                    {0,-1}
            };

            for(int dirIndex = 0; dirIndex<directions.length; dirIndex++){
                int[] dir = directions[dirIndex];

                if(!inBounds(pickedIndex[0]+dir[0],pickedIndex[1]+dir[1])){
                    continue;
                }


                int[] neighborIndex = new int[]{pickedIndex[0]+dir[0], pickedIndex[1]+dir[1]};
                Position neighbor = map.get(neighborIndex[0]).get(neighborIndex[1]);

                if(neighbor == Position.WALL){
                    continue;
                }

                int travelValue = picked.value+1;



                if(travelValue< neighbor.value){

                    neighbor.value = travelValue;

                    //System.out.println((pickedIndex[0]+dir[0])+", "+(pickedIndex[1]+dir[1])+": "+neighbor.value);
                    neighbor.parent = new int[]{-dir[0],-dir[1]};
                }

                //System.out.println(neighbor);
            }

            pickedIndex = pickSmallest();
            if(pickedIndex == null){
                break;
            }
            picked = map.get(pickedIndex[0]).get(pickedIndex[1]);
        }

        printThing();
        //printThing(map);

        //System.out.println(result);
    }

    public static boolean inBounds(int i, int j){
        try{
            map.get(i).get(j);
            return true;
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public static int[] pickSmallest(){
        int[] smallestIndex = null;
        int smallestValue = Integer.MAX_VALUE;

        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                Position p = map.get(i).get(j);
                if(p !=Position.WALL && !p.picked && p.value < smallestValue){
                    smallestValue = p.value;
                    smallestIndex = new int[]{i,j};
                }
            }
        }
        if(smallestIndex!=null){
            map.get(smallestIndex[0]).get(smallestIndex[1]).picked = true;
            //System.out.println("Set "+smallestIndex[0]+", "+smallestIndex[1]+" as picked");
        }
        return smallestIndex;
    }

    public static void printThing(){
        ArrayList<ArrayList<Character>> printMap = new ArrayList<>();
        for(ArrayList<Position> row : map){
            ArrayList<Character> printRow = new ArrayList<>();
            printMap.add(printRow);
            for(Position p : row){
                if(p==Position.WALL){
                    printRow.add('#');
                }
                else{

                    printRow.add('.');
                }
            }
        }

        System.out.println(map.get(mapSize-1).get(mapSize-1).value);
        int[] curr = new int[]{mapSize-1,mapSize-1};
        while(map.get(curr[0]).get(curr[1]).value != 0){
            printMap.get(curr[0]).set(curr[1],'0');
            int[] dir = map.get(curr[0]).get(curr[1]).parent;
            curr[0]+=dir[0];
            curr[1]+=dir[1];
        }

        for(ArrayList<Character> r : printMap){
            for(char c : r){
                System.out.print(c);
            }
            System.out.println();
        }

    }
}

class Position{
    public static Position WALL = new Position(-1000);
    public int value;
    public boolean picked;
    public int[] parent;

    public Position(int value){
        this.value=value;
        picked = false;
    }

    public void setParent(int vi, int vj){
        parent = new int[]{vi,vj};
    }

    @Override
    public String toString(){
        if(parent == null){
            return value+", (null)";
        }
        return value+", ("+parent[0]+", "+parent[1]+")";
    }
}