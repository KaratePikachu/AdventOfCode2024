import java.util.*;
import java.io.*;

import static java.util.Collections.swap;

class Equation{

    String val1;
    String val2;
    String operation;
    String result;

    public Equation(String val1, String val2, String operation, String result){
        this.val1=val1;
        this.val2=val2;
        this.operation=operation;
        this.result=result;
    }

    @Override
    public String toString(){
        return val1+" "+operation+" "+val2+" -> "+result;
    }
}

public class Code{
    public static String fileName = "InputFixed.txt";
    static TreeMap<String,Integer> bools = new TreeMap<>();
    static ArrayList<Equation> equations = new ArrayList<>();
    static ArrayList<Equation> finishedEquation = new ArrayList<>();

    static String xVal = "101100110111101110000001100000100011110011111";
    static String yVal = "100110000001010110001010100111101100111000111";

    static String expectedResult =
                       "01010010111001000100001100001000010000101100110";
    ///                  1110111011000110111010110100111000011011100110

    public static void main(String[] args) throws Exception{
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            if(nextLine.isEmpty()){
                break;
            }
            String[] elements = nextLine.split(": ");
            bools.put(elements[0], Integer.valueOf(elements[1]));

            //Read Logic here

        }

        while(myReader.hasNextLine()){
            String nextLine = myReader.nextLine();
            String[] elements = nextLine.split(" ");
            equations.add(new Equation(elements[0],elements[2],elements[1],elements[4]));
        }


        for(Equation e : equations){
            ArrayList<Equation> parents = getParents(e);
            ArrayList<Equation> children = getChildren(e);
            //All XORS should either be inputs fed into a generic register, or generic registers fed into an output
            if(isInput(e)){
                if(e.result.indexOf('z') == 0 && !e.result.equals("z00")){
                    logError(e);
                }


//                int numXORS = 0;
//                int numANDS = 0;
////                if(children.size()==2){
////                    for(Equation child : children){
////                        if(child.operation.equals("XOR")){ numXORS++;}
////                        else if(child.operation.equals("AND")){ numANDS++;}
////
////
////
////                    }
////                    if(numXORS !=1 || numANDS !=1){
////                        System.out.println("MISSING one");
////                    }
////                }
////                else if(children.size()==1){
////                    if(!children.get(0).operation.equals("OR")){
////                        logError(children.get(0));
////                    }
////                }


            }
            else{
                if(e.operation.equals("XOR")){
                    if(e.result.indexOf('z') != 0){
                        logError(e);
                    }
                }
                else if(e.operation.equals("OR")){
                    int in = getInputEquation(parents);
                    int out = getOutputEquation(children);

                    if(e.result.indexOf('z')==0){
                        logError(e,"Unexpected Z");
                    }

                    Equation inputParent = null;
                    Equation outputChild = null;
                    for(Equation p : parents){
                        if(isInput(p)){
                            inputParent = p;
                        }
                    }
                    if(inputParent == null){
                        logError(e,"Parents are not input");
                    }


                    for(Equation c : children){
                        if(isOutput(c)){
                            outputChild = c;
                        }
                    }
//                    if(outputChild != null){
//                        System.out.println(" output children?");
//                        logError(e);
//                    }
                }
                //System.exit(0);
            }


        }

//        while(!equations.isEmpty()){
//            for(int i=equations.size()-1; i>=0; i--){
//                Equation e = equations.get(i);
//                if(!bools.containsKey(e.val1) || !bools.containsKey(e.val2)){
//                    continue;
//                }
//
//                int result = evaluate(e);
////                if(e.result.indexOf('z') == 0){
////                    String strResult = ""+result;
////                    int index = Integer.parseInt(e.result.substring(1));
////                    String expectedBit = ""+expectedResult.charAt(expectedResult.length()-1-index);
////                    if(!expectedBit.equals(strResult)){
////                        stackTrace(e);
////                    }
////                }
//                bools.put(e.result,result);
//                finishedEquation.add(equations.remove(i));
//            }
//        }
//
//        String result = "";
//        for(String s : bools.keySet()){
//            if(s.indexOf('z') != 0){
//                continue;
//            }
//            System.out.println(s+": "+bools.get(s));
//            result = bools.get(s)+result;
//        }
//
//
//
//        System.out.println(result);
    }

//    private static void getInputEquation(ArrayList<Equation> parents) {
//        Equation ans = null;
//        for(Equation e : parents){
//
//        }
//    }

    private static boolean isInput(Equation e) {
        return e.val1.indexOf('x') == 0 || e.val1.indexOf('y') == 0;
    }

    private static boolean isOutput(Equation e) {
        return e.result.indexOf('z') == 0;
    }

    private static void logError(Equation e){
        System.out.println("Found error: "+ e);
    }

    private static void logError(Equation e,String msg){
        System.out.println(msg+": "+ e);
    }

    private static ArrayList<Equation> getParents(Equation e) {
        ArrayList<Equation> parents = new ArrayList<>();
        for(Equation other : equations){
            if(other.result.equals(e.val1) || other.result.equals(e.val2)){
                parents.add(other);
            }
        }
        return parents;
    }

    private static ArrayList<Equation> getChildren(Equation e) {
        ArrayList<Equation> children = new ArrayList<>();
        for(Equation other : equations){
            if(e.result.equals(other.val1) || e.result.equals(other.val2)){
                children.add(other);
            }
        }
        return children;
    }


    private static void stackTrace(Equation e){
        System.out.println(e);
        for(Equation parent : finishedEquation){
            if(parent.result.equals(e.val1) || parent.result.equals(e.val2)){
                stackTrace(parent);
            }
        }
    }

    private static int evaluate(Equation e){
        return switch(e.operation){
            case "XOR" -> bools.get(e.val1) ^ bools.get(e.val2);
            case "OR" -> bools.get(e.val1) | bools.get(e.val2);
            case "AND" -> bools.get(e.val1) & bools.get(e.val2);
            default -> throw new IllegalStateException("Unexpected value: " + e.operation);
        };
    }
}

