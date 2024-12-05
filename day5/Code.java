import java.util.*;
import java.io.*;

// class Link{
//     public int depends;
//     public boolean seen;

//     public Link(int d){
//         depends = d;
//         seen = false;
//     }
// }
public class Code{

    
    // public static ArrayList<String> getLines() throws Exception{
    //     ArrayList<String> lines = new ArrayList<>();
    //     Scanner myReader = new Scanner(new File("input.txt"));
    //     while(myReader.hasNextLine()){
    //     String nextLine = myReader.nextLine();
    //         if(nextLine.trim().isEmpty()){
    //            break;
    //         }
    //         lines.add(nextLine);
    //     }

    //     return lines;
    // }

    public static void main(String[] args) throws Exception{
        //part1();
        part2();
    }

    public static void part1() throws Exception{

        HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<>();
        //HashSet<Integer> seen = new HashSet<>();

        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            if(nextLine.trim().isEmpty()){
               break;
            }
            String[] items = nextLine.split("\\|");

            int num0 = Integer.parseInt(items[0]);
            int num1 = Integer.parseInt(items[1]);

            if(!hashMap.containsKey(num0)){
                hashMap.put(num0,new ArrayList<>());
            }

            hashMap.get(num0).add(num1);
            
        }

        int sum = 0;
        while(myReader.hasNextLine()){
            boolean invalid = false;

            String nextLine = myReader.nextLine();
            ArrayList<Integer> print = new ArrayList<>();

            String[] items = nextLine.split(",");


            for(String item : items){
                

                int number = Integer.parseInt(item);
                //seen.add(number);
                if(hashMap.containsKey(number)){

                    for(int dependency : hashMap.get(number)){
                        if(print.contains(dependency)){
                            System.out.println("Unable, "+dependency+" not before "+number);
                            invalid = true;
                        }
                    }

                    if(invalid){
                        continue;
                    }
                    
                }

                print.add(number);
            }

            if(invalid){
                continue;
            }


            // System.out.println("Test:");
            // for(int i : print){
            //     System.out.print(i+" ");
            // }
            // System.out.println();

            sum+= print.get(print.size()/2);
            print = new ArrayList<>();
        }

        

        System.out.println(sum);

        
    }

    public static void part2() throws Exception{

        HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<>();
        //HashSet<Integer> seen = new HashSet<>();

        Scanner myReader = new Scanner(new File("input.txt"));
        while(myReader.hasNextLine()){

            String nextLine = myReader.nextLine();
            if(nextLine.trim().isEmpty()){
               break;
            }
            String[] items = nextLine.split("\\|");

            int num0 = Integer.parseInt(items[0]);
            int num1 = Integer.parseInt(items[1]);

            if(!hashMap.containsKey(num0)){
                hashMap.put(num0,new ArrayList<>());
            }

            hashMap.get(num0).add(num1);
            
        }

        int sum = 0;
        while(myReader.hasNextLine()){
            boolean invalid = false;

            String nextLine = myReader.nextLine();
            ArrayList<Integer> print = new ArrayList<>();

            String[] items = nextLine.split(",");


            for(String item : items){
                ArrayList<Integer> invalids = new ArrayList<>();
                

                int number = Integer.parseInt(item);
                //seen.add(number);

                int insertIndex = print.size();

                if(hashMap.containsKey(number)){
                    
                    for(Integer dependency : hashMap.get(number)){
                        
                        if(print.contains(dependency)){
                            
                            //System.out.println("Unable, "+dependency+" not before "+number);
                            insertIndex = Math.min(insertIndex,print.indexOf(dependency));
                            
                            //invalids.add(dependency);
                            
                            invalid = true;
                        }
                        
                    }
                    if(number == 47){
                        System.out.println(print);
                        System.out.println(invalids);
                    }
                    
                }

                print.add(insertIndex,number);
                //print.addAll(invalids);
                //System.out.println(print);
                // System.out.println("is now "+print);
            }

            if(!invalid){
                continue;
            }


            System.out.println("Test:");
            for(int i : print){
                System.out.print(i+" ");
            }
            System.out.println();

            sum+= print.get(print.size()/2);
            print = new ArrayList<>();
        }

        

        System.out.println(sum);

        
    }
}
