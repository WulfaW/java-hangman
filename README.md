# Java Hangman

A classic command-line Hangman game developed in Java. Players must guess a hidden word, letter by letter, before the hangman figure is fully drawn after a series of incorrect guesses. The game is designed to be simple, engaging, and easy to run.

---

## Features

- **Dynamic Word Selection**: The game reads words from a `wordlist.txt` file, allowing for easy customization and expansion of the word bank.
- **Interactive Gameplay**: Provides real-time feedback on correct and incorrect guesses.
- **ASCII Art Display**: Progressively shows the hangman figure as the player makes wrong guesses, adding a classic visual element to the game.
- **Input Validation**: Ensures the player enters a valid single-letter guess and prevents them from guessing the same letter twice.
- **Replayability**: After each round, the player has the option to start a new game.

---

## Technologies Used

* **Java**: The project is built entirely using standard Java features and libraries, without any external dependencies.
    - `java.io.File`
    - `java.util.ArrayList`
    - `java.util.Random`
    - `java.util.Scanner`

---

## How to Play

### Prerequisites

You need to have the **Java Development Kit (JDK)** installed on your machine.

### Installation and Setup

1.  **Clone the Repository**:
    ```bash
    git clone [https://github.com/WulfaW/java-hangman.git](https://github.com/WulfaW/java-hangman.git)
    cd java-hangman
    ```

2.  **Create the Word List**:
    The game requires a `wordlist.txt` file inside the `src` folder. Create the file and add a comma-separated list of words. For example:
    ```
    programming, computer, developer, project, hangman, java, github
    ```

3.  **Compile and Run**:
    Use your preferred terminal to navigate to the project directory and run the following commands:
    ```bash
    # Compile the Java file
    javac src/Main.java

    # Run the compiled program
    java src.Main
    ```

The game will start in your terminal, and you can begin guessing letters to find the word!

Enjoy! 🎮
