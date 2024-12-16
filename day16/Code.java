import java.util.*;
import java.io.*;
class Position implements Comparable<Position>{
    public int x;
    public int y;
    public long value;
    public int parent = -1;

//    public Position(int x, int y){
//        this.x=x;
//        this.y=y;
//        this.value = Long.MAX_VALUE;
//    }

    public Position(int x, int y, long value){
        this.x=x;
        this.y=y;
        this.value = value;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Position otherPos){
            return this.x==otherPos.x && this.y==otherPos.y;
        }
        return false;
    }

    public static boolean sameXY(Position first, Position second) {
        return first.x == second.x && first.y ==second.y;
    }


    @Override
    public int compareTo(Position o) {
        int diff = (int) (this.value-o.value);
        if(diff !=0){
            return -diff;
        }

        diff = (int) (this.x-o.x);
        if(diff!=0){
            return -diff;
        }

        diff = (int) (this.y-o.y);
        return -diff;
    }

    @Override
    public String toString(){
        return x+", "+y+": "+value;
    }
}
public class Code{
    static TreeSet<Position> picked = new TreeSet<>();
    static PriorityQueue<Position> values = new PositionPriorityQueue();

    //Feed all the values into "values"
    //Keep popping off the top and removing/updating them back into values;

    public static String fileName = "test.txt";
    public static void main(String[] args) throws Exception{

        Scanner myReader = new Scanner(new File(fileName));
        int y = 0;
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();

            char[] chars = nextLine.toCharArray();
            for(int x=0; x < chars.length; x++){
                Position newPos;
                if(chars[x] == 'S'){
                    newPos = new Position(x,y,0);
                    newPos.parent = 3;
                }
                else if(chars[x] != '#'){
                    newPos = new Position(x,y,Long.MAX_VALUE);
                    newPos.parent = -1;
                }
                else{
                    continue;
                }

                values.add(newPos);

            }

            //Read Logic here
            y++;
        }



        System.out.println(values.peek());
    }

    private static class PositionPriorityQueue extends PriorityQueue<Position> {
        public ArrayList<Position> getAdjacents(Position p){
            ArrayList<Position> positions = new ArrayList<>();
            int[][] offsets = {
                    {0,1},
                    {0,-1},
                    {1,0},
                    {-1,0}
            };
            for(Position other : this){
                for(int[] nudge : offsets){
                    if(Position.sameXY(other,new Position(p.x,p.y,Long.MAX_VALUE))){
                        positions.add(other);
                    }
                }
            }

            return positions;
        }
    }
}

