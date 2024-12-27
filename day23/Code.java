import java.util.*;
import java.io.*;



public class Code{
    public static String fileName = "input.txt";
    static HashMap<String,TreeSet<String>> graph;
    //static HashSet<String> alreadySearched = new HashSet<>();
    //static HashMap<String,Integer> clusterSize = new HashMap<>();
    static ArrayList<TreeSet<String>> allSets = new ArrayList<>();
    static TreeSet<String> largestSet = new TreeSet<>();

    public static void main(String[] args) throws Exception{
        //Data Structure
        graph = new HashMap<>();
        Scanner myReader = new Scanner(new File(fileName));
        while(myReader.hasNextLine()){
            String[] computers = myReader.nextLine().split("-");

            for(String computer : computers){
                if(!graph.containsKey(computer)){
                    graph.put(computer,new TreeSet<>());
                }
            }

            graph.get(computers[0]).add(computers[1]);
            graph.get(computers[1]).add(computers[0]);


            //Read Logic here

        }


        for(String current : graph.keySet()){
            getLargestPossibleSetSize(current,new TreeSet<String>());
        }

        for(String s : largestSet){
            System.out.print(s+",");
        }

//        for(String key : graph.keySet()){
//            if(alreadySearched.contains(key)){
//                continue;
//            }
//            //TreeSet<String> adjacents = graph.get(key);
//            countSize(key);
//
//        }

        //System.out.println(result);
    }

    private static void getLargestPossibleSetSize(String current, TreeSet<String> set) {
        set.add(current);

        TreeSet<String> adjacents = graph.get(current);
        for(String other : adjacents){
            if(set.contains(other)){
                continue;
            }
            if(graph.get(other).containsAll(set)){
                TreeSet<String> clone = new TreeSet<>();
                clone.addAll(set);
                boolean foundDupe = false;
                for(TreeSet<String> estabishedSet : allSets){
                    if(estabishedSet.containsAll(clone)){
                        foundDupe=true;
                        break;
                    }
                }
                if(foundDupe){
                    return;
                }
                //System.out.println(clone.size());
                allSets.add(set);
                getLargestPossibleSetSize(other,clone);
            }

        }

        if(set.size()> largestSet.size()){
            largestSet = set;
        }

    }

    private static boolean validLAN(String curr) {
        TreeSet<String> connections = graph.get(curr);
        for(String s : connections){
            for(String other : graph.get(s)){
                if(!connections.contains(other) && !other.equals(curr)){
                    return false;
                }
            }
        }
        return true;

//        TreeSet<String> connections = graph.get(curr);
//        if(connections == null){
//            return;
//        }
//        String[] vals = new String[connections.size()];
//        connections.toArray(vals);
//
//        for(int i=0; i<vals.length; i++){
//            for(int j=i+1; j<vals.length; j++){
//                String val1 = vals[i];
//                String val2 = vals[j];
//                if(alreadySearched.contains(val1) || alreadySearched.contains(val2)){
//                    continue;
//                }
//                if(graph.get(val1).contains(val2)){
//                    if(!graph.get(val2).contains(val1)){
//                        throw new IllegalStateException();
//                    }
//                    System.out.println(val1+", "+val2);
//                    result++;
//                }
//            }
//        }
//        alreadySearched.add(curr);

    }
}

