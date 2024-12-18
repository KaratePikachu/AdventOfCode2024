public class Test {
    public static void main(String[] args) {
        for(long i=0; i<Long.MAX_VALUE; i++){
            if(i%1000000000==0){
                System.out.println(i);
            }
        }
    }
}
