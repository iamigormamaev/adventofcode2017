package solutions;


import java.util.*;
import java.util.stream.Collectors;


public class Day6 {

    public void firstPart(int[] inputArray) {
        System.out.println(solution(inputArray, true));
    }

    public void secondPart(int[] inputArray) {
        System.out.println(solution(inputArray, false));
    }

    public int solution(int[] inputArray, boolean isNeedToReturnOnlySteps) {

        Map<List<Integer>, Integer> allArraysMap = new HashMap<>();
        int step = 0;
        List<Integer> tempList = null;

        boolean isSolutionFinded = false;
        while (!isSolutionFinded) {
            inputArray = redistribute(inputArray);
            tempList = Arrays.stream(inputArray).boxed().collect(Collectors.toList());
            step++;
            if (allArraysMap.containsKey(tempList)) {
                isSolutionFinded = true;
            } else {
                allArraysMap.put(tempList, step);
            }
        }

        return isNeedToReturnOnlySteps ? step : step - allArraysMap.get(tempList);
    }


    public int[] redistribute(int[] array) {
        int redistributionValue = Integer.MIN_VALUE;
        int redistributionBankIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > redistributionValue) {
                redistributionValue = array[i];
                redistributionBankIndex = i;
            }
        }

        array[redistributionBankIndex] = 0;

        for (int i = redistributionBankIndex; redistributionValue > 0; ) {
            i = (i >= array.length - 1) ? 0 : ++i;
            array[i] += 1;
            redistributionValue -= 1;
        }
        return array;
    }

}