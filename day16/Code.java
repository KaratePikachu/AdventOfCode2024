
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    static ArrayList<ArrayList<Position>> map = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        //Data Structure
        Position end = null;

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
            //System.out.println("Picked's Parent: "+picked.parent[0]+" "+picked.parent[1]);
            //System.out.println("Picked: "+pickedIndex[0]+" "+pickedIndex[1]+": "+picked.value);
            //Update Neighbors
            int[][] directions = new int[][]{
                    {-1,0},
                    {0,1},
                    {1,0},
                    {0,-1}
            };

            for(int[] dir : directions){
                Position neighbor = map.get(pickedIndex[0]+dir[0]).get(pickedIndex[1]+dir[1]);

                if(neighbor == Position.WALL){
                    continue;
                }

                int travelValue = picked.value;
                if(dir[0]+picked.parent[0] == 0 && dir[1]+picked.parent[1] == 0){
                    travelValue+=1;

                }
                else{
                    travelValue+=1001;
                }



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
        for(ArrayList<Position> row : map){
            for(Position p : row){
                if(p==Position.WALL){
                    System.out.print("#");
                }
                else{
                    System.out.print(".");
                }
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
