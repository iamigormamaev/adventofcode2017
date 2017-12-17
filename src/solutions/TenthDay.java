package solutions;


public class TenthDay {
    int[] ADDITION_LENGTH = {17, 31, 73, 47, 23};

    public void firstPart(int[] inputArray) {
        int[] hashArray = initHashArray();
        hashArray = doRounds(hashArray, inputArray, 1);
        System.out.println(hashArray[0] * hashArray[1]);
    }


    public void secondPart(String s) {
        int[] lengths = stringToANCIIcodes(s);
        int[] hashArray = initHashArray();
        lengths = mergeArrays(lengths, ADDITION_LENGTH);
        hashArray = doRounds(hashArray, lengths, 64);
        int[] denseHash = getDenseHash(hashArray);
        String hash = arrayToHexString(denseHash);
        System.out.println(hash);
    }

    private String arrayToHexString(int[] array) {
        StringBuilder result = new StringBuilder();
        for (int i :
                array) {
            result.append(Integer.toHexString(i));
        }
        return result.toString();
    }

    private int[] getDenseHash(int[] sparseHash) {
        int[] result = new int[16];
        for (int i = 0; i < 16; i++) {
            int element = 0;
            for (int j = i * 16; j < ((i + 1) * 16); j++) {
                element = element ^ sparseHash[j];
            }
            result[i] = element;
        }
        return result;
    }

    private int[] initHashArray() {
        int[] result = new int[256];
        for (int i = 0; i < 256; i++) {
            result[i] = i;
        }
        return result;
    }

    private int[] doRounds(int[] array, int[] lengths, int roundsCount) {

        int currentPosition = 0;
        int skipSize = 0;
        for (int j = 0; j < roundsCount; j++) {
            for (int i :
                    lengths) {
                int[] arrayToReverse = roundCopyOfRange(array, currentPosition, i);
                arrayToReverse = reverse(arrayToReverse);
                array = roundReplaceArrayPart(array, arrayToReverse, currentPosition);
                currentPosition = roundChangeCurrentPosition(currentPosition, array.length, i, skipSize);
                skipSize++;
            }
        }
        return array;
    }

    private int[] mergeArrays(int[] firstArray, int[] secondArray) {
        int[] result = new int[firstArray.length + secondArray.length];
        for (int i = 0; i < firstArray.length; i++) {
            result[i] = firstArray[i];
        }

        for (int i = firstArray.length, j = 0; j < secondArray.length; i++, j++) {
            result[i] = secondArray[j];
        }
        return result;
    }

    private int[] stringToANCIIcodes(String s) {
        int[] result = new int[s.length()];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            result[i] = (int) chars[i];
        }
        return result;
    }

    private int roundChangeCurrentPosition(int currentPosition, int arrayLength, int currentLength, int skipSize) {

        return (currentPosition + currentLength + skipSize) < arrayLength ? currentPosition + currentLength + skipSize :
                currentPosition + currentLength + skipSize - arrayLength * ((currentPosition + currentLength + skipSize) / arrayLength);
    }

    private int[] reverse(int[] arrayToReverse) {
        for (int i = 0; i < arrayToReverse.length / 2; i++) {
            int temp = arrayToReverse[i];
            arrayToReverse[i] = arrayToReverse[arrayToReverse.length - i - 1];
            arrayToReverse[arrayToReverse.length - i - 1] = temp;
        }
        return arrayToReverse;
    }

    private int[] roundReplaceArrayPart(int[] originalArray, int[] arrayToReplace, int from) {
        for (int i = from, j = 0; j < arrayToReplace.length; i = i < originalArray.length - 1 ? ++i : 0, j++) {
            originalArray[i] = arrayToReplace[j];
        }
        return originalArray;
    }

    private int[] roundCopyOfRange(int[] array, int from, int size) {
        int[] result = new int[size];
        for (int i = from, j = 0; j < size; i = i < array.length - 1 ? ++i : 0, j++) {
            result[j] = array[i];
        }
        return result;
    }
}
