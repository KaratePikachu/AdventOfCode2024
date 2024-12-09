import java.util.*;
import java.io.*;

class Pair{
    public String type;
    public int size;

//    public Pair(String type, int size){
//        this.type=type;
//        this.size=size;
//    }
}

public class Code{
    static String fileName = "input.txt";
    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        ArrayList<String> data = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        char[] chars = myReader.nextLine().toCharArray();
        for(int i=0; i<chars.length; i++ ){
            int size = Integer.parseInt(String.valueOf(chars[i]));
            for(int j=0; j<size; j++){
                if (i%2==0) {
                    data.add(""+(i/2));
                }
                else{
                    data.add(".");
                }
            }
            //System.out.println(i);
        }



        while(true){
            int lower = 0;
            int upper = data.size()-1;
            while(upper>=lower && data.get(upper).equals(".")){
                upper--;
            }

            while(lower<=upper && !data.get(lower).equals(".")){
                lower++;
            }

            if(upper<lower){
                break;
            }


            data.set(lower,data.remove(upper));
        }

        long sum = 0;
        for(int i=0; i<data.size();i++){
            if(data.get(i).equals(".")){
                break;
            }

            sum+=Long.parseLong(data.get(i))*i;

        }


        printThing(data);
        System.out.println(sum);

        //System.out.println(result);

        
    }

    public static void printThing(ArrayList<String> thing){
            for(String c : thing){
                System.out.print(c+" ");
            }
            System.out.println();
    }
}
