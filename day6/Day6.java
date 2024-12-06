import java.util.*;
import java.io.*;

class Node {
    public char type;
    public ArrayList<Character> walkDirections;
    public Node(char type) {
        this.type = type;
        this.walkDirections = new ArrayList<>();
    }
}


public class Day6 {
    static String fileName = "input.txt";

    public static void main(String[] args) throws Exception {
        //part1();
        part2();
    }

    public static ArrayList<char[]> part1() throws Exception {

        ArrayList<char[]> map = new ArrayList<>();
        Scanner myReader = new Scanner(new File(fileName));
        while (myReader.hasNextLine()) {
            String nextLine = myReader.nextLine();
            map.add(nextLine.toCharArray());
        }

        int[] location = null;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                if (map.get(i)[j] == '^') {
                    location = new int[]{i, j};
                    break;
                }
            }
            if (location != null) {
                break;
            }
        }

        map.get(location[0])[location[1]] = 'X';

        char direction = 'U';

        while (notOnBorder(map, location)) {
            switch (direction) {
                case 'U':
                    location[0]--;
                    break;
                case 'D':
                    location[0]++;
                    break;
                case 'L':
                    location[1]--;
                    break;
                case 'R':
                    location[1]++;
                    break;
            }

            map.get(location[0])[location[1]] = 'X';

            if (!notOnBorder(map, location)) {
                break;
            }

            if (direction == 'U' && map.get(location[0] - 1)[location[1]] == '#') {
                direction = 'R';
            } else if (direction == 'D' && map.get(location[0] + 1)[location[1]] == '#') {
                direction = 'L';
            } else if (direction == 'L' && map.get(location[0])[location[1] - 1] == '#') {
                direction = 'U';
            } else if (direction == 'R' && map.get(location[0])[location[1] + 1] == '#') {
                direction = 'D';
            }

            //Rotate if it hits a wall
        }


        //Count the number of X's
        int count = 0;
        for (char[] row : map) {
            for (char c : row) {
                if (c == 'X') {
                    count++;
                }
            }
            //System.out.println();
        }
        //System.out.println(count);

        return map;
    }

    public static void part2() throws Exception {
        ArrayList<char[]> normalPath = part1();

        ArrayList<ArrayList<Node>> newMap = createNewMap();
        //find start
        int[] startLocation = null;
        for (int i = 0; i < newMap.size(); i++) {
            for (int j = 0; j < newMap.get(i).size(); j++) {
                if (newMap.get(i).get(j).type == '^') {
                    startLocation = new int[]{i, j};
                    break;
                }
            }
            if (startLocation != null) {
                break;
            }
        }

        int numCycles = 0;
        for (int i = 0; i < normalPath.size(); i++) {
            for (int j = 0; j < normalPath.get(i).length; j++) {
                if (normalPath.get(i)[j] != 'X') {
                    continue;
                }
                newMap = createNewMap();
                newMap.get(i).get(j).type = '#';

                if(detectCycle(newMap, startLocation)) {
                    numCycles++;
                }
            }
        }

        System.out.println(numCycles);
    }


    public static boolean detectCycle(ArrayList<ArrayList<Node>> map, int[] startLocation) {
        int[] location = new int[]{startLocation[0], startLocation[1]};
        char direction = 'U';



        while (modNotOnBorder(map, location)) {
            switch (direction) {
                case 'U':
                    location[0]--;
                    break;
                case 'D':
                    location[0]++;
                    break;
                case 'L':
                    location[1]--;
                    break;
                case 'R':
                    location[1]++;
                    break;
            }

            if(map.get(location[0]).get(location[1]).walkDirections.contains(direction)){
                return true;
            }
            map.get(location[0]).get(location[1]).walkDirections.add(direction);

            if (!modNotOnBorder(map, location)) {
                break;
            }

            if (direction == 'U' && map.get(location[0] - 1).get(location[1]).type == '#') {
                direction = 'R';
            } else if (direction == 'D' && map.get(location[0] + 1).get(location[1]).type == '#') {
                direction = 'L';
            } else if (direction == 'L' && map.get(location[0]).get(location[1] - 1).type == '#') {
                direction = 'U';
            } else if (direction == 'R' && map.get(location[0]).get(location[1] + 1).type == '#') {
                direction = 'D';
            }


        }
        return false;
    }

    public static ArrayList<ArrayList<Node>> createNewMap() throws FileNotFoundException {
        Scanner myReader = new Scanner(new File(fileName));
        ArrayList<ArrayList<Node>> map = new ArrayList<>();
        while (myReader.hasNextLine()) {
            ArrayList<Node> row = new ArrayList<>();
            map.add(row);

            char[] nextLine = myReader.nextLine().toCharArray();
            for (int i = 0; i < nextLine.length; i++) {
                row.add(new Node(nextLine[i]));
            }
        }

        return map;
    }

    public static boolean notOnBorder(ArrayList<char[]> map, int[] location) {
        return location[0] != 0 && location[0] != map.size() - 1 && location[1] != 0 && location[1] != map.get(location[0]).length - 1;
    }

    public static boolean modNotOnBorder(ArrayList<ArrayList<Node>> map, int[] location) {
        return location[0] != 0 && location[0] != map.size() - 1 && location[1] != 0 && location[1] != map.get(location[0]).size() - 1;
    }
}