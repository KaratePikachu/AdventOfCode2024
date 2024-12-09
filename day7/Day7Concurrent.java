import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Day7Concurrent {
    static String fileName = "input.txt";

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        ArrayList<ArrayList<Long>> eq = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            ArrayList<Long> row = new ArrayList<>();
            eq.add(row);
            String[] nextLine = myReader.nextLine().split(" ");
            //System.out.println(nextLine);
            row.add(Long.parseLong(nextLine[0].substring(0,nextLine[0].length()-1)));

            for(int i=1; i<nextLine.length; i++){
                row.add(Long.parseLong(nextLine[i]));
            }
            //Read Logic here

        }

        runPerms(eq);

        System.out.println((System.currentTimeMillis()-start)/(1000.0));


    }

    public static void runPerms(ArrayList<ArrayList<Long>> eq){
        long sum = 0;
        for(ArrayList<Long> row : eq){

            ForkJoinPool fjp = new ForkJoinPool();
            fjp.invoke(new ValidateEquationTask(row, row.get(1),2));

            if(ValidateEquationTask.foundPossibility){
                sum+=row.getFirst();
            }
            ValidateEquationTask.foundPossibility = false;
        }
        System.out.println(sum);
    }
}

class ValidateEquationTask extends RecursiveAction{
    static boolean foundPossibility = false;
    ArrayList<Long> row;
    long total;
    int index;
    ValidateEquationTask(ArrayList<Long> row, long total, int index)
    {
        this.row = row;
        this.total = total;
        this.index = index;
    }

    @Override
    protected void compute() {
        //Early Termination Conditions
        if(total > row.getFirst()){
            return;
        }
        if(foundPossibility){
            return;
        }
        //Termination Condition
        if(index == row.size()){
            if(row.getFirst()==total){
                foundPossibility = true;
            }
            return;
        }

        //Single Thread Condition
        if (index > 4){
            validateEquation(row, total, index);
        }

        List<ValidateEquationTask> subtasks = new ArrayList<>(3);

        subtasks.add(new ValidateEquationTask(row, total+row.get(index),index+1));
        subtasks.add(new ValidateEquationTask(row, total*row.get(index),index+1));
        subtasks.add(new ValidateEquationTask(row, Long.parseLong(""+total+row.get(index)),index+1));
        invokeAll(subtasks);
    }

    public void validateEquation(ArrayList<Long> row, long total, int index){
        //Early Termination Conditions
        if(total > row.getFirst()){
            return;
        }
        if(foundPossibility){
            return;
        }

        if(index == row.size()){
            if(row.getFirst()==total){
                foundPossibility = true;
            }
            return;
        }

        validateEquation(row, total+row.get(index),index+1);
        validateEquation(row, total*row.get(index),index+1);
        validateEquation(row, Long.parseLong(""+total+row.get(index)),index+1);
    }


}