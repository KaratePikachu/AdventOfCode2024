
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    static ArrayList<ArrayList<Position>> map = new ArrayList<>();

    static int[] start;
    static int[] end;


    public static void instantiateMap() throws FileNotFoundException {
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            ArrayList<Position> row = new ArrayList<>();
            map.add(row);

            for(char c : myReader.nextLine().toCharArray()){
                Position newPos;
                if(c == 'S'){
                    newPos = new Position(0);
                    newPos.parent = new int[]{0,-1};
                    start = new int[]{map.size()-1,map.get(map.size()-1).size()};
                }
                else if(c != '#'){
                    newPos = new Position(Integer.MAX_VALUE);
                    if(c == 'E'){
                        end = new int[]{map.size()-1,map.get(map.size()-1).size()};
                    }
                }
                else{
                    newPos = Position.WALL;
                }
                row.add(newPos);
            }

            //Read Logic here

        }
    }

    public static void main(String[] args) throws Exception{
        //Data Structure


        instantiateMap();

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
                int[] neighborIndex = new int[]{pickedIndex[0]+dir[0], pickedIndex[1]+dir[1]};
                Position neighbor = map.get(neighborIndex[0]).get(neighborIndex[1]);

                if(neighbor == Position.WALL ||  Math.abs(dir[0]+picked.parent[0]) == 2 || Math.abs(dir[1]+picked.parent[1]) == 2){
                    continue;
                }

                int travelValue = picked.value+1;



                //Actually though, fuck these edge cases.



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

        int numJumps = 0;
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(0).size(); j++){
                Position current = map.get(i).get(j);
                if(current == Position.WALL){
                    continue;
                }

                for(int nudgeI=-20; nudgeI<=20; nudgeI++){
                    for(int nudgeJ=-20; nudgeJ<=20; nudgeJ++){
                        try{
                            int cheatDistance = Math.abs(nudgeI)+Math.abs(nudgeJ);
                            if(cheatDistance>20){
                                continue;
                            }
                            Position cheatTo = map.get(i+nudgeI).get(j+nudgeJ);
                            if(cheatTo == Position.WALL){
                                continue;
                            }

                            int valDiff = cheatTo.value-current.value-cheatDistance;
                            if(valDiff >= 100){
                                numJumps++;
                            }

                        }
                        catch (IndexOutOfBoundsException e){
                            continue;
                        }
                    }
                }

//                for(int dirIndex = 0; dirIndex<directions.length; dirIndex++) {
//                    int[] dir = directions[dirIndex];
//                    try{
//
//                        Position n0 = map.get(i).get(j);
//                        Position n1 = map.get(i+dir[0]).get(j+dir[1]);
//                        Position n2 = map.get(i+dir[0]*2).get(j+dir[1]*2);
//                        if( n1 == Position.WALL && n2 != Position.WALL){
//
//                            //System.out.println(n2);
//                            int valDiff = n2.value-n0.value-2;
//                            if(valDiff >= 100){
//                                numJumps++;
//                            }
//                        }
//                    }
//                    catch (IndexOutOfBoundsException e){
//                        continue;
//                    }
//                }
            }
        }
        //printThing(map);

        System.out.println(numJumps);
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

//        int[] curr = new int[]{end[0],end[1]};
//        while(map.get(curr[0]).get(curr[1]).value != 0){
//            printMap.get(curr[0]).set(curr[1],'0');
//            int[] dir = map.get(curr[0]).get(curr[1]).parent;
//            curr[0]+=dir[0];
//            curr[1]+=dir[1];
//        }

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