import java.util.*;
import java.io.*;




public class Day6v1 {
    static String fileName = "input.txt";
    public static void main(String[] args) throws Exception {
        part1();
        //part2();
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
            if(location != null) {
                break;
            }
        }

        map.get(location[0])[location[1]] = 'X';

        char direction = 'U';

        while (notOnBorder(map,location)) {
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

            if(!notOnBorder(map,location)) {
                break;
            }

            if(direction == 'U' && map.get(location[0]-1)[location[1]] == '#') {
                direction = 'R';
            }
            else if(direction == 'D' && map.get(location[0]+1)[location[1]] == '#') {
                direction = 'L';
            }
            else if(direction == 'L' && map.get(location[0])[location[1]-1] == '#') {
                direction = 'U';
            }
            else if(direction == 'R' && map.get(location[0])[location[1]+1] == '#') {
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
        System.out.println(count);

        return map;
    }

    public static void part2() throws Exception {
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
            if(location != null) {
                break;
            }
        }


        ArrayList<char[]> normalPath = part1();

        int numCycles = 0;
        for(int i = 0; i < normalPath.size(); i++) {
            for (int j = 0; j < normalPath.get(i).length; j++) {
                if (normalPath.get(i)[j] != 'X') {
                    continue;
                }

                ArrayList<char[]> modPath = modPath(map, i, j);
                //System.out.println("Test");

                if (detectCycle(modPath, location)) {
                    numCycles++;
                }


            }
        }
        System.out.println(numCycles);
    }

    public static ArrayList<char[]> modPath(ArrayList<char[]> normalPath, int i, int j) {
        ArrayList<char[]> modPath = new ArrayList<>();
        for(int x = 0; x < normalPath.size(); x++) {
            char[] row = normalPath.get(x);
            char[] newRow = new char[row.length];
            modPath.add(newRow);
            for(int y = 0; y < row.length; y++) {
                if(x == i && y == j) {
                    newRow[y] = '#';
                }
                else {
                    newRow[y] = row[y];
                }
            }
        }

        return modPath;
    }



    public static boolean detectCycle(ArrayList<char[]> map, int[] startLocation) {
        int[] location = new int[]{startLocation[0], startLocation[1]};
        char direction = 'U';

        for (int i = 0; i < 100000; i++) {

            map.get(location[0])[location[1]] = 'o';
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


            if(!notOnBorder(map,location)) {
                //System.out.println(i);


                return false;
            }

            if(direction == 'U' && map.get(location[0]-1)[location[1]] == '#') {
                direction = 'R';
            }
            else if(direction == 'D' && map.get(location[0]+1)[location[1]] == '#') {
                direction = 'L';
            }
            else if(direction == 'L' && map.get(location[0])[location[1]-1] == '#') {
                direction = 'U';
            }
            else if(direction == 'R' && map.get(location[0])[location[1]+1] == '#') {
                direction = 'D';
            }

            //Rotate if it hits a wall
        }
        for(int x = 0; x < map.size(); x++) {
            for(int y = 0; y < map.get(x).length; y++) {
                System.out.print(map.get(x)[y]);
            }
            System.out.println();
        }
        System.out.println();
        return true;
    }

    public static boolean notOnBorder(ArrayList<char[]> map, int[] location) {
        return location[0] != 0 && location[0] != map.size() - 1 && location[1] != 0 && location[1] != map.get(location[0]).length - 1;
    }

//    public static boolean modNotOnBorder(ArrayList<Node[]> map, int[] location) {
//        if(location[0] == 0 || location[0] == map.size()-1 || location[1] == 0 || location[1] == map.get(location[0]).length-1) {
//            return false;
//        }
//        return true;
//    }
}

//class Node {
//    public char type;
//    public ArrayList<Character> walkDirections;
//    public Node(char type) {
//        this.type = type;
//        this.walkDirections = new ArrayList<>();
//    }
//}