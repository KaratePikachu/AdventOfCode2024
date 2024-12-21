public class Heaven{
    public static void main(String[] args) throws Exception{
        System.out.println(fn(5.0,8.0,2.0));
    }
    public static double fn(double in1, double in2, double in3) throws Exception{
        return 2.0*(in1*in2+in1*in3+in2*in3);
    }
}