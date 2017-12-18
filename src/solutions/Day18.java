package solutions;

import java.util.HashMap;
import java.util.Map;

public class Day18 {
    public void firstPart(String[] inputArray) {
        /*snd X plays a sound with a frequency equal to the value of X.
set X Y sets register X to the value of Y.
add X Y increases register X by the value of Y.
mul X Y sets register X to the result of multiplying the value contained in register X by the value of Y.
mod X Y sets register X to the remainder of dividing the value contained in register X by the value of Y (that is, it sets X to the result of X modulo Y).
rcv X recovers the frequency of the last sound played, but only when the value of X is not zero. (If it is zero, the command does nothing.)
jgz X Y jumps with an offset of the value of Y, but only if the value of X is greater than zero. (An offset of 2 skips the next instruction,
an offset of -1 jumps to the previous instruction, and so on.)*/

        Map<String, Long> values = new HashMap<>();
        boolean isEndReached = false;
        long lastSoundValue = 0;
        for (int i = 0; i < inputArray.length; i++) {

            String[] parts = inputArray[i].split(" ");

            switch (parts[0]) {
                case "snd":
                    lastSoundValue = getValueFromMap(values, parts[1]);
                    break;
                case "set":
                    values.put(parts[1], getValueFromMap(values, parts[2]));
                    break;
                case "add":
                    values.put(parts[1], getValueFromMap(values, parts[1]) + getValueFromMap(values, parts[2]));
                    break;
                case "mul":
                    values.put(parts[1], getValueFromMap(values,parts[1]) * getValueFromMap(values, parts[2]));
                    break;
                case "mod":
                    values.put(parts[1], getValueFromMap(values, parts[1]) % getValueFromMap(values, parts[2]));

                    break;
                case "rcv":
                    try {
                        if (values.get(parts[1]) != 0) {
                            System.out.println(lastSoundValue);
                            isEndReached = true;
                        }

                    } catch (NullPointerException e) {

                    }
                    break;
                case "jgz":
                    try {
                        if (values.get(parts[1]) != 0) {
                            i += getValueFromMap(values, parts[2]) - 1;
                        }
                    } catch (NullPointerException e) {

                    }
                    break;
            }
            if (isEndReached) break;
        }
    }

    private long getValueFromMap(Map<String, Long> map, String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            try {
                return map.get(s);
            } catch (NullPointerException ex) {
                return 0;
            }
        }
    }
}
