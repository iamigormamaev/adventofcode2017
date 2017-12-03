package solutions;

public class FirstDay {

    public void firstPart(int[] inputArray) {
        int result = 0;
        for (int i = 0; i < inputArray.length - 1; i++) {
            if (inputArray[i] == inputArray[i + 1]) {
                result = result + inputArray[i];
            }
        }
        if (inputArray[0] == inputArray[inputArray.length - 1]) result = result + inputArray[0];
        System.out.println(result);
    }

    public void secondPart(int[] inputArray) {
        int result = 0;
        int step = inputArray.length / 2;
        int index = 0;

        for (int i = 0; i < inputArray.length; i++) {

            if (i < step) {
                index = i + step;
            } else {
                index = i + step - inputArray.length;
            }
            if (inputArray[i] == inputArray[index]) {
                result = result + inputArray[i];
            }
        }
        System.out.println(result);
    }
}
