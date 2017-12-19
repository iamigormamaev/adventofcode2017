package solutions;

public class Day19 {
    int prevX = 0;
    int prevY = 0;
    int x = 0;
    int y = 0;
    Direction direction = Direction.DOWN;

    public void firstPart(String[] inputStrings) {
        solution(inputStrings, false);
    }

    public void secondPart(String[] inputStrings) {
        solution(inputStrings, true);
    }

    public void solution(String[] inputStrings, boolean needToCountSteps) {

        String[][] input = new String[inputStrings.length][];
        for (int i = 0; i < inputStrings.length; i++) {
            input[i] = inputStrings[i].split("");
        }

        StringBuilder result = new StringBuilder();
        findStartCoordinate(input);
        boolean isEndReached = false;
        int stepCount = 0;

        for (int i = x, j = y; !isEndReached; i = direction == Direction.DOWN || direction == Direction.UP ? i :
                direction == Direction.LEFT ? --i : ++i,
                j = direction == Direction.LEFT || direction == Direction.RIGHT ? j :
                        direction == Direction.UP ? --j : ++j) {
            stepCount++;
            switch (input[j][i]) {
                case "|":
                    break;
                case "-":
                    break;
                case "+":
                    findNewDirection(j, i, prevX, prevY, input);
                    break;
                case " ":
                    isEndReached = true;
                    break;
                default:
                    result.append(input[j][i]);
                    break;
            }
            prevX = j;
            prevY = i;
        }
        if (needToCountSteps) {
            System.out.println(stepCount);
        } else {
            System.out.println(result.toString());
        }
    }


    private void findNewDirection(int x, int y, int prevX, int prevY, String[][] input) {

        if ((x + 1 != prevX && y != prevY) && x + 1 < input.length && (!input[x + 1][y].equals(" ")))
            direction = Direction.DOWN;
        else if ((x != prevX && y + 1 != prevY) && y + 1 < input[x].length && (!input[x][y + 1].equals(" ")))
            direction = Direction.RIGHT;
        else if ((x - 1 != prevX && y != prevY) && x - 1 >= 0 && (!input[x - 1][y].equals(" ")))
            direction = Direction.UP;
        else if ((x != prevX && y - 1 != prevY) && x - 1 >= 0 && (!input[x][y - 1].equals(" ")))
            direction = Direction.LEFT;

    }

    private void findStartCoordinate(String[][] input) {
        for (int i = 0; i < input[0].length; i++) {
            if (input[0][i].equals("|")) {
                x = i;
                y = 1;
                prevX = i;
                prevY = 0;
                direction = Direction.DOWN;
            }
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
}
