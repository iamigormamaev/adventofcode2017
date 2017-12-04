package solutions.thirdday;

public class ThirdDayFirstPart {
    private boolean isX = true;

    public void solution(int input) {
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

    private void changeX() {
        isX = !isX;
    }




}
