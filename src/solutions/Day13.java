package solutions;

import java.util.HashMap;
import java.util.Map;

public class Day13 {

    public void firstPart(String[] input) {
        Map<Integer, Level> levels = parseLevels(input);
        int depthCount = parseDepth(input[input.length - 1]);
        int currentPosition = 0;
        int severity = 0;

        for (int i = 0; i <= depthCount; i++) {
            if (levels.containsKey(currentPosition)) {
                severity += levels.get(currentPosition).getSeverity();
            }

            for (Map.Entry<Integer, Level> pair :
                    levels.entrySet()) {
                pair.getValue().makeStep();
            }

            currentPosition++;
        }

        System.out.println(severity);
    }

    public void secondPart(String[] input) {
        Map<Integer, Level> levels = parseLevels(input);
        int depthCount = parseDepth(input[input.length - 1]);

        long result = 0;

        boolean isAnswerFinded = false;

        for (long j = 1; !isAnswerFinded; j++) {
            boolean caught = false;
            makeSteps(levels,1);
            Map<Integer, Level> levelsCopy = copyMap(levels);
            int currentPosition = 0;
            for (int i = 0; i <= depthCount; i++) {
                if (levelsCopy.containsKey(currentPosition)) {
                    if (levelsCopy.get(currentPosition).getCaught()) {
                        caught = true;
                        break;
                    }
                }
                makeSteps(levelsCopy, 1);
                currentPosition++;
            }
            if (!caught) {
                isAnswerFinded = true;
                result = j;
            }
        }
        System.out.println(result);
    }

    private void makeSteps(Map<Integer, Level> levels, long stepsCount) {
        for (long i = 0; i < stepsCount; i++) {
            for (Map.Entry<Integer, Level> pair :
                    levels.entrySet()) {
                pair.getValue().makeStep();
            }
        }
    }

    private Map<Integer, Level> copyMap(Map<Integer, Level> original) {
        Map<Integer, Level> copyMap = new HashMap<>();
        for (HashMap.Entry<Integer, Level> pair :
                original.entrySet()) {
            copyMap.put(pair.getKey(), new Level(pair.getValue()));
        }
        return copyMap;
    }

    private Map<Integer, Level> parseLevels(String[] strings) {
        Map<Integer, Level> result = new HashMap<>();
        for (String s :
                strings) {
            String[] parts = s.split(": ");
            result.put(Integer.parseInt(parts[0]), new Level(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        return result;
    }

    private int parseDepth(String s) {
        String[] parts = s.split(": ");
        return Integer.parseInt(parts[0]);
    }

    public class Level {
        int depth;
        int range;
        int currentRange;
        int direction;

        public Level(int depth, int range) {
            this.depth = depth;
            this.range = range;
            currentRange = 0;
            direction = 1;
        }

        public Level(Level level) {
            this.depth = level.depth;
            this.range = level.range;
            currentRange = level.currentRange;
            direction = level.direction;
        }

        public void makeStep() {
            currentRange += direction;
            if (currentRange == (range - 1)) direction = -1;
            else if (currentRange == 0) direction = 1;
        }

        public int getSeverity() {
            if (currentRange == 0) return range * depth;
            else return 0;
        }

        public boolean getCaught() {
            if (currentRange == 0) return true;
            else return false;
        }

        @Override
        public String toString() {
            return "Level{" +
                    "depth=" + depth +
                    ", range=" + range +
                    ", currentRange=" + currentRange +
                    ", direction=" + direction +
                    '}';
        }
    }
}
