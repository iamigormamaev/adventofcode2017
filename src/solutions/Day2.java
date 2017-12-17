package solutions;

public class Day2 {

    public void firstPart(int[][] inputArray) {
        int result = 0;
        for (int i = 0; i < inputArray.length; i++) {
            int strMin = Integer.MAX_VALUE;
            int strMax = Integer.MIN_VALUE;
            int strDif = 0;
            for (int j = 0; j < inputArray[i].length; j++) {
                if (inputArray[i][j] > strMax) strMax = inputArray[i][j];
                if (inputArray[i][j] < strMin) strMin = inputArray[i][j];
            }
            strDif = strMax - strMin;
            result = result + strDif;
        }
        System.out.println();
        System.out.println(result);
    }

    public void secondPart(int[][] inputArray) {

        int result = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                for (int k = 0; k < inputArray[i].length; k++) {
                    if (j != k && inputArray[i][j] % inputArray[i][k] == 0) {
                        result = result + inputArray[i][j] / inputArray[i][k];
                    }
                }
            }
        }
        System.out.println(result);
    }
}
