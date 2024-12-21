import java.util.function.Consumer;
public class Hell{
   static Method temp1, temp2, temp3;

   static interface Method{
      double consume(Double... in) throws Exception;
   }
   public static void main(String[] W_W) throws Exception{
      temp1 = temp -> (double)Hell.class.getDeclaredMethod(new String(new byte[]{109,121,115,116}), Double.TYPE, Double.TYPE, Double.TYPE).invoke(null, temp[0], temp[1], temp[2]);
      temp2 = temp -> (double)Hell.class.getDeclaredMethod(new String(new byte[]{109,121,115,116,49}), Double.TYPE, Double.TYPE).invoke(null, temp[0], temp[1]);
      temp3 = temp -> (double)Hell.class.getDeclaredMethod(new String(new byte[]{109,121,115,116,50}), Double.TYPE, Double.TYPE).invoke(null, temp[0], temp[1]);
      System.out.println(temp1.consume(1.0, 2.0, 3.0));
   }
   public static double myst(double temp1, double temp2, double temp3) throws Exception{
      double temp4 = Hell.temp2.consume(temp1, Hell.temp2.consume(temp2, temp3));
      return Hell.temp2.consume(2.0, Hell.temp2.consume(temp4/temp2/temp3, temp4/temp1/temp3))+Hell.temp2.consume(2.0, Hell.temp2.consume(temp4/temp2/temp3, temp4/temp1/temp2))+Hell.temp2.consume(2.0, Hell.temp2.consume(temp4/temp1/temp3, temp4/temp1/temp2));
   
   }
   public static double myst1(double temp1, double temp2) throws Exception{
      double temp3 = 0;
      for(; temp2 >= 1; temp3+=temp1+0*(temp2---temp2)){}
      return Hell.temp3.consume(temp3, temp2*temp1);
   }
   public static double myst2(double temp1, double temp2) throws Exception{
      final double[] temp3 = {temp1, temp2};
      final Consumer<Consumer>[] temp4 = new Consumer[1];
      temp4[0] = c -> {if(temp3[1]>=1){temp3[1]--; temp3[0]++; temp4[0].accept(c);}};
      temp4[0].accept(temp4[0]);
      return temp3[0]+temp3[1];
   }
}