package solutions;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9 {


    public void firstPart(String inputString) {

        inputString = deleteCanceledCharacters(inputString);
        inputString = inputString.replace(",", "");
        String inputStringCopy = inputString;

        Pattern pattern = Pattern.compile("(<.*?>)");
        Matcher matcher = pattern.matcher(inputString);
        inputStringCopy = matcher.replaceAll("");

        Character[] chars = inputStringCopy.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Stack<Character> stack = new Stack<>();

        int count = 0;

        for (Character c :
                chars) {
            if (c == '{') {
                stack.push(c);
            } else if (c == '}') {
                count += stack.size();
                stack.pop();
            }
        }
        System.out.println(count);
    }

    public void secondPart(String inputString) {
        inputString = deleteCanceledCharacters(inputString);

        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(inputString);
        int count = 0;
        while (matcher.find()) {
            count += matcher.group(1).length();
        }
        System.out.println(count);
    }

    public String deleteCanceledCharacters(String s) {
        s = s.replace("!!", "");
        Pattern pattern = Pattern.compile("!.");
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll("");
    }
}
