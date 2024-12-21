import java.util.function.Consumer;
public class Hell{

    public static void main(String[] args) throws Exception{
        System.out.println(first(1.0,2.0,3.0));
    }
    public static double first(double temp1, double temp2, double temp3) throws Exception{
        double temp4 = second(temp1, second(temp2, temp3));
        return second(2.0, second(temp4/temp2/temp3, temp4/temp1/temp3))+second(2.0, second(temp4/temp2/temp3, temp4/temp1/temp2))+second(2.0, second(temp4/temp1/temp3, temp4/temp1/temp2));

    }
    public static double second(double temp1, double temp2) throws Exception{
        double var = 0;
        for(; temp2 >= 1; var+=temp1+0*(temp2---temp2)){}
        return third(var, temp2*temp1);
    }

    public static double third(double temp1, double temp2) throws Exception{
        final double[] temp3 = {temp1, temp2};
        final Consumer<Consumer>[] temp4 = new Consumer[1];
        temp4[0] = c -> {if(temp3[1]>=1){temp3[1]--; temp3[0]++; temp4[0].accept(c);}};
        temp4[0].accept(temp4[0]);
        return temp3[0]+temp3[1];
    }
}