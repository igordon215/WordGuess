package com.github.zipcodewilmington;
import java.util.Scanner;

/**
 * @author xt0fer
 * @version 1.0.0
 * @date 5/27/21 11:02 AM
 */
//Things we will probably need...
// Constants= Words to be guessed and # of tries
// Instance variables we will need, secret word, current guess, remaining tries, scanner...
// Methods we need to start game, control flow yes/no, new game, single letter, whole word
public class Hangman {
    // Constants = no change
    private static final String[] WORDS = {"ZIPCODE", "JAVA", "CODING", "EAGLES", "PHILLIES"};
    private static final int MAX_TRIES = 7;
    // Instance Variables
    private String secretWord;
    private StringBuilder currentGuess;
    private int remainingTries;
    private Scanner scanner;
    // Constructor init scanner
    public Hangman() {
        scanner = new Scanner(System.in);
    }
    // Main Method
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        hangman.play();
    }
    // Method for playing the game
    public void play() {
        boolean playAgain = true;
        while (playAgain) {
            startGame();
            System.out.println("Do you want to play again? (yes/no)");
            String playChoice = scanner.nextLine().trim().toLowerCase();
            playAgain = playChoice.equals("yes") || playChoice.equals("y");
        }
        System.out.println("Thanks for Playing!");
        scanner.close();
    }
    // Method to start the game
    private void startGame() {
        secretWord = WORDS[(int) (Math.random() * WORDS.length)];
        currentGuess = new StringBuilder("-".repeat(secretWord.length()));
        remainingTries = MAX_TRIES;
        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word: " + currentGuess);
        // current guess index ---- & letter guess
        while (remainingTries > 0 && currentGuess.indexOf("-") != -1) {
            System.out.println("Enter a letter or guess the word:");
            String guess = scanner.nextLine().toUpperCase().trim();
            if (guess.length() == 1) {
                handleLetterGuess(guess.charAt(0));
            }else if (guess.length() > 1) {
                handleWordGuess(guess);
            }else {
                System.out.println("Invalid input. Please enter a letter or guess the entire word.");
            }
        }
         if (currentGuess.indexOf("-") == -1) {
            System.out.println("Congratulations! You guessed the word: " + secretWord);
        }else {
            System.out.println("Out of tries! The word was " + secretWord);
        }
    }
    // single letter guess
    private void handleLetterGuess(char letter) {
        boolean found = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                currentGuess.setCharAt(i, letter);
                found = true;
            }
        }
        if (found) {
            System.out.println("Good Guess: " + currentGuess);
        }else {
            remainingTries--;
            System.out.println("Incorrect guess! Remaining Tries: " + remainingTries);
        }
    }
    //full word guess
    private void handleWordGuess(String word) {
        if (word.equals(secretWord)) {
            currentGuess = new StringBuilder(secretWord);
        }else {
            remainingTries--;
            System.out.println("Incorrect guess! Remaining tries: " + remainingTries);
            System.out.println("Current guess: " + currentGuess);
        }
    }
}
