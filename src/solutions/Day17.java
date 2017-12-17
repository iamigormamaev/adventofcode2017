package solutions;

import java.util.ArrayList;
import java.util.List;

public class Day17 {
    public void firstPart(int numberOfSteps) {
        List<Integer> buffer = solution(numberOfSteps, 2017, true);
        System.out.println(buffer.get(buffer.indexOf(2017) + 1));

    }

    public void secondPart(int numberOfSteps) {
        List<Integer> buffer = solution(numberOfSteps, 50000000, false);
        System.out.println(buffer.get(1));
    }

    private List<Integer> solution(int numberOfSteps, int iterations, boolean saveAllValuesInList) {

        List<Integer> buffer = new ArrayList<>();
        buffer.add(0);

        int currentPosition = 0;
        int bufferSize = 1;

        for (int i = 1; i <= iterations; i++) {
            currentPosition = getCurrentPosition(numberOfSteps, bufferSize, currentPosition);
            bufferSize += 1;
            addNextValue(buffer, i, currentPosition, saveAllValuesInList);
        }
        return buffer;
    }

    private int getCurrentPosition(int stepCount, int bufferSize, int previousPosition) {
        int currentPosition = previousPosition;
        for (int i = 0; i < stepCount; i++) {
            currentPosition = currentPosition < bufferSize - 1 ? ++currentPosition : 0;
        }
        return currentPosition + 1;
    }

    private void addNextValue(List<Integer> buffer, int valueToInsert, int currentPosition, boolean saveAllValuesInList) {
        if (saveAllValuesInList || currentPosition < 2) {
            try {
                buffer.add(currentPosition, valueToInsert);
            } catch (IndexOutOfBoundsException e) {
                buffer.add(valueToInsert);
            }
        }
    }

}
