package solutions;

import java.util.HashMap;
import java.util.Map;

public class Day3 {
    private boolean isX = true;

    public void firstPart(int input) {
        int x = 0;
        int y = 0;
        int step = 1;

        for (int i = 2; i <= input; ) {
            for (int m = 0; m < 2; m++) {
                for (int j = 0; j < step; j++) {

                    if (isX) {
                        if (!(step % 2 == 0)) x++;
                        else x--;
                    } else {
                        if (!(step % 2 == 0)) y++;
                        else y--;
                    }
                    i++;
                    if (i > input) break;
                }
                if (i > input) break;
                changeX();
            }
            step++;
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    public void secondPart(int input) {
        Map<Coordinate, Integer> map = new HashMap<>();
        map.put(new Coordinate(0, 0), 1);
        int x = 0;
        int y = 0;
        int step = 1;
        int value = 0;
        while (value <= input) {

            for (int m = 0; m < 2; m++) {
                for (int j = 0; j < step; j++) {
                    if (isX) {
                        if (!(step % 2 == 0)) x++;
                        else x--;
                    } else {
                        if (!(step % 2 == 0)) y++;
                        else y--;
                    }

                    Coordinate currentCoordinate = new Coordinate(x, y);
                    value = 0;
                    for (int k = x - 1; k <= x + 1; k++) {
                        for (int l = y - 1; l <= y + 1; l++) {
                            Integer findValue = map.get(new Coordinate(k, l));
                            if (findValue != null) value = value + findValue;
                        }
                    }
                    map.put(currentCoordinate, value);

                    if (value > input) break;
                }
                changeX();
                if (value > input) break;
            }
            step++;
        }

        System.out.println(value);
    }

    private void changeX() {
        isX = !isX;
    }


    public class Coordinate {
        public int x;
        public int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }



}
