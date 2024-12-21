import java.rmi.UnexpectedException;
import java.util.*;
import java.io.*;

public class Code{
    public static String fileName = "test.txt";


    static int instructionPointer = 0;
    static int registerA;
    static int registerB;
    static int registerC;


    public static void main(String[] args) throws Exception{
        program();
    }

    public static void program() throws FileNotFoundException {
        //Data Structure
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> instructions = new ArrayList<>();

        Scanner myReader = new Scanner(new File(fileName));
        registerA = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        registerB = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        registerC = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        myReader.nextLine();

        String[] nums = myReader.nextLine().split("[ ,]");
        for(int i=1; i<nums.length; i++){
            instructions.add(Integer.parseInt(nums[i]));
        }



        //int numOperations = 0;
        while(instructionPointer<instructions.size()){
            //numOperations++;
            //System.out.println("--------Operation "+numOperations+"--------");
            int opCode = instructions.get(instructionPointer);
            int literalOperand = instructions.get(instructionPointer+1);
            //System.out.println("f("+opCode+"), "+literalOperand);

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

    //goal, restore register A1.
    private static void adv(int literalOperand) {
        int comboOperand = toCombo(literalOperand);
        //System.out.println("registerA = registerA/(int)Math.pow(2,"+comboOperand+");");
        registerA = registerA/(int)Math.pow(2,comboOperand);
    }

    //
    private static void bxl(int literalOperand) {
        //System.out.println("registerB ^= "+literalOperand);
        registerB ^= literalOperand;
    }

    private static void bst(int literalOperand) {

        int comboOperand = toCombo(literalOperand);
        //System.out.println("registerB = "+comboOperand+" % 8;");
        registerB = comboOperand % 8;
    }

    private static void jnz(int literalOperand) {
        if(registerA == 0){
            return;
        }
        //System.out.println("Jump to start");
        //System.exit(0);
        instructionPointer = literalOperand-2;
    }

    //Need to restore register B
    private static void bxc(int literalOperand) {
        //System.out.println("registerB ^= registerC;");
        registerB ^= registerC;
    }

    private static void out(int literalOperand) {
        int comboOperand = toCombo(literalOperand);
        int val = comboOperand%8;
        System.out.print(val+",");
    }

    private static void bdv(int literalOperand) {
        int comboOperand = toCombo(literalOperand);
        //System.out.println("registerB = registerB/(int)Math.pow(2,"+comboOperand+");");
        registerB = registerB/(int)Math.pow(2,comboOperand);
    }

    private static void cdv(int literalOperand) {
        int comboOperand = toCombo(literalOperand);
        //System.out.println("registerC = registerC/(int)Math.pow(2,"+comboOperand+");");
        registerC = registerC/(int)Math.pow(2,comboOperand);
    }

    private static int toCombo(int literalOperand){
        return switch (literalOperand) {
            case 0, 1, 2, 3 -> literalOperand;
            case 4 -> registerA;
            case 5 -> registerB;
            case 6 -> registerC;
            default -> throw new IllegalStateException("Unexpected value: " + literalOperand);
        };
    }
}

