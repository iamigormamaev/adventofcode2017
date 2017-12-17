package solutions;

import java.util.*;

public class Day4 {

    public void firstPart(String[] inputArray) {
        int result = 0;
        for (String currentFullString : inputArray) {
            String[] currentWordsArray = currentFullString.split("\\s");
            Set<String> currentWordsSet = new HashSet<>();
            Collections.addAll(currentWordsSet, currentWordsArray);
            if (currentWordsSet.size() == currentWordsArray.length) {
                result++;
            }
        }
        System.out.println(result);
    }

    public void secondPart(String[] inputArray) {
        int result = 0;

        for (String currentFullString : inputArray) {
            String[] currentWordsArray = currentFullString.split("\\s");
            boolean isValid = true;
            for (int i = 0; i < currentWordsArray.length - 1; i++) {
                for (int j = i + 1; j < currentWordsArray.length; j++) {
                    if (wordToMap(currentWordsArray[i]).equals(wordToMap(currentWordsArray[j]))) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) break;
            }
            if (isValid) {
                result++;
            }
        }
        System.out.println(result);
    }

    private Map<Character, Integer> wordToMap(String s) {
        Map<Character, Integer> resultMap = new HashMap<>();
        char[] letters = s.toCharArray();
        for (char c : letters) {
            if (resultMap.containsKey(c)) {
                resultMap.put(c, resultMap.get(c) + 1);
            } else {
                resultMap.put(c, 1);
            }
        }
        return resultMap;
    }
}
