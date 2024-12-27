import java.util.*;
import java.io.*;

class Position{
    public int x;
    public int y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

}
public class Code{
    public static String fileName = "test.txt";
    final static int CONFIRM = 10;

    final static int UP = 11;
    final static int RIGHT = 12;
    final static int DOWN = 13;
    final static int LEFT = 14;

    final static int CACHESTART = 20;
    static int CACHEID = 0;


    static HashMap<Integer,Position> numPad;
    static HashMap<Integer,Position> keyPad;

    static TreeMap<Integer,Integer> cacheTransfer = new TreeMap<>();
    static TreeMap<Integer,ArrayList<Integer>> cacheValues = new TreeMap<>();
    static TreeMap<ArrayList<Integer>,Integer> cacheIDs = new TreeMap<>(new CustomComparator());

    static class CustomComparator implements Comparator<ArrayList<Integer>> {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {

            if(o1.size()<o2.size()){
                return -1;
            }
            else if(o1.size()>o2.size()){
                return 1;
            }

            for(int i=0; i<o1.size(); i++){
                if(o1.get(i)<o2.get(i)){
                    return -1;
                }
                else if(o1.get(i)>o2.get(i)){
                    return 1;
                }
            }
            return 0;
        }
    }

    public static HashMap<Integer,Position> createNumPad(){
        HashMap<Integer,Position> curr = new HashMap<>();
        curr.put(7,new Position(0,0));
        curr.put(8,new Position(1,0));
        curr.put(9,new Position(2,0));

        curr.put(4,new Position(0,1));
        curr.put(5,new Position(1,1));
        curr.put(6,new Position(2,1));

        curr.put(1,new Position(0,2));
        curr.put(2,new Position(1,2));
        curr.put(3,new Position(2,2));

        curr.put(0,new Position(1,3));
        curr.put(CONFIRM,new Position(2,3));

        return curr;
    }

    public static HashMap<Integer,Position> createKeyPad(){
        HashMap<Integer,Position> curr = new HashMap<>();

        curr.put(UP,new Position(1,0));
        curr.put(CONFIRM,new Position(2,0));

        curr.put(LEFT,new Position(0,1));
        curr.put(DOWN,new Position(1,1));
        curr.put(RIGHT,new Position(2,1));

        return curr;
    }

    public static void main(String[] args) throws Exception{
        //Data Structure
        numPad = createNumPad();
        keyPad = createKeyPad();




        Scanner myReader = new Scanner(new File(fileName));
        long result = 0;
        while(myReader.hasNextLine()){
            LinkedList<Integer> inputs = new LinkedList<>();
            LinkedList<Integer> queue = new LinkedList<>();
            String nextLine = myReader.nextLine();
            for(char c : nextLine.toCharArray()){
                if(c == 'A'){
                    inputs.add(CONFIRM);
                }
                else{
                    inputs.add(Integer.parseInt(""+c));
                }
            }
            //print(inputs);
            prime(inputs,queue);
            Position pos = new Position(2,3);//Keypad A
            //numpad

            for(int input : inputs){
                while(!queue.isEmpty()){
                    int val = queue.removeFirst();
                    Position newPos = numPad.get(val);
                    int xDiff = newPos.x-pos.x;
                    int yDiff = newPos.y-pos.y;
                    addDirs(inputs,pos,xDiff,yDiff,"numPad");
                    pos = newPos;
                }



            }
//            while(!queue.isEmpty()){
//                int val = queue.removeFirst();
//                Position newPos = numPad.get(val);
//                int xDiff = newPos.x-pos.x;
//                int yDiff = newPos.y-pos.y;
//                addDirs(inputs,pos,xDiff,yDiff,"numPad");
//                pos = newPos;
//            }
//            //print(inputs);
//            //keypad
//            for(int rep = 0; rep<25; rep++){
//                prime(inputs,queue);
//                pos = new Position(2,0);//Keypad A
//                while(!queue.isEmpty()){
//                    int val = queue.removeFirst();
//                    Position newPos = keyPad.get(val);
//                    int xDiff = newPos.x-pos.x;
//                    int yDiff = newPos.y-pos.y;
//                    addDirs(inputs,pos,xDiff,yDiff,"keyPad");
//                    pos = newPos;
//                }
//                System.out.println(inputs.size());
//                System.out.println(rep);
//            }






            //System.out.println(inputs.size());
            //System.out.println(inputs.size()*Integer.parseInt(nextLine.split("A")[0]));
            //System.out.println(inputs.size()+"*"+Long.parseLong(nextLine.split("A")[0]));
            result+=inputs.size()*Long.parseLong(nextLine.split("A")[0]);
        }





        System.out.println(result);
    }

    public static void addDirs(LinkedList<Integer> inputs, Position pos, int xDiff, int yDiff, String mode){
        if(mode.equals("numPad") && pos.y == 3 && pos.x+xDiff == 0){
            while(yDiff<0){
                inputs.add(UP);
                yDiff++;
            }
        }

        if(mode.equals("numPad") && pos.x == 0 && pos.y+yDiff == 3){
            while(xDiff>0){//Right
                inputs.add(RIGHT);
                xDiff--;
            }
        }


        if(mode.equals("keyPad") && pos.y == 0 && pos.x == 2 && xDiff == -2){
            inputs.add(DOWN);
            yDiff--;
            inputs.add(LEFT);
            xDiff++;

            inputs.add(LEFT);
            xDiff++;
        }

        if(mode.equals("numPad")){
            while(xDiff<0){
                inputs.add(LEFT);
                xDiff++;
            }
            while(yDiff>0){
                inputs.add(DOWN);
                yDiff--;
            }
            while(xDiff>0){//Right
                inputs.add(RIGHT);
                xDiff--;
            }
            while(yDiff<0){
                inputs.add(UP);
                yDiff++;
            }


        }
        else if(mode.equals("keyPad")){
            while(xDiff<0){
                inputs.add(LEFT);
                xDiff++;
            }
            while(yDiff>0){
                inputs.add(DOWN);
                yDiff--;
            }
            while(xDiff>0){//Right
                inputs.add(RIGHT);
                xDiff--;
            }
            while(yDiff<0){
                inputs.add(UP);
                yDiff++;
            }

        }






        inputs.add(CONFIRM);
    }

    public static void prime(LinkedList<Integer> a, LinkedList<Integer> b){
        b.clear();
        while(!a.isEmpty()){
            b.add(a.removeFirst());
        }
    }

    public static void print(LinkedList<Integer> list){
        for(int i : list){
            if(i == UP){
                System.out.print("^");
            }
            else if(i == RIGHT){
                System.out.print(">");
            }
            else if(i == DOWN){
                System.out.print("v");
            }
            else if(i == LEFT){
                System.out.print("<");
            }
            else if(i == CONFIRM){
                System.out.print("A");
            }
            else{
                System.out.print(i);
            }
        }
        System.out.println();
    }
}

