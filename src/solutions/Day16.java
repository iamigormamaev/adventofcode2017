package solutions;

import java.util.ArrayList;
import java.util.List;

public class Day16 {
    public void firstPart(String input) {
        String[] commands = input.split(",");
        List<Character> programs = initPrograms();
        programs = doCommands(programs, commands, 1);
        System.out.println(programs);
    }

    public void secondPart(String input) {
        String[] commands = input.split(",");
        List<Character> programs = initPrograms();
        int cycleLoopCount = findCycleLoopCount(programs, commands);
        programs = doCommands(programs, commands, 1000000000 % cycleLoopCount);
        System.out.println(programs);
    }

    private int findCycleLoopCount(List<Character> programs, String[] commands) {
        List<Character> programsCopyForWork = new ArrayList<>(programs);
        List<Character> programsCopyOriginal = new ArrayList<>(programs);

        doCommands(programsCopyForWork, commands, 1);
        int cycleLoopCount = 1;
        while (!programsCopyOriginal.equals(programsCopyForWork)) {
            doCommands(programsCopyForWork, commands, 1);
            cycleLoopCount++;
        }
        return cycleLoopCount;
    }

    private List<Character> doCommands(List<Character> programs, String[] commands, int cycleCount) {
        for (int i = 0; i < cycleCount; i++) {
            for (String command : commands) {
                switch (command.toCharArray()[0]) {
                    case 's':
                        spin(programs, Integer.parseInt(command.substring(1)));
                        break;
                    case 'x':
                        String[] positions = command.substring(1).split("/");
                        exchange(programs, Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
                        break;
                    case 'p':
                        String[] programsNames = command.substring(1).split("/");
                        partner(programs, programsNames[0], programsNames[1]);
                        break;
                }
            }

        }
        return programs;
    }


    private void partner(List<Character> programs, String programName1, String programName2) {
        int index1 = programs.indexOf(programName1.charAt(0));
        int index2 = programs.indexOf(programName2.charAt(0));
        programs.set(index1, programName2.charAt(0));
        programs.set(index2, programName1.charAt(0));
    }

    private void exchange(List<Character> programs, int position1, int position2) {
        char tempChar = programs.get(position1);
        programs.set(position1, programs.get(position2));
        programs.set(position2, tempChar);
    }

    private void spin(List<Character> programs, int number) {
        List<Character> temp = new ArrayList<>(programs.subList(programs.size() - number, programs.size()));
        programs.removeAll(temp);
        programs.addAll(0, temp);
    }


    private List<Character> initPrograms() {
        List<Character> result = new ArrayList<>();
        result.add('a');
        result.add('b');
        result.add('c');
        result.add('d');
        result.add('e');
        result.add('f');
        result.add('g');
        result.add('h');
        result.add('i');
        result.add('j');
        result.add('k');
        result.add('l');
        result.add('m');
        result.add('n');
        result.add('o');
        result.add('p');
        return result;
    }
}
