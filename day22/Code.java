import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Code{
    public static String fileName = "input.txt";
    public static long PRUNE = 16777216L;
    //orig = prune(orig*64 mix orig)
    //secret/32
    //
    //
    static ArrayList<Long> secrets = new ArrayList<>();
    static TreeMap<long[],Long> overallPatterns = new TreeMap<>(new CustomComparator());
    static TreeMap<long[],Long> currCustomerPatterns;

    static class CustomComparator implements Comparator<long[]> {
        @Override
        public int compare(long[] o1, long[] o2) {
            for(int i=0; i<4; i++){
                if(o1[i]<o2[i]){
                    return -1;
                }
                else if(o1[i]>o2[i]){
                    return 1;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception{
        //Data Structure
        LinkedList<Long> currPattern;


        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            secrets.add(Long.parseLong(nextLine));

            //Read Logic here

        }



        for(long l : secrets){
            currCustomerPatterns = new TreeMap<>(new CustomComparator());
            currPattern = new LinkedList<>();
            long secret = l;
            long currentValue = secret%10;
            //System.out.println(secret);
            for(int i=0; i<2000; i++){


                secret = nextSecret(secret);
                long nextValue = secret%10;
                long valDiff = nextValue-currentValue;
                currPattern.add(valDiff);
                if(currPattern.size() == 4){
                    long[] newPattern = toPattern(currPattern);

                    if(!currCustomerPatterns.containsKey(newPattern)){
                        currCustomerPatterns.put(newPattern,nextValue);
                    }

                    currPattern.removeFirst();
                }

                //Used for the diff calculation
                currentValue = nextValue;
            }
            for(long[] key : currCustomerPatterns.keySet()){
                if(!overallPatterns.containsKey(key)){
                    overallPatterns.put(key,0L);
                }
                overallPatterns.put(key,overallPatterns.get(key)+currCustomerPatterns.get(key));
            }

        }

        long maxBananas = 0;
        for(long[] key : overallPatterns.keySet()){

            long newVal = overallPatterns.get(key);
            if(newVal > maxBananas){
                //System.out.println(key[0]+" "+key[1]+" "+key[2]+" "+key[3]+"->"+overallPatterns.get(key));
                maxBananas = newVal;
            }
            //maxBananas = Math.max(maxBananas,newVal);
        }



        System.out.println(maxBananas);
    }

    private static long[] toPattern(LinkedList<Long> currPattern) {
        return new long[]{currPattern.get(0), currPattern.get(1), currPattern.get(2), currPattern.get(3)};
    }

    public static long nextSecret(long secret){
        //Step 1
        long result = secret*64;
        secret ^= result;
        secret %= PRUNE;

        //Step 2
        result = secret/32;
        secret ^=result;
        secret %= PRUNE;

        //Step 3
        result = secret*2048;
        secret ^= result;
        secret %= PRUNE;

        return secret;
    }
}

