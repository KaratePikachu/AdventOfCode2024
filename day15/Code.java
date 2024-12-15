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
                    row.add(c);
                    robotPos = new int[]{map.size()-1,row.size()-1};
                    //System.out.println(robotPos[0]+" "+robotPos[1]);
                    row.add('.');
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


//        for(ArrayList<Character> row : map){
//            for(char c : row){S
//                System.out.print(c);
//            }
//            System.out.println();
//        }

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

            System.out.println(d);
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
                if(map.get(i).get(j) == 'O'){
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
            if(push(map,robotPos[0],robotPos[1],vector)){
                robotPos[0]+=vector[0];
                robotPos[1]+=vector[1];
            }
        }
    }

    public static boolean push(ArrayList<ArrayList<Character>> map, int posi, int posj, int[] vector) throws Exception {
        boolean canPush;
        if(map.get(posi+vector[0]).get(posj+vector[1]) == 'O'){
            canPush = push(map,posi+vector[0],posj+vector[1],vector);
        }
        else if(map.get(posi+vector[0]).get(posj+vector[1]) == '#'){
            canPush = false;
        }
        else if(map.get(posi+vector[0]).get(posj+vector[1]) == '.'){
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

