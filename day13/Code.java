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
    public static long sum = 0;
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
            sol[0]+=10000000000000L;
            sol[1]+=10000000000000L;

            long numerator = sol[0]*eq2[1]-sol[1]*eq2[0];

            long demoninator = eq1[0]*eq2[1]-eq1[1]*eq2[0];

            if(numerator%demoninator == 0){
                long a = numerator/demoninator;
                long b = (sol[1]-a*eq1[1])/eq2[1];

                sum+=3*a+b;
                if((a*eq1[0]+b*eq2[0] != sol[0]) || (a*eq1[1]+b*eq2[1]!=sol[1])){
                    System.out.println(eq1[0]+" "+eq1[1]);
                    System.out.println("!!!");
                    System.out.println(a*eq1[0]+b*eq2[0]+" != "+sol[0]);
                    System.out.println((a*eq1[1]+b*eq2[1]+" != "+sol[1]));
                    sum-=3*a+b;
                }

            }


            myReader.nextLine();
        }
        //System.out.println(result);

        System.out.println(sum);
    }
}