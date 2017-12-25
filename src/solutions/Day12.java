package solutions;

import java.util.ArrayList;
import java.util.List;

public class Day12 {

    public void firstPart(String[] input) {
        List<List<Integer>> graph = parseStringInputToGraph(input);
        int result = 1;
        for (int i = 1; i < graph.size(); i++) {
            List<Integer> processedSteps = new ArrayList<>();
            processedSteps.add(i);
            if (findZero(graph, i, processedSteps)) result++;
        }
        System.out.println(result);
    }

    private boolean findZero(List<List<Integer>> graph, int step, List<Integer> processedSteps) {
        boolean result = false;
        for (int i = 0; i < graph.get(step).size(); i++) {
            processedSteps.add(step);
            if (graph.get(step).get(i) == 0) {
                result = true;
                break;
            }
            if (!processedSteps.contains(graph.get(step).get(i))) {
                result = findZero(graph, graph.get(step).get(i), processedSteps);
            }
            if (result) break;
        }
        return result;
    }

    public void secondPart(String[] input) {
        List<List<Integer>> graph = parseStringInputToGraph(input);
        List<Integer> programs = initPrograms(graph.size());
        int result = 0;
        for (int i = 0; i < graph.size(); i++) {
            if (programs.contains(i)) {
                List<Integer> processedSteps = new ArrayList<>();
                processedSteps.add(i);
                findAllChildrenAndDeleteThemFromPrograms(graph, i, processedSteps, programs);
                result++;
            }
        }
        System.out.println(result);
    }

    private List<Integer> initPrograms(int size) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    private void findAllChildrenAndDeleteThemFromPrograms(List<List<Integer>> graph, int step, List<Integer> processedSteps, List<Integer> programs) {
        for (int i = 0; i < graph.get(step).size(); i++) {
            processedSteps.add(step);
            programs.remove((new Integer(step)));

            if (!processedSteps.contains(graph.get(step).get(i))) {
                findAllChildrenAndDeleteThemFromPrograms(graph, graph.get(step).get(i), processedSteps, programs);
                programs.remove(graph.get(step).get(i));
            }
        }
    }

    private List<List<Integer>> parseStringInputToGraph(String[] input) {
        List<List<Integer>> result = new ArrayList<>();
        for (String s :
                input) {
            String[] parts = s.split(" <-> |, ");
            List<Integer> tempList = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                tempList.add(Integer.parseInt(parts[i]));
            }
            result.add(tempList);
        }
        return result;
    }
}
