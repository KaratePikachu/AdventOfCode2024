import java.util.*;
import java.io.*;
class Position implements Comparable<Position>{
    public int x;
    public int y;
    public long value;
    public char parent;

//    public Position(int x, int y){
//        this.x=x;
//        this.y=y;
//        this.value = Long.MAX_VALUE;
//    }

    public Position(int x, int y, long value){
        this.x=x;
        this.y=y;
        this.value = value;
        this.parent = ' ';
    }

    public Position(Position other) {
        this.x=other.x;
        this.y=other.y;
        this.value=other.value;
        this.parent=other.parent;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Position otherPos){
            return this.x==otherPos.x && this.y==otherPos.y;
        }
        return false;
    }

    public static boolean sameXY(Position first, Position second) {
//        if(first.x == second.x && first.y ==second.y){
//            System.out.println(first.toString()+" "+second.toString());
//        }
        return first.x == second.x && first.y ==second.y;
    }

    public static boolean inLine(Position source, Position destination){
        if(source.parent == ' '){

            System.out.println("Critical Error: Parent somehow -1");
            System.out.println(source);
            System.exit(-1);
        }

        //U/D is 0/2
        //LR is 1/3
        if(source.parent=='e' || source.parent =='w'){//Left Right case, want different left right
            return source.x-destination.x != 0;
        }
        else{
            return source.y-destination.y != 0;
        }
    }

    public void setParent(Position other){
        int xDiff = this.x-other.x;
        int yDiff = this.y-other.y;

        if(yDiff == 1){
            this.parent = 'n';
        }
        else if(xDiff == -1){
            this.parent = 'e';
        }
        else if(yDiff == -1){
            this.parent = 's';
        }
        else if(xDiff == 1){
            this.parent = 'w';
        }
        else{
            System.out.println("??? non normal diff");
            System.exit(-1);
        }
    }


    @Override
    public int compareTo(Position o) {
        int diff = (int) (this.value-o.value);
        if(diff !=0){
            return -diff;
        }

        diff = (int) (this.x-o.x);
        if(diff!=0){
            return diff;
        }

        diff = (int) (this.y-o.y);
        return diff;
    }

    @Override
    public String toString(){
        return x+", "+y+": "+parent;
    }
}
public class Code{
    static PositionPriorityQueue picked = new PositionPriorityQueue();
    static PositionPriorityQueue values = new PositionPriorityQueue();

    //Feed all the values into "values"
    //Keep popping off the top and removing/updating them back into values;

    public static String fileName = "test2.txt";
    public static void main(String[] args) throws Exception{
        Position end = null;
        Scanner myReader = new Scanner(new File(fileName));
        int y = 0;
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();

            char[] chars = nextLine.toCharArray();
            for(int x=0; x < chars.length; x++){
                Position newPos;
                if(chars[x] == 'S'){
                    newPos = new Position(x,y,0);
                    newPos.parent = 'w';
                }
                else if(chars[x] != '#'){
                    newPos = new Position(x,y,Long.MAX_VALUE);
                    if(chars[x] == 'E'){
                        end = newPos;
                    }
                }
                else{
                    continue;
                }

                values.add(newPos);

            }

            //Read Logic here
            y++;
        }


        while(!values.isEmpty()){
            Position pickedPos = values.remove();

            //System.out.println("-----");
            //System.out.println("Picked: "+pickedPos.toString()+" "+pickedPos.value);
//            for(Position p : values){
//                if(pickedPos.value>p.value){
//                    System.out.println("Somehow picked value too big: "+p.toString()+" "+p.value);
//                }
//            }

            //System.out.println(Position.sameXY(pickedPos, new Position(pickedPos)));
            picked.add(pickedPos);
            for(Position p : values.getAdjacents(pickedPos)){
                if(picked.contains(p)){
                    continue;
                }
                long travelWeight = pickedPos.value;
                values.remove(p);

                if(Position.inLine(pickedPos,p)){
                    travelWeight+=1;
                }
                else{
                    travelWeight+=1001;
                }

                if(travelWeight<p.value){
                    p.value = travelWeight;
                    p.setParent(pickedPos);
                }

                //System.out.println(p.toString());

                values.add(p);


            }
        }






        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        myReader = new Scanner(new File(fileName));

        while(myReader.hasNextLine()){
            ArrayList<Character> row = new ArrayList<>();
            map.add(row);
            for(char c : myReader.nextLine().toCharArray()){
                row.add(c);
            }


        }





        Position curr = new Position(end);


        while(curr.value!=0){
            map.get(curr.y).set(curr.x,'@');
            //System.out.println(curr+" "+curr.value);
            if(curr.parent =='n'){
                curr.y-=1;
            }
            else if(curr.parent =='e'){
                curr.x+=1;
            }
            else if(curr.parent =='s'){
                curr.y+=1;
            }
            else if(curr.parent =='w'){
                curr.x-=1;
            }
            else{
                System.out.println("PARENT???");
                System.exit(0);
            }

            assert picked.get(curr) != null;
            curr = new Position(picked.get(curr));
        }

        for(ArrayList<Character> row : map){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

        System.out.println(end.value);

    }

    private static class PositionPriorityQueue extends ArrayList<Position> {
        public Position peek(){
            if(this.isEmpty()){
                throw new NoSuchElementException();
            }

            Position smallestValuePos = this.get(0);

            for(Position pos : this){
                if(smallestValuePos.value>pos.value){
                    smallestValuePos = pos;
                }
            }

            return smallestValuePos;
        }

        public Position remove(){
            Position val = peek();
            this.remove(val);
            return val;
        }

        public ArrayList<Position> getAdjacents(Position p){
            ArrayList<Position> positions = new ArrayList<>();
            int[][] offsets = {
                    {-1,0},//UP
                    {0,1},//RIGHT
                    {1,0},//DOWN
                    {0,-1}//LEFT
            };
            for(Position other : this){
                for(int i=0; i<offsets.length; i++){
//                    if((i+2)%4==p.parent){
//                        continue;
//                    }
                    int[] nudge = offsets[i];
                    if(Position.sameXY(other,new Position(p.x+nudge[0],p.y+nudge[1],Long.MAX_VALUE))){
                        positions.add(other);
                    }
                }
            }

            return positions;
        }


        public Position get(Position key){
            for(Position other : this){
                if(Position.sameXY(key,other)){
                    return other;
                }
            }
            return null;
        }
    }
}

