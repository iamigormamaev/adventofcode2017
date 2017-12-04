package solutions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FourthDay {

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
}
