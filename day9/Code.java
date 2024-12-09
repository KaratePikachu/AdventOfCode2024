import java.util.*;
import java.io.*;

class Pair{
    public String type;
    public int size;

    public Pair(String type, int size){
        this.type=type;
        this.size=size;
    }
}

public class Code{
    static String fileName = "input.txt";
    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        ArrayList<Pair> data = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        char[] chars = myReader.nextLine().toCharArray();
        for(int i=0; i<chars.length; i++ ){
            int size = Integer.parseInt(String.valueOf(chars[i]));
            if (i%2==0) {
                data.add(new Pair(""+(i/2),size));
            }
            else{
                data.add(new Pair(".",size));
            }
            //System.out.println(i);
        }

        //int lastMovedID=Integer.MAX_VALUE;
        for(int i=data.size()-1; i>=0; i--){
            if(data.get(i).type.equals(".")){
                continue;
            }
//            if(lastMovedID<Integer.parseInt(data.get(i).type)){
//                continue;
//            }

            Pair mover = data.get(i);
            //lastMovedID = Integer.parseInt(mover.type);

            for(int j=0; j<i; j++){
                if(!data.get(j).type.equals(".")){
                    continue;
                }




                if(data.get(j).size == mover.size){

                    data.get(j).type = mover.type;
                    mover.type = ".";
                    break;
                }
                else if(data.get(j).size > mover.size){

                    data.get(j).size = data.get(j).size-mover.size;
                    data.add(j,new Pair(mover.type,mover.size));
                    mover.type = ".";
                    break;
                }
            }
            for(int j=0; j<data.size()-1;j++){
                if(data.get(j).type.equals(".") && data.get(j+1).type.equals(".")){
                    data.get(j).size+=data.get(j+1).size;
                    data.remove(j+1);
                }
            }
        }

        ArrayList<String> result = getThing(data);

        //printThing(data);

        long sum = 0;
        for(int i=0; i<result.size();i++){
            //System.out.print(result.get(i));
            if(result.get(i).equals(".")){
                continue;
            }

            sum+=Long.parseLong(result.get(i))*i;

        }

        System.out.println(sum);

        //printThing(data);

        //System.out.println(sum);

        //System.out.println(result);

        
    }

    public static void printThing(ArrayList<Pair> thing){
            for(Pair c : thing){
                for(int i=0; i<c.size; i++){
                    System.out.print(c.type+" ");
                }
            }
            System.out.println();
    }

    public static ArrayList<String> getThing(ArrayList<Pair> thing){
        ArrayList<String> result = new ArrayList<>();
        for(Pair c : thing){
            for(int i=0; i<c.size; i++){
                result.add(c.type);
            }
        }
        return result;
    }
}
