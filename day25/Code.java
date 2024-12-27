import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "input.txt";
    static TreeSet<int[]> locks = new TreeSet<>(new CustomComparator());
    static TreeSet<int[]> keys = new TreeSet<>(new CustomComparator());

    final static boolean ISKEY = false;
    final static boolean ISLOCK = true;

    static class CustomComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if(o1.length<o2.length){
                return -1;
            }
            else if(o1.length>o2.length){
                return 1;
            }
            for(int i=0; i<o1.length; i++){
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

        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){


            char[][] grid = new char[7][];

            for(int i=0; i<7; i++){
                grid[i] = myReader.nextLine().toCharArray();
            }
            myReader.nextLine();

            //Read Logic here
            boolean type = grid[0][0] == '#' ? ISLOCK : ISKEY;
            char expected = grid[0][0];

            int[] nums = new int[5];
            for(int i=0; i<grid.length; i++){
                for(int j=0; j<grid[i].length; j++){
                    if(grid[i][j] == expected){
                        nums[j]++;
                    }
                }
            }

            if(type == ISLOCK){
                locks.add(nums);
            }
            else{
                keys.add(nums);
            }
        }


        for(int[] r : keys){
            for(int i : r){
                System.out.print(i+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int[] r : locks){
            for(int i : r){
                System.out.print(i+" ");
            }
            System.out.println();
        }

        int result = 0;
        for(int[] key : keys){
            for(int[] lock : locks){
                boolean validPair = true;
                for(int i=0; i<key.length; i++){
                    if(key[i] < lock[i]){
                        validPair = false;
                    }
                }
                if(validPair){
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}

