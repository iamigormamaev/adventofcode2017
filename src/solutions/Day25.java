package solutions;

import java.util.HashMap;
import java.util.Map;


public class Day25 {

    public void firstPart(String[] input) {
        Map<Character, State> states = parseStates(input);
        Character state = parseStartState(input);
        Long stepsCount = parseStepsCount(input);
        int currentPosition = 0;
        Map<Integer, Integer> tape = new HashMap<>();
        tape.put(0, 0);
        for (long i = 0; i < stepsCount; i++) {
            Instructions instructions = states.get(state).getInstr(tape.get(currentPosition));
            tape.put(currentPosition, instructions.writeValue);
            if (!tape.containsKey(currentPosition + instructions.direction)) {
                tape.put(currentPosition + instructions.direction, 0);
            }
            currentPosition += instructions.direction;
            state = instructions.nextState;
        }
        int result = 0;
        for (Map.Entry<Integer, Integer> pair :
                tape.entrySet()) {
            result += pair.getValue();
        }
        System.out.println(result);
    }

/*    private Character processState(Map<Character, State> states, Character state, Map<Integer, Integer> tape, int currentPosition, int step) {
        Instructions instructions = states.get(state).getInstr(tape.get(currentPosition));
        tape.put(currentPosition, instructions.writeValue);
        if (!tape.containsKey(currentPosition + instructions.direction)) {
            tape.put(currentPosition + instructions.direction, 0);
        }
        currentPosition += instructions.direction;
        return instructions.nextState;
    }*/

    private Long parseStepsCount(String[] input) {
        String s = input[1].replaceAll("Perform a diagnostic checksum after | steps.", "");
        return Long.parseLong(s);
    }

    private Character parseStartState(String[] input) {
        String s = input[0].replaceAll("Begin in state ", "");
        return s.charAt(0);
    }

    private Map<Character, State> parseStates(String[] input) {
        Map<Character, State> result = new HashMap<>();
        StringBuilder fullString = new StringBuilder();
        for (int i = 2; i < input.length; i++) {
            fullString.append(input[i]);
        }
        String cleanInput = fullString.toString().replaceAll("\\.*In state |[:.]  If the current value is [10]|:    - Write the value |.    - Move one slot to the |.    - Continue with state |\\.", " ");
        String[] inst = cleanInput.trim().split("\\s+");
        for (int i = 0; i < inst.length; i += 7) {
            result.put(inst[i].charAt(0), new State(inst[i].charAt(0), new Instructions(Integer.parseInt(inst[i + 1]), inst[i + 2], inst[i + 3].charAt(0)),
                    new Instructions(Integer.parseInt(inst[i + 4]), inst[i + 5], inst[i + 6].charAt(0))));
        }
        return result;
    }

    private class State {
        private Character stateName;
        Instructions zero;
        Instructions one;

        public State(Character stateName, Instructions zero, Instructions one) {
            this.stateName = stateName;
            this.zero = zero;
            this.one = one;
        }

        public Instructions getInstr(int i) {
            if (i == 0) return zero;
            return one;
        }

        public Character getStateName() {
            return stateName;
        }

        public Instructions getZero() {
            return zero;
        }

        public Instructions getOne() {
            return one;
        }

        @Override
        public String toString() {
            return "State{" +
                    "stateName=" + stateName +
                    ", zero=" + zero +
                    ", one=" + one +
                    '}';
        }
    }

    private class Instructions {
        private int writeValue;
        private int direction;
        private Character nextState;

        public Instructions(int writeValue, String directionText, Character nextState) {
            this.writeValue = writeValue;
            switch (directionText) {
                case "left":
                    direction = -1;
                    break;
                case "right":
                    direction = 1;
            }
            this.nextState = nextState;
        }

        public int getWriteValue() {
            return writeValue;
        }

        public int getDirection() {
            return direction;
        }

        public Character getNextState() {
            return nextState;
        }

        @Override
        public String toString() {
            return "Instructions{" +
                    "writeValue=" + writeValue +
                    ", direction=" + direction +
                    ", nextState=" + nextState +
                    '}';
        }
    }
}
