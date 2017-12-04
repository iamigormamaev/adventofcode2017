package solutions.thirdday;

import java.util.HashMap;
import java.util.Map;

public class ThirdDaySecondPart {
    private boolean isX = true;

    public void solution(int input) {
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


}
