package solutions;

import java.util.*;

public class SeventhDay {
    private Map<String, TreePart> haveChildrenMap = new HashMap<>();
    private Map<String, TreePart> allTreePartsMap = new HashMap<>();


    public void firstPart(String[] inputArray) {
        fillTree(inputArray);
        System.out.println(getHeadOfTreeName());
    }

    public void secondPart(String[] inputArray) {
        fillTree(inputArray);
        findBadPart(getHeadOfTreeName());
    }

    private boolean findBadPart(String treePartName) {
        Map<Integer, Integer> branchWeightCounters = new HashMap<>();
        Map<Integer, String> branchWeightToTreePartNameMap = new HashMap<>();
        int normalWeightCount = 0;
        if (allTreePartsMap.get(treePartName).isHaveChildren()) {
            for (String s :
                    allTreePartsMap.get(treePartName).getChildren()) {
                int weight = calculateWeight(s);

                if (branchWeightCounters.containsKey(weight)) {
                    normalWeightCount = branchWeightCounters.get(weight) + 1;
                    branchWeightCounters.put(weight, normalWeightCount);
                } else {
                    branchWeightCounters.put(weight, 1);
                }
                branchWeightToTreePartNameMap.put(weight, s);
            }
            int badWeight = 0;
            if (branchWeightCounters.containsValue(1)) {

                for (Map.Entry<Integer, Integer> pair : branchWeightCounters.entrySet()) {
                    if (pair.getValue() == 1) {
                        badWeight = pair.getKey();
                        break;
                    }
                }


                if (findBadPart(branchWeightToTreePartNameMap.get(badWeight))) {

                    int badBranchWeight = 0;
                    int normalBranchWeight = 0;

                    for (String s :
                            allTreePartsMap.get(branchWeightToTreePartNameMap.get(badWeight)).getChildren()) {
                        badBranchWeight += calculateWeight(s);
                    }

                    for (Map.Entry<Integer, Integer> pair : branchWeightCounters.entrySet()) {
                        if (pair.getValue() == normalWeightCount) {
                            normalBranchWeight = pair.getKey();
                            break;
                        }
                    }

                    System.out.println(badWeight - badBranchWeight - (badWeight - normalBranchWeight));
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    private int calculateWeight(String treePartName) {
        int weightSum = allTreePartsMap.get(treePartName).getWeight();
        if (allTreePartsMap.get(treePartName).haveChildren) {
            for (String s : allTreePartsMap.get(treePartName).getChildren()) {
                weightSum += calculateWeight(s);
            }
        }
        return weightSum;
    }

    private void fillTree(String[] inputArray) {

        for (String s :
                inputArray) {
            TreePart treePart = parseTreePart(s);
            if (treePart.haveChildren) {
                haveChildrenMap.put(treePart.getName(), treePart);
            }
            allTreePartsMap.put(treePart.getName(), treePart);

        }
    }

    private String getHeadOfTreeName() {

        Set<String> haveChildrenNamesSet = haveChildrenMap.keySet();
        Set<String> allChildrenSet = new HashSet<>();

        for (Map.Entry<String, TreePart> pair : haveChildrenMap.entrySet()) {
            allChildrenSet.addAll(pair.getValue().getChildren());
        }

        haveChildrenNamesSet.removeAll(allChildrenSet);
        return new ArrayList<>(haveChildrenNamesSet).get(0);
    }


    public TreePart parseTreePart(String string) {
        String[] parts = string.split("\\) -> |\\s\\(|\\s|\\,\\s|\\)");

        List<String> children = new ArrayList<>();
        if (parts.length > 2) {
            children = Arrays.asList(Arrays.copyOfRange(parts, 2, parts.length));
        }
        TreePart treePart = new TreePart(parts[0], Integer.parseInt(parts[1]), children);


        return treePart;
    }

    public class TreePart {
        String name;
        int weight;
        List<String> children;
        boolean haveChildren;

        public TreePart(String name, int weight, List<String> children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
            haveChildren = !children.isEmpty();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        public boolean isHaveChildren() {
            return haveChildren;
        }

        public void setHaveChildren(boolean haveChildren) {
            this.haveChildren = haveChildren;
        }

        @Override
        public String toString() {
            return "TreePart{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", children=" + children +
                    ", haveChildren=" + haveChildren +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeventhDay that = (SeventhDay) o;

        if (haveChildrenMap != null ? !haveChildrenMap.equals(that.haveChildrenMap) : that.haveChildrenMap != null)
            return false;
        return allTreePartsMap != null ? allTreePartsMap.equals(that.allTreePartsMap) : that.allTreePartsMap == null;
    }

    @Override
    public int hashCode() {
        int result = haveChildrenMap != null ? haveChildrenMap.hashCode() : 0;
        result = 31 * result + (allTreePartsMap != null ? allTreePartsMap.hashCode() : 0);
        return result;
    }
}
