package solutions;

public class Day5 {

    public void firstPart(int[] inputArray) {
        solution(inputArray, false);
    }

    public void secondPart(int[] inputArray) {
        solution(inputArray, true);
    }

    private void solution(int[] inputArray, boolean isNeedToDecrease) {
        int i = 0;
        int step = 0;
        boolean isSolutionFind = false;
        while (!isSolutionFind) {
            try {
                if (isNeedToDecrease && inputArray[i] >= 3) {
                    i += inputArray[i]--;
                } else {
                    i += inputArray[i]++;
                }
                step++;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(step);
                isSolutionFind = true;
            }
        }
    }
}
