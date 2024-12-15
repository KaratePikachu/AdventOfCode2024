import java.awt.*;
import java.util.*;
import java.io.*;

class Robot{
    public int x;
    public int y;
    public int vx;
    public int vy;
    public Robot(int x, int y, int vx, int vy){
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
    }
}
public class Code{
    public static String fileName = "input.txt";
    public static int width = 101;
    public static int height = 103;
    public static int iterations = 100;

    public static void main(String[] args) throws Exception{

            ArrayList<Robot> robots = new ArrayList<>();


            //Data Structure

            Scanner myReader = new Scanner(new File(fileName));
            while(myReader.hasNextLine()){

                String[] nextLine = myReader.nextLine().split("[=, ]");
                int x = Integer.parseInt(nextLine[1]);
                int y = Integer.parseInt(nextLine[2]);
                int vx =Integer.parseInt(nextLine[4]);
                int vy =Integer.parseInt(nextLine[5]);
                robots.add(new Robot(x,y,vx,vy));
                //Read Logic here

            }

            int numAns = 0;
        int numLoops = 0;
        while(true){

            numLoops++;
            //System.out.println(numLoops);
            boolean noOverlaps = true;

            for(Robot robot : robots){

                robot.x=loop(robot.x+robot.vx,width);
                robot.y=loop(robot.y+robot.vy,height);

            }

            for(int i=robots.size()-1; i>=0; i--){
                for(int j=i-1; j>=0; j--){
                    if(robots.get(i).x == robots.get(j).x && robots.get(i).y == robots.get(j).y){
                        //System.out.println("Overlap!");
                        noOverlaps = false;

                    }
                }
            }

            if(noOverlaps){
                numAns++;
                if(numAns==3){
                    System.out.println(numLoops);
                    visualize(robots,width,height);
                    break;
                }
            }
        }







//        int[] sums = new int[]{0,0,0,0};
//        for(Robot robot : robots){
//            int quad = 0;
//
//            if(robot.x>width/2){
//                quad+=2;
//            }
//            else if(robot.x<width/2){
//
//            }
//            else{
//                continue;
//            }
//
//            if(robot.y>height/2){
//                quad+=1;
//            }
//            else if(robot.y<height/2){
//
//            }
//            else{
//                continue;
//            }
//
//            //System.out.println(robot.x+" "+robot.y+" Quad "+ quad);
//
//            sums[quad]++;
//        }




        //System.out.println(sums[0]+" "+sums[1]+" "+sums[2]+" "+sums[3]);
        //System.out.println(sums[0]*sums[1]*sums[2]*sums[3]);
    }

    private static void visualize(ArrayList<Robot> robs, int xB, int yB){
        boolean[][] temp = new boolean[yB][xB];
        for(int i = 0; i < robs.size(); i++){
            temp[robs.get(i).y][robs.get(i).x] = true;
        }
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[i].length; j++){
                System.out.print(temp[i][j]?"O":" ");
            }
            System.out.println("");
        }
    }

    public static int loop(int val, int max){
        while(val<0){
            val+=max;
        }
        val = val%max;
        return val;
    }
}

