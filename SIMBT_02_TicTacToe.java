import java.util.Scanner;
import java.util.Random;

class GameBoard {
    public static char[][] board;

    public GameBoard() {
        board = new char[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    static public void placeMark(int row, int col, char mark) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3)
            board[row][col] = mark;
        else
            System.out.println("Invalid");
    }

    static boolean checkColWin() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }

        }
        return false;

    }

    static boolean checkRowWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }

        }
        return false;

    }

    static boolean checkDigWin() {
        for (int i = 0; i < 3; i++) {
            if ((board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2])
                    || (board[2][0] != ' ' && board[2][0] == board[1][1] && board[1][1] == board[2][0])) {
                return true;
            }

        }
        return false;

    }

    static void instruction() {
        System.out.println("-----------INSTRUCTIONS-----------");
        System.out.println("The row and coloumn start from 0 and 0");
        System.out.println("You have to enter row and column in which you wish to mark ");
        System.out.println("First Player - X");
        System.out.println("Second Player - O");

    }
}

abstract class Player {
    String name;
    char mark;
    static int count;

    abstract public void makeMove();

    public boolean isValid(int row, int col) {

        if (row >= 0 && row < 3 && col >= 0 && col < 3) {

            if (GameBoard.board[row][col] == ' ') {
                count++;
                return true;
            }
        }
        return false;
    }
}

class HumanPlayer extends Player {
    HumanPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public void makeMove() {
        Scanner scan = new Scanner(System.in);
        int row;
        int col;
        do {
            System.out.println("Enter correct row and coloumn");
            row = scan.nextInt();
            col = scan.nextInt();
        } while (!isValid(row, col));
        GameBoard.placeMark(row, col, mark);
    }
}

class AiPlayer extends Player {
    AiPlayer(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public void makeMove() {
        int row;
        int col;
        do {
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while (!isValid(row, col));
        GameBoard.placeMark(row, col, mark);

    }
}

public class SIMBT_02_TicTacToe {
    public static void main(String[] args) {
        GameBoard g = new GameBoard();
        GameBoard.instruction();
        System.out.println("Wanted to play with bot : say - Y or N");
        Scanner sc = new Scanner(System.in);
        char s = sc.next().charAt(0);
        if (s == 'Y') {
            System.out.println("Hello");
            HumanPlayer p1 = new HumanPlayer("John", 'X');
            Player p2 = new AiPlayer("Bot", 'O');
            Player cp = p1;
            while (true) {
                System.out.println(cp.name + " 's turn");
                cp.makeMove();
                GameBoard.printBoard();
                if (Player.count == 9) {
                    System.out.println("Tie");
                    break;
                }

                else if (GameBoard.checkColWin() || GameBoard.checkDigWin() || GameBoard.checkRowWin()) {
                    System.out.println(cp.name + " has won");
                    break;
                } else {
                    if (cp == p1) {
                        cp = p2;
                    } else {
                        cp = p1;
                    }
                }

            }
        } else if (s == 'N') {

            HumanPlayer p1 = new HumanPlayer("Bob", 'X');
            HumanPlayer p2 = new HumanPlayer("John", 'O');
            HumanPlayer cp = p1;
            while (true) {
                System.out.println(cp.name + " 's turn");
                cp.makeMove();
                GameBoard.printBoard();
                if (Player.count == 9) {
                    System.out.println("Tie");
                    break;
                } else if (GameBoard.checkColWin() || GameBoard.checkDigWin() || GameBoard.checkRowWin()) {
                    System.out.println(cp.name + " has won");
                    break;
                } else {
                    if (cp == p1) {
                        cp = p2;
                    } else {
                        cp = p1;
                    }
                }

            }

        } else {
            System.out.println("Please enter valid choice");
        }
    }
}
