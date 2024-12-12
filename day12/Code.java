import java.util.*;
import java.io.*;

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
                edges(map,i,j);
                System.out.println(area+" "+perimeter+": "+(area*perimeter));
                floodFill(map,i,j,' ');
            }
        }


        

        System.out.println(sum);

        
    }

    public static void edges(ArrayList<ArrayList<Character>> map, int starti, int startj){
        int dir = 0;
        int[][] dirs = {
                {1,0},
                {0,1},
                {-1,0},
                {0,-1}
        };

        int i=starti;
        int j=startj;

        do{
            int[] front = dirs[dir];
            int[] left = (dir==0) ? dirs[3] : dirs[dir-1];
            int[] right = dirs[(dir+1)%4];
            //can turn left
            if(inBounds(map,i+left[0],j+left[1]) && map.get(i+left[0]).get(j+left[1]) == '.'){
                dir = (dir==0) ? 3 : dir-1;
                perimeter++;
                i+=left[0];
                j+=left[1];
            }//forward
            else if(inBounds(map,i+front[0],j+front[1]) && map.get(i+front[0]).get(j+front[1]) == '.'){
                i+=front[0];
                j+=front[1];
            }//right
            else if(!inBounds(map,i+front[0],j+front[1]) || map.get(i+front[0]).get(j+front[1]) != '.'){
                perimeter++;
                dir = (dir+1)%4;
            }

            //System.out.println(i+", "+j+"! dir: "+dir);
        }
        while(i!=starti || j!=startj || dir != 0);



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



    public static ArrayList<ArrayList<Character>> createMap() throws FileNotFoundException {
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        Scanner myReader = new Scanner(new File("test2.txt"));
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
