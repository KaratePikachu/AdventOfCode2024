import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    public static int[] robotPos = new int[2];
    public static void main(String[] args) throws Exception{
        //Data Structure
        ArrayList<ArrayList<Character>> map = new ArrayList<>();
        ArrayList<Character> movements = new ArrayList<>();


        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            if(nextLine.trim().isEmpty()){
                break;
            }

            ArrayList<Character> row = new ArrayList<>();
            map.add(row);



            for(char c : nextLine.toCharArray()){
                if(c=='@'){
                    row.add('@');
                    robotPos = new int[]{map.size()-1,row.size()-1};
                    //System.out.println(robotPos[0]+" "+robotPos[1]);
                    row.add('.');
                }
                else if(c=='O'){
                    row.add('[');
                    row.add(']');
                }
                else{
                    row.add(c);
                    row.add(c);
                }
            }

            //Read Logic here

        }





        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            for(char c : nextLine.toCharArray()){
                movements.add(c);
            }

            //Read Logic here

        }




        for(char d : movements){
            switch (d){
                case '^':
                    move(map, new int[]{-1,0});
                    break;
                case '>':
                    move(map, new int[]{0,1});
                    break;
                case 'v':
                    move(map, new int[]{1,0});
                    break;
                case '<':
                    move(map, new int[]{0,-1});
                    break;
            }

//            System.out.println(d);
//            for(ArrayList<Character> row : map){
//                for(char c : row){
//                    System.out.print(c);
//                }
//                System.out.println();
//            }
        }

        int sum = 0;
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(0).size(); j++){
                if(map.get(i).get(j) == '['){
                    sum+=i*100+j;
                }
            }
        }


        System.out.println(sum);
    }

    public static void move(ArrayList<ArrayList<Character>> map, int[] vector) throws Exception {
        if(map.get(robotPos[0]+vector[0]).get(robotPos[1]+vector[1]) == '#'){
            return;
        }
        else if(map.get(robotPos[0]+vector[0]).get(robotPos[1]+vector[1]) == '.'){
            swap(map,robotPos[0],robotPos[1],robotPos[0]+vector[0],robotPos[1]+vector[1]);
            robotPos[0]+=vector[0];
            robotPos[1]+=vector[1];
        }
        else{
            if(canPush(map,robotPos[0],robotPos[1],vector)){
                push(map,robotPos[0],robotPos[1],vector);
                robotPos[0]+=vector[0];
                robotPos[1]+=vector[1];
            }
        }
    }

    public static boolean canPush(ArrayList<ArrayList<Character>> map, int posi, int posj, int[] vector) throws Exception{
        char frontThing = map.get(posi+vector[0]).get(posj+vector[1]);

        boolean canPush;
        if(frontThing == '[' || frontThing == ']'){
            if(vector[0]==0){//Side to side
                canPush = canPush(map,posi+vector[0],posj+vector[1],vector);
            }
            else{//hell
                boolean leftPushed;
                boolean rightPushed;
                if(frontThing == '['){
                    leftPushed = canPush(map,posi+vector[0],posj+vector[1],vector);
                    rightPushed = canPush(map,posi+vector[0],posj+vector[1]+1,vector);
                }
                else {
                    leftPushed = canPush(map,posi+vector[0],posj+vector[1]-1,vector);
                    rightPushed = canPush(map,posi+vector[0],posj+vector[1],vector);
                }
                canPush = leftPushed && rightPushed;
            }

        }
        else if(frontThing == '#'){
            canPush = false;
        }
        else if(frontThing == '.'){
            canPush = true;
        }
        else{
            throw new Exception("Huh?");
        }

        return canPush;
    }

    public static boolean push(ArrayList<ArrayList<Character>> map, int posi, int posj, int[] vector) throws Exception {
        char frontThing = map.get(posi+vector[0]).get(posj+vector[1]);

        boolean canPush;
        if(frontThing == '[' || frontThing == ']'){
            if(vector[0]==0){//Side to side
                canPush = push(map,posi+vector[0],posj+vector[1],vector);
            }
            else{//hell
                boolean leftPushed;
                boolean rightPushed;
                if(frontThing == '['){
                    leftPushed = push(map,posi+vector[0],posj+vector[1],vector);
                    rightPushed = push(map,posi+vector[0],posj+vector[1]+1,vector);
                }
                else {
                    leftPushed = push(map,posi+vector[0],posj+vector[1]-1,vector);
                    rightPushed = push(map,posi+vector[0],posj+vector[1],vector);
                }
                canPush = leftPushed && rightPushed;
            }

        }
        else if(frontThing == '#'){
            canPush = false;
        }
        else if(frontThing == '.'){
            canPush = true;
        }
        else{
            throw new Exception("Huh?");
        }

        if(canPush){
            swap(map,posi,posj,posi+vector[0],posj+vector[1]);
        }

        return canPush;
    }

    public static void swap(ArrayList<ArrayList<Character>> map, int a, int b, int x, int y){
        char temp = map.get(a).get(b);
        map.get(a).set(b,map.get(x).get(y));
        map.get(x).set(y,temp);
    }
}

