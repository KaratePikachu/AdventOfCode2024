import java.util.*;
import java.io.*;

public class Day4 {

    public static char[][][] ans = {
        {
            {'M',' ','M'},
            {' ','A',' '},
            {'S',' ','S'}
        },
        {
            {'M',' ','S'},
            {' ','A',' '},
            {'M',' ','S'}
        },
        {
            {'S',' ','S'},
            {' ','A',' '},
            {'M',' ','M'}
        },
        {
            {'S',' ','M'},
            {' ','A',' '},
            {'S',' ','M'}
        }
    };
    public static ArrayList<String> getLines() throws Exception{
        ArrayList<String> lines = new ArrayList<>();
        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){
        String nextLine = myReader.nextLine();
            //if(nextLine.trim().isEmpty()){
            //    continue;
            //}
            lines.add(nextLine);
        }

        return lines;
    }

    public static void main(String[] args) throws Exception{
        //part1();
        part2();
    }

    // public static void part1() throws Exception{
    //     ArrayList<String> lines = getLines();
    //     char[][] grid = new char[lines.size()][lines.get(0).length()];

    //     for(int i=0; i<lines.size(); i++){
    //         grid[i] = lines.get(i).toCharArray();
    //     }
    //     int numCorrect = 0;
    //     for(int i=0; i<grid.length; i++){
    //         for(int j=0; j<grid[0].length; j++){
    //             for(int x = -1; x<2; x++){
    //                 for(int y = -1; y<2; y++){
    //                     try{
    //                         if(x==0 && y==0){
    //                             continue;
    //                         }
    
    //                         for(int c = 0; c<ans.length; c++){
    //                             if(grid[i+x*c][j+y*c] != ans[c]){
    //                                 break;
    //                             }
    //                             if(c == ans.length-1){
    //                                 numCorrect++;
    //                             }
    //                         }
    //                     }
    //                     catch(IndexOutOfBoundsException e){

    //                     }

    //                 }
    //             }
    //         }
    //     }
    //     System.out.println(numCorrect);

    // }

    public static void part2() throws Exception{
        ArrayList<String> lines = getLines();
        char[][] grid = new char[lines.size()][lines.get(0).length()];

        for(int i=0; i<lines.size(); i++){
            grid[i] = lines.get(i).toCharArray();
        }
        int numCorrect = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(testPerm(grid, i, j)){
                    numCorrect++;
                }
            }
        }
        System.out.println(numCorrect);

    }

    private static boolean testPerm(char[][] grid, int i, int j){
        for(int perm = 0; perm<4; perm++){
            try{
                for(int x = 0; x<3; x++){
                    for(int y = 0; y<3; y++){
                        if(ans[perm][x][y] == ' '){
                            continue;
                        }
                        if(grid[i+x][j+y] != ans[perm][x][y]){
                            throw new IndexOutOfBoundsException();
                        }
                        if(x == 2 && y==2){
                            // for(int b=0; b<3; b++){
                            //     for(int c=0; c<3; c++){
                            //         if(grid[i+b][j+c] != ans[perm][b][c]){
                            //             System.out.println(grid[i+b][i+c]+" is not "+ ans[perm][b][c]);
                            //         }
                                    
                                    
                            //     }
                            // }
                            return true;
                        }
                    }
                }
            }
            catch(IndexOutOfBoundsException e){

            }
        }
        return false;
    }
}
