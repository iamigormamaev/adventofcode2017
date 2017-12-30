package solutions;

public class Day15 {
    public void firstPart(int[] input) {
        Generator generatorA = new Generator(input[0], 16807);
        Generator generatorB = new Generator(input[1], 48271);
        int result = getSolution(generatorA, generatorB, false, 40_000_000);
        System.out.println(result);
    }

    public void secondPart(int[] input) {
        Generator generatorA = new Generator(input[0], 16807);
        generatorA.setCriteria(4);
        Generator generatorB = new Generator(input[1], 48271);
        generatorB.setCriteria(8);
        int result = getSolution(generatorA, generatorB, true, 5_000_000);
        System.out.println(result);
    }

    private int getSolution(Generator generatorA, Generator generatorB, boolean useCriteria, int cycleCount) {
        int result = 0;
        for (int i = 0; i < cycleCount; i++) {
            if (generatorA.generateNext16bits(useCriteria).equals(generatorB.generateNext16bits(useCriteria))) {
                result++;
            }
        }
        return result;
    }

    public class Generator {

        long previousValue;
        int factor;
        int criteria;

        public Generator(long startingValue, int factor) {
            this.previousValue = startingValue;
            this.factor = factor;
        }

        public void setCriteria(int criteria) {
            this.criteria = criteria;
        }

        private void generateNext() {
            previousValue = (previousValue * factor) % 2147483647L;
        }

        private void generateNextWithCriteria() {
            previousValue = (previousValue * factor) % 2147483647L;
            while (previousValue % criteria != 0) {
                previousValue = (previousValue * factor) % 2147483647L;
            }
        }

        public String generateNext16bits(boolean useCriteria) {
            if (useCriteria) {
                generateNextWithCriteria();
            } else {
                generateNext();
            }
            String binString = Long.toBinaryString(previousValue);
            binString = ("0000000000000000" + binString).substring(binString.length());
            return binString.substring(binString.length() - 16);
        }


    }
}
