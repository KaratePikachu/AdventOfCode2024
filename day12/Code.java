import java.util.*;
import java.io.*;
class Node{
    boolean[] usedDir;

    boolean inBlob;
    public Node(boolean inBlob){
        usedDir = new boolean[4];
        this.inBlob = inBlob;
    }
}

public class Code{
    static long perimeter = 0;
    static long area = 0;

    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        //Data Structure
        ArrayList<ArrayList<Character>> map = createMap();
        //---------------------

        long sum = 0;
        for(int i=0; i<map.size(); i++){
            for( int j=0; j<map.get(i).size(); j++){
                if(map.get(i).get(j) == ' '){
                    continue;
                }
                area = 0;
                perimeter = 0;
                System.out.println("Region: "+map.get(i).get(j));
                floodFill(map,i,j,'.');

                count(map);
                edges(toSpecial(map));
                //System.out.println(area+" "+perimeter+": "+(area*perimeter));
                sum+=area*perimeter;
                floodFill(map,i,j,' ');
            }
        }


        

        System.out.println(sum);

        
    }

    public static void edges(ArrayList<ArrayList<Node>> map){
        int[][] dirs = {
                {1,0},
                {0,1},
                {-1,0},
                {0,-1}
        };



        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                if(!map.get(i).get(j).inBlob){
                    continue;
                }


                for(int forward=0; forward<4; forward++){
                    //Up
                    int left = forward == 0 ? 3 : forward-1;
                    int right = (forward+1)%4;
                    if( (!nodeInBounds(map,i+dirs[forward][0],j+dirs[forward][1]) || !map.get(i+dirs[forward][0]).get(j+dirs[forward][1]).inBlob) && !map.get(i).get(j).usedDir[forward] ){
                        perimeter++;
                        int y = i;
                        int x = j;
                        while(nodeInBounds(map,x,y) && map.get(y).get(x).inBlob){
                            map.get(y).get(x).usedDir[forward] = true;
                            y+=dirs[left][0];
                            x+=dirs[left][1];
                        }

                        y = i;
                        x = j;
                        while(nodeInBounds(map,x,y) && map.get(y).get(x).inBlob){
                            map.get(y).get(x).usedDir[forward] = true;
                            y+=dirs[right][0];
                            x+=dirs[right][1];
                        }
                    }
                }
            }
        }





    }

    public static void count(ArrayList<ArrayList<Character>> map){
        int[][] locs = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        for(int i=0; i<map.size(); i++){
            for( int j=0; j<map.get(i).size(); j++){
                if(map.get(i).get(j) != '.'){
                    continue;
                }
                area++;
            }
        }
    }

    public static void floodFill(ArrayList<ArrayList<Character>> map, int i, int j, char val){
        char oldVal = map.get(i).get(j);
        int[][] locs = {
                {-1,0},
                {1,0},
                {0,-1},
                {0,1}
        };

        map.get(i).set(j,val);

        for(int[] loc : locs){
            if(inBounds(map,i+loc[0],j+loc[1]) && map.get(i+loc[0]).get(j+loc[1]) == oldVal){
                floodFill(map,i+loc[0],j+loc[1], val);
            }
        }
    }

    public static boolean inBounds(ArrayList<ArrayList<Character>> map, int i, int j){
        try{
            map.get(i).get(j);
            return true;
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public static boolean nodeInBounds(ArrayList<ArrayList<Node>> map, int i, int j){
        try{
            map.get(i).get(j);
            return true;
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public static ArrayList<ArrayList<Node>> toSpecial(ArrayList<ArrayList<Character>> map){
        ArrayList<ArrayList<Node>> newOne = new ArrayList<>();
        for(ArrayList<Character> row : map){
            ArrayList<Node> newRow = new ArrayList<>();
            newOne.add(newRow);
            for(char c : row){
                newRow.add(new Node(c == '.'));
            }
        }
        return newOne;
    }



    public static ArrayList<ArrayList<Character>> createMap() throws FileNotFoundException {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){
            ArrayList<Character> row = new ArrayList<>();
            map.add(row);
            char[] nextLine = myReader.nextLine().toCharArray();
            //Read Logic here
            for(char c : nextLine){
                row.add(c);
            }

        }

        return map;
    }
}
