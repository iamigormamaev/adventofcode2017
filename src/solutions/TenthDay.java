package solutions;

import java.util.Arrays;


public class TenthDay {
    public void firstPart(int[] inputArray) {
        int[] list = new int[256];
        for (int i = 0; i < 256; i++) {
            list[i] = i;
        }

        int currentPosition = 0;
        int skipSize = 0;
        for (int i :
                inputArray) {
            int[] arrayToReverse = roundCopyOfRange(list, currentPosition, i);
            arrayToReverse = reverse(arrayToReverse);
            list = roundReplaceArrayPart(list, arrayToReverse, currentPosition);
            currentPosition = roundChangeCurrentPosition(currentPosition, list.length, i, skipSize);
            skipSize++;
            System.out.println(Arrays.toString(list));
        }


        System.out.println(list[0] * list[1]);
    }

    private int roundChangeCurrentPosition(int currentPosition, int arrayLength, int currentLength, int skipSize) {
        return (currentPosition + currentLength + skipSize) < arrayLength ? currentPosition + currentLength + skipSize :
                currentPosition + currentLength + skipSize - arrayLength;
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
        for (int i = from, j = 0; j < size; i = i < array.length-1 ? ++i : 0, j++) {
            result[j] = array[i];
        }
        return result;
    }
}
