import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("*********************");
        System.out.println("* Welcome to Hangman! *");
        System.out.println("*********************");

        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            String word = getRandomWordFromFile("wordlist.txt"); 
            ArrayList<Character> playerGuesses = new ArrayList<>();
            int wrongGuesses = 0;
            boolean gameWon = false;

            while (wrongGuesses < 6) {
                System.out.println(getHangmanArt(wrongGuesses));

                System.out.print("Guessed letters: ");
                for (char c : playerGuesses) {
                    System.out.print(c + " ");
                }
                System.out.println("\n");

                if (printWordState(word, playerGuesses)) {
                    gameWon = true;
                    break;
                }

                if (!getPlayerGuess(scanner, playerGuesses)) {
                    continue; // Invalid input or already guessed, so we loop again
                }

                char lastGuess = playerGuesses.get(playerGuesses.size() - 1);
                if (word.indexOf(lastGuess) == -1) {
                    wrongGuesses++;
                    System.out.println("Sorry, the letter '" + lastGuess + "' is not in the word.");
                }
            }

            if (gameWon) {
                System.out.println("\nYou win! The word was: " + word);
            } else {
                System.out.println(getHangmanArt(wrongGuesses));
                System.out.println("You lose! The word was: " + word);
            }

            System.out.print("\nDo you want to play again? (Y/N): ");
            String playAgainInput = scanner.nextLine().trim().toLowerCase();
            playAgain = playAgainInput.equals("y");

            if (!playAgain && !playAgainInput.equals("n")) {
                System.out.println("Invalid input. Exiting game.");
            }

            System.out.println(); // Add a blank line for better separation between games

        } while (playAgain);
        
        scanner.close();
    }

    private static String getRandomWordFromFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            if (fileScanner.hasNextLine()) {
                String[] wordsArray = fileScanner.nextLine().split(",");
                for (String word : wordsArray) {
                    words.add(word.trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: wordlist.txt not found at " + filePath);
            System.out.println("Please make sure the file is in the 'src' folder.");
            System.exit(1);
        }

        if (words.isEmpty()) {
            System.out.println("Error: wordlist.txt is empty.");
            System.exit(1);
        }

        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    private static boolean printWordState(String word, ArrayList<Character> playerGuesses) {
        int correctCount = 0;
        System.out.print("Word: ");
        for (int i = 0; i < word.length(); i++) {
            char currentLetter = word.charAt(i);
            if (playerGuesses.contains(currentLetter)) {
                System.out.print(currentLetter + " ");
                correctCount++;
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
        return (word.length() == correctCount);
    }

    private static boolean getPlayerGuess(Scanner scanner, ArrayList<Character> playerGuesses) {
        System.out.print("Guess a letter: ");
        String letterGuess = scanner.nextLine().toLowerCase();

        if (letterGuess.length() != 1 || !Character.isLetter(letterGuess.charAt(0))) {
            System.out.println("Invalid input. Please enter a single letter.");
            return false;
        }

        char guess = letterGuess.charAt(0);

        if (playerGuesses.contains(guess)) {
            System.out.println("You have already guessed that letter. Try again.");
            return false;
        }

        playerGuesses.add(guess);
        return true;
    }

    static String getHangmanArt(int wrongGuesses) {
        return switch (wrongGuesses) {
            case 0 -> """
               -----
               |   |
                   |
                   |
                   |
                   |
            --------
            """;
            case 1 -> """
               -----
               |   |
               O   |
                   |
                   |
                   |
            --------
            """;
            case 2 -> """
               -----
               |   |
               O   |
               |   |
                   |
                   |
            --------
            """;
            case 3 -> """
               -----
               |   |
               O   |
              /|   |
                   |
                   |
            --------
            """;
            case 4 -> """
               -----
               |   |
               O   |
              /|\\  |
                   |
                   |
            --------
            """;
            case 5 -> """
               -----
               |   |
               O   |
              /|\\  |
              /    |
                   |
            --------
            """;
            case 6 -> """
               -----
               |   |
               O   |
              /|\\  |
              / \\  |
                   |
            --------
            """;
            default -> "";
        };
    }
}
