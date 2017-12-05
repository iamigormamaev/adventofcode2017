import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {

    public int[][] getTwoDimensionalArrayFromConsole() {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        List<String> strings = new ArrayList<>();
        String s = in.nextLine();

        while (!s.isEmpty()) {
            strings.add(s);
            s = in.nextLine();
            System.out.println(s);
        }

        int[][] resultArray = new int[strings.size()][];
        for (int i = 0; i < strings.size(); i++) {
            String[] tempArray = strings.get(i).split("\\s");
            resultArray[i] = new int[tempArray.length];
            for (int j = 0; j < tempArray.length; j++) {
                resultArray[i][j] = Integer.parseInt(tempArray[j]);
            }
        }
        in.close();
        return resultArray;
    }

    public int[] getOneDimensionalArrayFromConsole() {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        String inputString = in.nextLine();
        int[] resultArray = new int[inputString.length()];
        int i = 0;
        for (char c : inputString.toCharArray()) {
            resultArray[i] = Character.getNumericValue(c);
            i++;
        }
        in.close();
        return resultArray;
    }

    public String[] getOneDimensionalStringArrayFromConsole() {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        List<String> strings = new ArrayList<>();

        String s = in.nextLine();

        while (!s.isEmpty()) {
            strings.add(s);
            s = in.nextLine();
        }

        in.close();

        return strings.toArray(new String[strings.size()]);
    }

    public int[] getOneDimensionalIntArrayFromConsole() {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        List<Integer> integerList = new ArrayList<>();

        String s = in.nextLine();

        while (!s.isEmpty()) {
            integerList.add(Integer.parseInt(s));
            s = in.nextLine();
        }

        in.close();
        return integerList.stream().mapToInt(i -> i).toArray();
    }
}
