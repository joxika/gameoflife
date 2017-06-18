package gameoflife;

public class Game {

    public static int LIVING_CELL = 1;
    public static int DEAD_CELL = 0;

    int row;
    int column;
    int[][] multi;
    int[][] ujmulti;
    int szomszed;

    public Game(int row, int column) {
        multi = new int[row][column];
        ujmulti = new int[row][column];
        this.row = row;
        this.column = column;
    }

    public static void main(String[] args) {
        Game g = new Game(10, 10);
        g.setUp();
        g.print(g.multi);
        g.playTimes(3);
    }

    private void playTimes(int times)
    {
        for (int i = 0; i < times; i++) {
            play();
            print(ujmulti);
        }
    }

    private void setUp() {
        createBlinker(2, 3);
    }

    private void createBlinker(int x, int y) {
        multi[x][y] = 1;
        multi[x + 1][y] = 1;
        multi[x - 1][y] = 1;
    }


    private void play() {

        for (int currentRow = 0; currentRow < row; currentRow++) {

            for (int currentColumn = 0; currentColumn < column; currentColumn++) {
                szomszed = 0;
                checkSzomszedok(currentRow, currentColumn);

                if (szomszed < 2 || szomszed > 3) {
                    ujmulti[currentRow][currentColumn] = DEAD_CELL;
                }else if (szomszed == 2) {
                    ujmulti[currentRow][currentColumn] = multi[currentRow][currentColumn];
                }else if(szomszed == 3){
                    ujmulti[currentRow][currentColumn] = LIVING_CELL;
                }

            }

        }

        multi=ujmulti;

    }

    private void checkSzomszedok(int currentRow, int currentColumn) {
        checkSzomszed(currentRow + 1, currentColumn);
        checkSzomszed(currentRow - 1, currentColumn);
        checkSzomszed(currentRow, currentColumn - 1);
        checkSzomszed(currentRow, currentColumn + 1);
        checkSzomszed(currentRow + 1, currentColumn + 1);
        checkSzomszed(currentRow - 1, currentColumn + 1);
        checkSzomszed(currentRow + 1, currentColumn - 1);
        checkSzomszed(currentRow - 1, currentColumn - 1);
    }

    private void checkSzomszed(int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }
        if (i >= row || j >= column) {
            return;
        }


        if (multi[i][j] == 1) {
            szomszed++;
        }
    }

    private void print(int[][] multi) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (multi[i][j] == DEAD_CELL) {
                    System.out.print("_ ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();

        }
        System.out.println();
    }


}
