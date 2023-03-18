import java.util.Scanner;
import java.util.Random;

public class Hangman2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Start");
        String[] word = { "january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december" };
        Random r = new Random();
        int ind = r.nextInt(13);
        String original_month = word[ind];
        int len = original_month.length();
        System.out.println("The word has " + len + " characters");
        char[] letters;
        letters = new char[len];
        for (int i = 0; i < len; i++) {
            letters[i] = '.';
        }
        int lives = 3;
        while (lives > 0) {
            System.out.print("Lives:");
            for (int i = 0; i < lives; i++) {
                System.out.print("o");
            }

            System.out.println();
            System.out.print("Input: ");
            String input = sc.nextLine();
            char letter = input.charAt(0);
          
         
            if (!guessLetter(original_month, letter, letters, len)) {
                lives--;
            }
            System.out.println("__________________________________");
            if (checkWin(len, letters)) {
                System.out.println("You Won!");
                break;
            }
        }
        if (lives == 0) {
            System.out.println("You Lost!" + "The word was :" + original_month);
        }
        System.out.println("Exit");
    }
    static boolean guessLetter(String original_month,char letter,char[] letters,int len){
        boolean isGuessCorrect = false;
        for (int i = 0; i < len; i++) {
            char l = original_month.charAt(i);
            if (l == letter) {
                letters[i] = l;
                isGuessCorrect = true;
                
            }
        }
        return isGuessCorrect;
    }
    static boolean checkWin(int len,char[] letters){
        boolean isGamefinished = true;
        System.out.print("Words :");
        for (int i = 0; i < len; i++) {
            if (letters[i] == '.') {
                isGamefinished = false;
            }
            System.out.print(letters[i]);
        }
        System.out.println();
        return isGamefinished;
    }
}
