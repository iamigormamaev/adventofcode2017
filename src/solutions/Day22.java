package solutions;

import java.util.HashMap;
import java.util.Map;

public class Day22 {


    public void firstPart(String[] input) {

        Map<Coordinate, Node> nodeMap = parseNodeMap(input);
        Virus virus = new Virus(nodeMap, nodeMap.get(new Coordinate(0, 0)), "UP");
//        printGrid(nodeMap);
        for (int i = 0; i < 10_000; i++) {
            virus.burstFirstPart();
        }
        System.out.println(virus.getInfectedCounter());
    }

    public void secondPart(String[] input) {
        Map<Coordinate, Node> nodeMap = parseNodeMap(input);
        Virus virus = new Virus(nodeMap, nodeMap.get(new Coordinate(0, 0)), "UP");
//        printGrid(nodeMap);
        for (int i = 0; i < 10_000_000; i++) {
            virus.burstSecondPart();
        }
        System.out.println(virus.getInfectedCounter());
    }

    public void printGrid(Map<Coordinate, Node> nodeMap) {
        int gridSize = findGridSize(nodeMap);
        char[][] grid = new char[gridSize][gridSize];
        for (Node n :
                nodeMap.values()) {
            switch (n.state) {
                case "INFECTED":
                    grid[n.coordinate.x + (gridSize / 2)][(gridSize / 2) - n.coordinate.y] = '#';
                    break;
                case "CLEAN":
                    grid[n.coordinate.x + (gridSize / 2)][(gridSize / 2) - n.coordinate.y] = '.';
                    break;
                case "WEAKENED":
                    grid[n.coordinate.x + (gridSize / 2)][(gridSize / 2) - n.coordinate.y] = 'w';
                    break;
                case "FLAGGED":
                    grid[n.coordinate.x + (gridSize / 2)][(gridSize / 2) - n.coordinate.y] = 'f';
                    break;
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[j][i] != '#' && grid[j][i] != '.' && grid[j][i] != 'w' && grid[j][i] != 'f') {
                    grid[j][i] = '.';
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private int findGridSize(Map<Coordinate, Node> nodeMap) {
        int max = 0;
        for (Coordinate c :
                nodeMap.keySet()) {
            if (c.x > max) max = c.x;
            if (c.y > max) max = c.y;
        }
        return (((max + 1) * 2) % 2 == 0) ? ((max + 1) * 2) + 1 : ((max + 1) * 2);
    }


    private Map<Coordinate, Node> parseNodeMap(String[] input) {
        Map<Coordinate, Node> result = new HashMap<>();
        int center = input.length / 2;
        for (int i = 0; i < input.length; i++) {
            char[] chars = input[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                Coordinate c = new Coordinate(j - center, center - i);
                switch (chars[j]) {
                    case '.':
                        result.put(c, new Node(c, "CLEAN"));
                        break;
                    case '#':
                        result.put(c, new Node(c, "INFECTED"));
                        break;
                }
            }
        }
        return result;
    }

    public class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate(Coordinate coordinate) {
            this.x = coordinate.x;
            this.y = coordinate.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    public class Node {
        Coordinate coordinate;
        String state;

        public Node(Coordinate coordinate, String state) {
            this.coordinate = coordinate;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (coordinate != null ? !coordinate.equals(node.coordinate) : node.coordinate != null) return false;
            return state != null ? state.equals(node.state) : node.state == null;
        }

        @Override
        public int hashCode() {
            int result = coordinate != null ? coordinate.hashCode() : 0;
            result = 31 * result + (state != null ? state.hashCode() : 0);
            return result;
        }
    }

    public class Virus {
        private Map<Coordinate, Node> nodeMap;
        private Node currentNode;
        private String direction;

        private int infectedCounter;


        public Virus(Map<Coordinate, Node> nodeMap, Node startNode, String direction) {
            this.nodeMap = nodeMap;
            this.currentNode = startNode;
            this.direction = direction;
            infectedCounter = 0;
        }

        public int getInfectedCounter() {
            return infectedCounter;
        }

        public void burstFirstPart() {
            if (currentNode.state.equals("INFECTED")) {
                changeDirectionTo("RIGHT");
                currentNode.state = "CLEAN";

            } else {
                changeDirectionTo("LEFT");
                currentNode.state = "INFECTED";
                infectedCounter++;
            }
            changeNode();
//            printGrid(nodeMap);
        }

        public void burstSecondPart() {
            switch (currentNode.state) {
                case "INFECTED":
                    changeDirectionTo("RIGHT");
                    currentNode.state = "FLAGGED";
                    break;
                case "CLEAN":
                    changeDirectionTo("LEFT");
                    currentNode.state = "WEAKENED";
                    break;
                case "WEAKENED":
                    currentNode.state = "INFECTED";
                    infectedCounter++;
                    break;
                case "FLAGGED":
                    changeDirectionTo("REVERSE");
                    currentNode.state = "CLEAN";
                    break;
            }
            changeNode();
//            printGrid(nodeMap);
        }

        private void changeNode() {
            Coordinate coordinate = new Coordinate(currentNode.coordinate);
            switch (direction) {
                case "RIGHT":
                    coordinate.x++;
                    break;
                case "LEFT":
                    coordinate.x--;
                    break;
                case "UP":
                    coordinate.y++;
                    break;
                case "DOWN":
                    coordinate.y--;
                    break;
            }

            if (!nodeMap.containsKey(coordinate)) {
                nodeMap.put(coordinate, new Node(coordinate, "CLEAN"));
            }
            currentNode = nodeMap.get(coordinate);
        }

        private void changeDirectionTo(String command) {
            switch (command) {
                case "RIGHT":
                    switch (direction) {
                        case "RIGHT":
                            direction = "DOWN";
                            break;
                        case "LEFT":
                            direction = "UP";
                            break;
                        case "UP":
                            direction = "RIGHT";
                            break;
                        case "DOWN":
                            direction = "LEFT";
                            break;
                    }
                    break;
                case "LEFT":
                    switch (direction) {
                        case "RIGHT":
                            direction = "UP";
                            break;
                        case "LEFT":
                            direction = "DOWN";
                            break;
                        case "UP":
                            direction = "LEFT";
                            break;
                        case "DOWN":
                            direction = "RIGHT";
                            break;
                    }
                    break;
                case "REVERSE":
                    switch (direction) {
                        case "RIGHT":
                            direction = "LEFT";
                            break;
                        case "LEFT":
                            direction = "RIGHT";
                            break;
                        case "UP":
                            direction = "DOWN";
                            break;
                        case "DOWN":
                            direction = "UP";
                            break;
                    }
                    break;
            }

        }
    }
}
