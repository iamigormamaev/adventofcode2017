package solutions;

public class Day14 {

    public void firstPart(String s) {
        String[] hashes = getKnotHashes(s);
        String[] binaryHashes = getBinaryHashes(hashes);
        int result = countUsedSquares(binaryHashes);

        System.out.println(result);
    }

    public void secondPart(String s) {
        String[] hashes = getKnotHashes(s);
        String[] binaryHashes = getBinaryHashes(hashes);
        int result = countGroups(binaryHashes);

        System.out.println(result);
    }

    private int countGroups(String[] binaryHashes) {
        int result = 0;
        int[][] array = stringBinaryHashesToIntArray(binaryHashes);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 1) {
                    result++;
                    clearGroup(array, i, j);
                }
            }
        }
        return result;
    }

    private void clearGroup(int[][] array, int i, int j) {
        array[i][j] = 0;
        if (i + 1 < array.length && array[i + 1][j] == 1) {
            clearGroup(array, i + 1, j);
        }
        if (i - 1 > -1 && array[i - 1][j] == 1) {
            clearGroup(array, i - 1, j);
        }
        if (j + 1 < array[i].length && array[i][j + 1] == 1) {
            clearGroup(array, i, j + 1);
        }
        if (j - 1 > -1 && array[i][j - 1] == 1) {
            clearGroup(array, i, j - 1);
        }
    }

    private int[][] stringBinaryHashesToIntArray(String[] binaryHashes) {
        int[][] result = new int[binaryHashes.length][];
        for (int i = 0; i < binaryHashes.length; i++) {
            char[] chars = binaryHashes[i].toCharArray();
            result[i] = new int[chars.length];
            for (int j = 0; j < chars.length; j++) {
                result[i][j] = Character.getNumericValue(chars[j]);
            }
        }
        return result;
    }

    private int countUsedSquares(String[] binaryHashes) {
        int result = 0;
        for (int i = 0; i < binaryHashes.length; i++) {
            char[] chars = binaryHashes[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                result += Character.getNumericValue(chars[j]);
            }
        }
        return result;
    }

    private String[] getKnotHashes(String s) {
        String[] result = new String[128];
        Day10 hashBuilder = new Day10();
        for (int i = 0; i < 128; i++) {
            result[i] = hashBuilder.knotHash(s + "-" + i);
        }
        return result;
    }

    private String[] getBinaryHashes(String[] hashes) {
        String[] result = new String[128];
        for (int i = 0; i < hashes.length; i++) {
            StringBuilder currentString = new StringBuilder();
            char[] chars = hashes[i].toCharArray();
            for (int k = 0; k < chars.length; k++) {

                String binString = Integer.toBinaryString(Integer.parseInt(Character.toString(chars[k]), 16));
                if (binString.length() < 4) {

                }
                currentString.append(("0000" + binString).substring(binString.length()));
            }
            result[i] = currentString.toString();
        }
        return result;
    }


}
