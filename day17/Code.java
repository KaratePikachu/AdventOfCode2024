import java.rmi.UnexpectedException;
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "test.txt";
    public static ArrayList<Integer> instructions = new ArrayList<>();


    static int instructionPointer = 0;
    static long registerA;
    static long registerB;
    static long registerC;

    static long foundAreg = 20534862392791L;

    public static void main(String[] args) throws Exception {
        //Data Structure

        Scanner myReader = new Scanner(new File(fileName));
        //registerA = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        //registerB = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        //registerC = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        myReader.nextLine();
        myReader.nextLine();
        myReader.nextLine();
        myReader.nextLine();

        String[] nums = myReader.nextLine().split("[ ,]");
        for (int i = 1; i < nums.length; i++) {
            instructions.add(Integer.parseInt(nums[i]));
        }

        registerA = 164278899142333L;
        registerB = 0;
        registerC = 0;
        instructionPointer = 0;
        program();

//        for(int i=instructions.size()-1; i>=0; i--){
//            for(long j=foundAreg*8; j<foundAreg*8+8; j++){
//                //Setup
//                registerA = j;
//                registerB = 0;
//                registerC = 0;
//                instructionPointer = 0;
//
//                //Logic
//                System.out.print(registerA+": ");
//                program();
//                System.out.println();
//
//            }
//
//            System.exit(0);
//        }
    }

    public static void program(){
        while(instructionPointer<instructions.size()){
            int opCode = instructions.get(instructionPointer);
            int literalOperand = instructions.get(instructionPointer+1);


            switch(opCode){
                case 0:
                    adv(literalOperand);
                    break;
                case 1:
                    bxl(literalOperand);
                    break;
                case 2:
                    bst(literalOperand);
                    break;
                case 3:
                    jnz(literalOperand);
                    break;
                case 4:
                    bxc(literalOperand);
                    break;
                case 5:
                    out(literalOperand);
                    break;
                case 6:
                    bdv(literalOperand);
                    break;
                case 7:
                    cdv(literalOperand);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + literalOperand);
            }

            instructionPointer+=2;
        }




        //System.out.println(registerB);
    }

    private static void adv(int literalOperand) {
        long comboOperand = toCombo(literalOperand);
        double div = (double)registerA/Math.pow(2,comboOperand);
        registerA = (long)div;
    }

    private static void bxl(int literalOperand) {
        registerB ^= literalOperand;
    }

    private static void bst(int literalOperand) {
        long comboOperand = toCombo(literalOperand);
        registerB = comboOperand % 8;
    }

    private static void jnz(int literalOperand) {
        if(registerA == 0){
            return;
        }

        instructionPointer = literalOperand-2;
    }

    private static void bxc(int literalOperand) {
        registerB ^= registerC;
    }

    private static void out(int literalOperand) {
        long comboOperand = toCombo(literalOperand);
        long val = comboOperand%8;
        System.out.print(val+",");
    }

    private static void bdv(int literalOperand) {
        long comboOperand = toCombo(literalOperand);
        double div = registerA/Math.pow(2,comboOperand);
        registerB = (long)div;
    }

    private static void cdv(int literalOperand) {
        long comboOperand = toCombo(literalOperand);
        double div = registerA/Math.pow(2,comboOperand);
        registerC = (long)div;
    }

    private static long toCombo(int literalOperand){
        return switch (literalOperand) {
            case 0, 1, 2, 3 -> literalOperand;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> throw new IllegalStateException("Unexpected value: " + literalOperand);
        };
    }
}