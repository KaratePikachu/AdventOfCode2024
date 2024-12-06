import java.util.*;
import java.io.*;

class Node {
    public char type;
    public HashSet<Character> hasWalked;
    public Node(char type) {
        this.type = type;
        this.hasWalked = new HashSet<>();
    }
}


public class Day6 {
    static String fileName = "input.txt";

    public static void main(String[] args) throws Exception {
        //part1();
        part2();
    }

    public static void part2() throws Exception {
        ArrayList<ArrayList<Node>> map = generateMap();



        int[] startLocation = null;

        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                if(map.get(i).get(j).type == '^'){
                    startLocation = new int[]{i,j};
                }
            }
        }
//        for(ArrayList<Node> row : map){
//            for(Node item : row){
//                System.out.print(item.type);
//            }
//            System.out.println();
//        }

        //This solves part 1
        plotPath(map,startLocation[0],startLocation[1]);

        //printMap(map,-1,-1,true);

        int sum = 0;
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                if(map.get(i).get(j).hasWalked.isEmpty()){
                    continue;
                }
                if(i == startLocation[0] && j == startLocation[0]){
                    continue;
                }

                ArrayList<ArrayList<Node>> modifiedMap = generateMap();
                modifiedMap.get(i).get(j).type = '#';
                if(detectedCycle(modifiedMap,startLocation[0],startLocation[1])){
                    sum++;
                    //printMap(modifiedMap,i,j,false);

                }
            }
        }

        System.out.println(sum);

    }

    public static void printMap(ArrayList<ArrayList<Node>> map, int boxi, int boxj, boolean seePath){
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                if(i == boxi && j == boxj){
                    System.out.print('O');
                }
                else if(seePath && !map.get(i).get(j).hasWalked.isEmpty()){
                    System.out.print('+');
                }
                else{
                    System.out.print(map.get(i).get(j).type);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean detectedCycle(ArrayList<ArrayList<Node>> map, int starti, int startj){
        int[] location = new int[]{starti,startj};
        char direction = 'U';

        boolean success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
        if(!success){
            //System.out.println("???");
        }

        while(!hitBorder(map,location)){

            if(hittingBox(map,location,direction)){
                if(direction=='U'){
                    direction = 'R';
                }
                else if(direction=='R'){
                    direction = 'D';
                }
                else if(direction=='D'){
                    direction = 'L';
                }
                else {
                    direction = 'U';
                }

                success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
                if(!success){
                    //System.out.println("???");
                    return true;
                }
                continue;
            }

            //Step
            if(direction=='U'){
                location[0]--;
            }
            else if(direction=='R'){
                location[1]++;
            }
            else if(direction=='D'){
                location[0]++;
            }
            else {
                location[1]--;
            }

            success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
            if(!success){
                //System.out.println("???");
                return true;
            }

        }

        return false;
    }

    public static void plotPath(ArrayList<ArrayList<Node>> map, int starti, int startj){
        int[] location = new int[]{starti,startj};
        char direction = 'U';

        boolean success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
        if(!success){
            System.out.println("???");
        }

        while(!hitBorder(map,location)){

            if(hittingBox(map,location,direction)){
                if(direction=='U'){
                    direction = 'R';
                }
                else if(direction=='R'){
                    direction = 'D';
                }
                else if(direction=='D'){
                    direction = 'L';
                }
                else {
                    direction = 'U';
                }

                success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
                if(!success){
                    System.out.println("???");
                }
                continue;
            }

            //Step
            if(direction=='U'){
                location[0]--;
            }
            else if(direction=='R'){
                location[1]++;
            }
            else if(direction=='D'){
                location[0]++;
            }
            else {
                location[1]--;
            }

            success = map.get(location[0]).get(location[1]).hasWalked.add(direction);
            if(!success){
                System.out.println("???");
            }

        }

//        int sum = 0;
//        for(ArrayList<Node> row : map){
//            for(Node item : row){
//                if(!item.hasWalked.isEmpty()){
//                    sum++;
//                }
//            }
//        }
//        System.out.println(sum);
    }

    public static boolean hittingBox(ArrayList<ArrayList<Node>> map, int[] location, char direction){
        if(direction=='U'){
            return map.get(location[0]-1).get(location[1]).type == '#';
        }
        else if(direction=='R'){
            return map.get(location[0]).get(location[1]+1).type == '#';
        }
        else if(direction=='D'){
            return map.get(location[0]+1).get(location[1]).type == '#';
        }
        else {
            return map.get(location[0]).get(location[1]-1).type == '#';
        }
    }

    public static boolean hitBorder(ArrayList<ArrayList<Node>> map, int[] location){
        return location[0] == 0 || location[1] == 0 || location[0] == map.size()-1 || location[1] == map.get(location[0]).size() -1;

    }

    public static ArrayList<ArrayList<Node>> generateMap() throws FileNotFoundException {
        ArrayList<ArrayList<Node>> map = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            ArrayList<Node> row = new ArrayList<>();
            map.add(row);

            char[] nextLine = myReader.nextLine().toCharArray();

            for(char item : nextLine){
                row.add(new Node(item));
            }

            //Read Logic here

        }

        return map;
    }

//    public static ArrayList<ArrayList<Node>> createCopy(ArrayList<ArrayList<Node>> map){
//        ArrayList<ArrayList<Node>> mapCopy = new ArrayList<>();
//        return null;
//    }
}