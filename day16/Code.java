
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    static ArrayList<ArrayList<Position>> map = new ArrayList<>();
    static Position end = null;


    static ArrayList<int[]> plusIntersections = new ArrayList<>();
    static ArrayList<int[]> equalPaths = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        //Data Structure


        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            ArrayList<Position> row = new ArrayList<>();
            map.add(row);

            for(char c : myReader.nextLine().toCharArray()){
                Position newPos;
                if(c == 'S'){
                    newPos = new Position(0);
                    newPos.parent = new int[]{0,-1};
                }
                else if(c != '#'){
                    newPos = new Position(Integer.MAX_VALUE);
                    if(c == 'E'){
                        end = newPos;
                    }
                }
                else{
                    newPos = Position.WALL;
                }
                row.add(newPos);
            }

            //Read Logic here

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
                int[] neighborIndex = new int[]{pickedIndex[0]+dir[0], pickedIndex[1]+dir[1]};
                Position neighbor = map.get(neighborIndex[0]).get(neighborIndex[1]);

                if(neighbor == Position.WALL ||  Math.abs(dir[0]+picked.parent[0]) == 2 || Math.abs(dir[1]+picked.parent[1]) == 2){
                    continue;
                }

                int travelValue = picked.value;
                if(dir[0]+picked.parent[0] == 0 && dir[1]+picked.parent[1] == 0){
                    travelValue+=1;
                }
                else{
                    travelValue+=1001;
                }

                boolean frontClear = map.get(neighborIndex[0]+dir[0]).get(neighborIndex[1]+dir[1]) != Position.WALL;
                //Check if both sides are clear
                int[] left= directions[dirIndex == 0 ? 3 : dirIndex-1];
                int[] right= directions[(dirIndex+1)%directions.length];
                boolean sidesClear = (map.get(neighborIndex[0]+left[0]).get(neighborIndex[1]+left[1]) != Position.WALL) && (map.get(neighborIndex[0]+right[0]).get(neighborIndex[1]+right[1]) != Position.WALL);

                if(neighbor.picked && neighbor.value+1000 == travelValue){
                    equalPaths.add(new int[]{neighborIndex[0],neighborIndex[1]});
                }

                //Actually though, fuck these edge cases.
                boolean forceUpdate = false;

                if(neighbor.picked && neighbor.value+1000>travelValue){

                    if(frontClear && !sidesClear){
                        //System.out.println("FRont edge case");
                        map.get(neighborIndex[0]+dir[0]).get(neighborIndex[1]+dir[1]).value = travelValue+1;
                        forceUpdate = true;
                    }
                    else if(sidesClear && !frontClear){
                        //System.out.println("Side edge case");
                    }
                    else if(!sidesClear && !frontClear){

                    }
                    else{
                        System.out.println("Bad!!!"+ (neighborIndex[0])+" "+(neighborIndex[1]));
                        plusIntersections.add(new int[]{neighborIndex[0],neighborIndex[1]});
                    }
                }


                if(travelValue< neighbor.value || forceUpdate){

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
        System.out.println(end.value);
        //printThing(map);

        //System.out.println(result);
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

        int[] curr = new int[]{1,printMap.get(0).size()-2};
        while(map.get(curr[0]).get(curr[1]).value != 0){
            printMap.get(curr[0]).set(curr[1],'0');
            int[] dir = map.get(curr[0]).get(curr[1]).parent;
            curr[0]+=dir[0];
            curr[1]+=dir[1];
        }

        for(int[] plus : equalPaths){
            printMap.get(plus[0]).set(plus[1],'+');
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
