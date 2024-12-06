import java.util.*;
import java.io.*;

public class Day3{
    public static void main(String[] args) throws Exception{
        //part1();
        part2();
    }

    // public static void part1() throws Exception{
    //     ArrayList<String> lines = FileInput.getLines();
    //     int sum = 0;
    //     boolean doOp = true;
    //     for(String test : lines){
    //         String line = test;
    //         while(line.indexOf("mul(")!=-1){
    //             try{
    //                 // int multIndex = line.indexOf("mul(");
    //                 // int doIndex = line.indexOf("do()");
    //                 // int dontIndex = line.indexOf("don't()");
    //                 // if(doIndex<dontIndex && doIndex<multIndex){
    //                 //     doOp = true;
    //                 //     line = line.substring(line.indexOf("do()")+4);
    //                 // }
    //                 // else if(dontIndex<doIndex && dontIndex<multIndex){
    //                 //     doOp = false;
    //                 //     line = line.substring(line.indexOf("don't()")+7);
    //                 // }

    //                 // if(!doOp){
    //                 //     continue;
    //                 // }  
    //                 int dontIndex = line.indexOf("don't()");
    //                 int multIndex = line.indexOf("mul(");

    //                 line = line.substring(Math.min(dontIndex,multIndex));

    //                 if(line.indexOf("don't()" == 0)){
    //                     line = line.substring(line.indexOf("do()")+4);
    //                     continue;
    //                 }



    //                 line = line.substring(4);
                    
    //                 String[] nums = line.substring(0,line.indexOf(")")).split(",");
    //                 if(nums.length!=2){
    //                     continue;
    //                 }

    //                 if(!isNum(nums[0])){
    //                     continue;
    //                 }
    //                 if(!isNum(nums[1])){
    //                     continue;
    //                 }

    //                 line = line.substring(line.indexOf(")")+1);

    //                 sum += Integer.parseInt(num1)*Integer.parseInt(num2);
                    
    //             }
    //             catch(IndexOutOfBoundsException e){
    //                 break;
    //             }
    //         }
    //     }
    //     System.out.println(sum);
    // }

    private static boolean isNum(String num){
        for(char c : num.toCharArray()){
            if(c<'0' || c>'9'){
                return false;
            }
        }
        return true;
    }

    public static void part2() throws Exception{
        ArrayList<String> lines = FileInput.getLines();
        int sum = 0;
        boolean doOp = true;
        for(String test : lines){
            String line = test;
            while(line.indexOf("mul(") != -1){
                int mulIndex = line.indexOf("mul(");
                int dontIndex = line.indexOf("don't()");

                if(dontIndex!=-1 && dontIndex < mulIndex){
                    line = line.substring(dontIndex+7);

                    int doIndex = line.indexOf("do()");
                    if(doIndex == -1){
                        break;
                    }

                    line = line.substring(doIndex+4);
                    continue;
                }

                line = line.substring(mulIndex);
                
                String num1 = null;
                String num2 = null;
                try{
                    num1 = line.substring(line.indexOf("(")+1,line.indexOf(","));
                    num2 = line.substring(line.indexOf(",")+1,line.indexOf(")"));
                }
                catch(IndexOutOfBoundsException e){
                    line = line.substring(1);
                    continue;
                }

                if(!isNum(num1)){
                    line = line.substring(1);
                    continue;
                }
                if(!isNum(num2)){
                    line = line.substring(1);
                    continue;
                }

                sum+=Integer.parseInt(num1)*Integer.parseInt(num2);
                line = line.substring(line.indexOf(")"));

            }
        }
        System.out.println(sum);
    }
}