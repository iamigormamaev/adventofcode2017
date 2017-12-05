package solutions;

public class FifthDay {
    public void firstPart(int[] inputArray) {
        int i = 0;
        int step = 0;
        while (true) {
            try {
                i += inputArray[i]++;
                step++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(step);
                break;
            }
        }
    }
}
