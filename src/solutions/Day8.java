package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {

    int maxEver = Integer.MIN_VALUE;


    public void firstPart(String[] inputArray) {
        Map<String, Integer> stepsRegisters = solution(inputArray);
        int max = Integer.MIN_VALUE;
        for (int i :
                stepsRegisters.values()) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }

    public void secondPart(String[] inputArray) {
        solution(inputArray);
        System.out.println(maxEver);

    }

    private Map<String, Integer> solution(String[] inputArray) {
        List<Step> allSteps = new ArrayList<>();
        for (String s :
                inputArray) {
            allSteps.add(parseStep(s));
        }
        Map<String, Integer> stepsRegisters = initStepsRegistersFromStepsList(allSteps);
        for (Step step :
                allSteps) {
            processStep(step, stepsRegisters);
        }
        return stepsRegisters;
    }

    private void processStep(Step step, Map<String, Integer> registers) {
        boolean needToProcess = false;
        switch (step.conditionSign) {
            case ">":
                needToProcess = registers.get(step.conditionRegister) > step.conditionValue;
                break;
            case "<":
                needToProcess = registers.get(step.conditionRegister) < step.conditionValue;
                break;
            case "==":
                needToProcess = registers.get(step.conditionRegister) == step.conditionValue;
                break;
            case ">=":
                needToProcess = registers.get(step.conditionRegister) >= step.conditionValue;
                break;
            case "<=":
                needToProcess = registers.get(step.conditionRegister) <= step.conditionValue;
                break;
            case "!=":
                needToProcess = registers.get(step.conditionRegister) != step.conditionValue;
                break;
        }
        if (needToProcess) {
            int tempValue = 0;
            switch (step.operation) {
                case "inc":
                    tempValue = registers.get(step.register) + step.value;
                    registers.put(step.register, tempValue);
                    if (tempValue > maxEver) {
                        maxEver = tempValue;
                    }
                    break;
                case "dec":
                    tempValue = registers.get(step.register) - step.value;
                    registers.put(step.register, tempValue);
                    if (tempValue > maxEver) {
                        maxEver = tempValue;
                    }
                    break;
            }
        }
//        System.out.println(step);
//        System.out.println(registers);
    }

    private Map<String, Integer> initStepsRegistersFromStepsList(List<Step> stepsList) {
        Map<String, Integer> stepsRegisters = new HashMap<>();
        for (Step step :
                stepsList) {
            stepsRegisters.put(step.register, 0);
        }
        return stepsRegisters;
    }


    public Step parseStep(String s) {
        String[] stepParts = s.split("\\s");
        return new Step(stepParts[0], stepParts[1], Integer.parseInt(stepParts[2]), stepParts[4], stepParts[5], Integer.parseInt(stepParts[6]));
    }

    public class Step {
        String register;
        String operation;
        int value;
        String conditionRegister;
        String conditionSign;
        int conditionValue;

        public Step(String register, String operation, int value, String conditionRegister, String conditionSign, int conditionValue) {
            this.register = register;
            this.operation = operation;
            this.value = value;
            this.conditionRegister = conditionRegister;
            this.conditionSign = conditionSign;
            this.conditionValue = conditionValue;
        }

        @Override
        public String toString() {
            return "Step{" +
                    "register='" + register + '\'' +
                    ", operation='" + operation + '\'' +
                    ", value=" + value +
                    ", conditionRegister='" + conditionRegister + '\'' +
                    ", conditionSign='" + conditionSign + '\'' +
                    ", conditionValue=" + conditionValue +
                    '}';
        }
    }
}
