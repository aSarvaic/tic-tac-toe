
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void printLayout() { //print game layout
        char[][] ticTacToeGround = new char[3][3];
        int start = 1;
        System.out.println("Hello, numbers represent position in range(1-9): ");
        for (int i = 0; i < ticTacToeGround.length; i++) {
            for (int j = 0; j < ticTacToeGround[i].length; j++) {
                ticTacToeGround[i][j] = (char) (start + '0');
                start++;
                if (j == 1) {
                    System.out.print(" | " + ticTacToeGround[i][j] + " | ");
                } else {
                    System.out.print(ticTacToeGround[i][j]);
                }
            }
            System.out.println();
            if (i != 2) {
                System.out.println("---------");
            }
        }
    }

    public static boolean checkWinRow(Character[][] ticTacToeArray) {
        for (int i = 0; i < ticTacToeArray.length; i++) {
            if (ticTacToeArray[i][0].equals(ticTacToeArray[i][1]) && ticTacToeArray[i][0].equals(ticTacToeArray[i][2]) && !ticTacToeArray[i][0].equals(' ')) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkWinDiagonal(Character[][] ticTacToeArray) {
        if (ticTacToeArray[1][1].equals(ticTacToeArray[0][0]) && ticTacToeArray[0][0].equals(ticTacToeArray[2][2]) && !ticTacToeArray[1][1].equals(' ')) {
            return true;
        }
        if (ticTacToeArray[1][1].equals(ticTacToeArray[0][2]) && ticTacToeArray[1][1].equals(ticTacToeArray[2][0]) && !ticTacToeArray[1][1].equals(' ')) {
            return true;
        }
        return false;
    }

    public static boolean checkWinColumn(Character[][] ticTacToeArray) {
        for (int i = 0; i < ticTacToeArray.length; i++) {
            if (ticTacToeArray[0][i].equals(ticTacToeArray[1][i]) && ticTacToeArray[0][i].equals(ticTacToeArray[2][i]) && !ticTacToeArray[0][i].equals(' ')) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPositionTaken(int move, Character[][] ticTacToeArray) {
        int start = 1;
        for (int i = 0; i < ticTacToeArray.length; i++) {
            for (int j = 0; j < ticTacToeArray[i].length; j++) {
                if (move == start && !ticTacToeArray[i][j].equals(' ')) {
                    return true;
                }
                start++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        printLayout();

        Character[][] ticTacToeGround = new Character[3][3]; //fill array with empty spaces
        for (int i = 0; i < ticTacToeGround.length; i++) {
            for (int j = 0; j < ticTacToeGround[i].length; j++) {
                ticTacToeGround[i][j] = ' ';
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        boolean playerX = true;
        int moveCount = 1;
        boolean play = true;
        while (play) {
            try {
                char playerChar;
                if (playerX) {
                    playerChar = 'X';
                } else {
                    playerChar = 'O';
                }
                System.out.println("Player " + playerChar + " please insert your position(1-9)");
                int move = scanner.nextInt();
                if (move < 1 || move > 9) { //Overenie platneho cisla
                    System.out.println("Wrong number!");
                    continue;
                }
                if (checkPositionTaken(move, ticTacToeGround)) {
                    System.out.println("Position taken!");
                    continue;
                }
                int start = 1;
                for (int i = 0; i < ticTacToeGround.length; i++) {
                    for (int j = 0; j < ticTacToeGround[i].length; j++) {
                        if (move == start && ticTacToeGround[i][j].equals(' ')) {
                            ticTacToeGround[i][j] = playerChar;
                        }

                        start++;
                        if (j == 1) {
                            System.out.print(" | " + ticTacToeGround[i][j] + " | ");
                        } else {
                            System.out.print(ticTacToeGround[i][j]);
                        }

                    }
                    System.out.println();
                    if (i != 2) {
                        System.out.println("---------");
                    }
                }
                moveCount++;
                if (moveCount % 2 == 0) { //move count and switching player
                    playerX = false;
                } else {
                    playerX = true;
                }

                if (checkWinRow(ticTacToeGround) || checkWinDiagonal(ticTacToeGround) || checkWinColumn(ticTacToeGround)) {
                    System.out.println("Player " + playerChar + " win!"); //checking win in every direction
                    play = false;
                }

                if (moveCount == 10) { //check if game is a tie
                    System.out.println("It's a tie!");
                    play = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again: ");
                scanner.nextLine();
            }
        }

    }


}