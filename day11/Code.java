import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Code{

    public static void main(String[] args) throws Exception{
        part1();
        //part2();
    }

    public static void part1() throws Exception{
        HashMap<Long,Long> stones = new HashMap<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        for(String stone : myReader.nextLine().split(" ")){
            addStone(stones,Long.parseLong(stone),1L);

        }


        for(int i=0; i<75; i++){
            stones = blink(stones);
        }


        long sum = 0;

        for(Long num : stones.keySet()){
            sum+= stones.get(num);
        }
        System.out.println(sum);




        

        //System.out.println(result);

        
    }

    public static void addStone(HashMap<Long,Long> stones, long num, long amount){
        if(!stones.containsKey(num)){
            stones.put(num, 0L);
        }
        stones.put(num,stones.get(num)+amount);
    }

    public static HashMap<Long,Long> blink(HashMap<Long,Long> stones){
        HashMap<Long,Long> newStones = new HashMap<>();

        for(Long num : stones.keySet()){
            if(num == 0L){
                addStone(newStones,1L,stones.get(num));
            }
            else if((""+num).length()%2==1){
                addStone(newStones, num*2024L, stones.get(num));
            }
            else{
                String numStr = ""+num;
                int mid = numStr.length()/2;
                long leftStone = Long.parseLong(numStr.substring(0,mid));
                long rightStone = Long.parseLong(numStr.substring(mid));
                addStone(newStones,leftStone,stones.get(num));
                addStone(newStones,rightStone,stones.get(num));
            }
        }
        return newStones;
    }
}
