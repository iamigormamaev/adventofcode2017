package solutions;

import java.util.ArrayList;
import java.util.List;

public class Day24 {
    private int maxSize = Integer.MIN_VALUE;
    private int maxLength = 0;

    public void firstPart(String[] input) {
        solution(input, false);
        System.out.println(maxSize);
    }

    public void secondPart(String[] input) {
        solution(input, true);
        System.out.println(maxSize);
    }

    private void solution(String[] input, boolean considerLength) {
        List<Component> components = parseComponentsFromString(input);
        List<Component> starters = getStarters(components);

        for (Component c :
                starters) {
            List<Component> componentsCopy = new ArrayList<>(components);
            componentsCopy.remove(c);
            c.blockPort(0);
            searchMaxLength(componentsCopy, c, c.getSize(), 1, considerLength);
        }
    }

    private void searchMaxLength(List<Component> components, Component currentComponent, int size, int step, boolean considerLength) {
        boolean hasNext = false;

        for (Component c :
                components) {
            if (c.hasPort(currentComponent.getFreePort())) {

                hasNext = true;
                List<Component> componentsCopy = new ArrayList<>(components);
                componentsCopy.remove(c);
                c.blockPort(currentComponent.getFreePort());
                searchMaxLength(componentsCopy, c, size + c.getSize(), ++step, considerLength);
            }
        }

        if (!hasNext) {
            if (considerLength) {
                if (step > maxLength) {
                    maxSize = size;
                    maxLength = step;
                } else if (step == maxLength) {
                    if (size > maxSize) {
                        maxSize = size;
                    }
                }
            } else {
                if (size > maxSize) {
                    maxSize = size;
                }
            }
        }
    }

    private List<Component> parseComponentsFromString(String[] strings) {
        List<Component> result = new ArrayList<>();
        for (String s :
                strings) {
            String[] ports = s.split("/");
            result.add(new Component(Integer.parseInt(ports[0]), Integer.parseInt(ports[1])));
        }
        return result;
    }

    private List<Component> getStarters(List<Component> components) {
        List<Component> result = new ArrayList<>();
        for (Component c :
                components) {
            if (c.hasPort(0)) {
                result.add(c);
            }
        }
        return result;
    }

    private class Component {
        private int port1;
        private int port2;
        private int freePort;


        public boolean hasPort(int port) {
            return port1 == port || port2 == port;
        }

        public Component(int port1, int port2) {
            this.port1 = port1;
            this.port2 = port2;
        }

        public int getSize() {
            return port1 + port2;
        }

        public void blockPort(int port) {
            if (port1 == port) {
                freePort = port2;
            } else {
                freePort = port1;
            }
        }

        public int getFreePort() {

            return freePort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Component component = (Component) o;

            if (port1 != component.port1) return false;
            return port2 == component.port2;
        }

        @Override
        public int hashCode() {
            int result = port1;
            result = 31 * result + port2;
            return result;
        }
    }


}
