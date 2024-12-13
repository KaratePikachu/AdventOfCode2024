import java.util.*;
import java.io.*;

class Equation{
    long[] buttonA;
    long[] buttonB;
    long[] prize;

    public Equation(long[] a, long[] b, long[] p){
        buttonA=a;
        buttonB=b;
        prize=p;
    }
}
public class Code{
    public static String fileName = "input.txt";

    public static void main(String[] args) throws Exception{
        //Data Structure
        ArrayList<Equation> equations = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){

            String lineA = myReader.nextLine();
            String lineB = myReader.nextLine();
            String lineSol = myReader.nextLine();

            long[] eq1 = new long[]{Long.parseLong(lineA.substring(lineA.indexOf("X+")+2,lineA.indexOf(","))),
                    Long.parseLong(lineA.substring(lineA.indexOf("Y+")+2))};
            long[] eq2 = new long[]{Long.parseLong(lineB.substring(lineB.indexOf("X+")+2,lineB.indexOf(","))),
                    Long.parseLong(lineB.substring(lineB.indexOf("Y+")+2))};
            long[] sol = new long[]{Long.parseLong(lineSol.substring(lineSol.indexOf("X=")+2,lineSol.indexOf(","))),
                    Long.parseLong(lineSol.substring(lineSol.indexOf("Y=")+2))};
            Equation eq = new Equation(eq1,eq2,sol);
            eq.prize[0]+=10000000000000L;
            eq.prize[1]+=10000000000000L;

            long numerator =
            unprocessed.buttonA[0]-
            equations.add();

            myReader.nextLine();
        }
        //System.out.println(result);

        //long sum = 0;

//        for(Equation e : equations){
//            sum+=smallestCost(e);
//        }
//        System.out.println(sum);
    }

    public static long smallestCost(Equation equation){
        long smallestCost = -1;
        for(int x1=0; x1<101; x1++){
            for(int x2=0; x2<101; x2++){
                long combx = equation.buttonA[0] *x1+ equation.buttonB[0] *x2;
                long comby = equation.buttonA[1] *x1+ equation.buttonB[1] *x2;

                if(combx == equation.prize[0] && comby == equation.prize[1]){
                    int tokens = x1*3+x2;
                    if(smallestCost==-1){
                        smallestCost = tokens;
                    }
                    else if(tokens<smallestCost){
                        smallestCost = tokens;
                    }
                }
            }
        }

        if(smallestCost==-1){
            smallestCost = 0;
        }
        return smallestCost;
    }
}