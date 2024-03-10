public class GameManager {
    static public int COLUMNS = 7;
    static public int ROWS = 6;

    // Maybe use decriptive static values in future
    static public int FREI;
    static public int BELEGT_S1;
    static public int BELEGT_S2;

    public int spielerNr;

    private int[][] spielfeld;

    GameManager() {
        spielerNr = 1;
        spielfeld = new int[COLUMNS][ROWS];
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                spielfeld[i][j] = 0;
            }
        }
    }

    public void place(int x, int y) {
        boolean richtigPlaziert = false;
        if (spielfeld[x][y] == 1 || spielfeld[x][y] == 2) return;
        for (int i = y; i < ROWS; i++) {
            if (i != 0) spielfeld[x][i - 1] = 0;

            spielfeld[x][i] = spielerNr;

            if (i != ROWS - 1 && spielfeld[x][i + 1] != 0) break;

        }
        if (hasWon()) System.out.println("Spieler " + spielerNr + "hat gewonnen!");
        changePlayer();
    }

    public int getStatus(int x, int y) {
        return spielfeld[x][y];
    }

    public void changePlayer() {
        switch (spielerNr) {
            case 1:
                spielerNr = 2;
                break;
            case 2:
                spielerNr = 1;
                break;
        }
    }

    private boolean hasWon() {
        return anyTrue(hasHorizontal(), hasVertical());
    }

    private boolean hasHorizontal() {
        int streak;
        for (int i = 0; i < ROWS; i++) {
            streak = 0;
            for (int j = 0; j < COLUMNS; j++) {
                if (spielfeld[j][i] == spielerNr) streak++;
                else if (streak >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasVertical() {
        int streak;
        for (int i = 0; i < COLUMNS; i++) {
            streak = 0;
            for (int j = 0; j < ROWS; j++) {
                if (spielfeld[i][j] == spielerNr) streak++;
                else if (streak >= 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean anyTrue(boolean... werte) {
        for (boolean wert : werte) {
            if (wert) {
                return true;
            }
        }
        return false;
    }
}
