import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {
    "+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int nbOfMisses = 0;
        // 1- chose random word
        String wordToGuess = getRandomWord();
        String wordCopy = wordToGuess;
        System.out.println(wordCopy);
        // store the users inputs
        String rightLetters  = "";
        String wrongLetters = "";
        
        while ( nbOfMisses < gallows.length - 1) {
            System.out.println(gallows[nbOfMisses]);
            // create word placeholder 
            displayWord(wordCopy, rightLetters);
            if (rightLetters.length() == wordToGuess.length()) {
                System.out.println("Good Work");
                System.exit(0);
            }
            System.out.println("Misses:\t " + wrongLetters);
            // get the user input
            String userLetter = getUserInput();
            // check the user input
            if (wordToGuess.contains(userLetter)) {
                rightLetters += userLetter;
                wordToGuess =  wordToGuess.replaceFirst(userLetter.charAt(0) + "", "*");
            } else {
                wrongLetters += userLetter;
                nbOfMisses++;
            }
        } 
        System.out.println(gallows[nbOfMisses]);
        System.out.println("Rip!");
        System.out.println("the word was:" + wordCopy);

    }

    /**
     * Function name : getRandomWord
     * @return (String)
     * Inside the function:
     *  1. pick up a random word from the the words array
     */
    public static String getRandomWord() {
        return words[(int)(Math.random() * words.length + 1)];
    }

    /**
     * Funcion name :displayWord
     * @param word (String)
     * @param rightLetters (String)
     * Inside the function:
     *  1. iterate throw the real word 
     *  2. display gussed caracters and "_" for the others
     */
    public static void displayWord(String word,String rightLetters) {
        System.out.print("Word: \t");
        for (int i = 0; i < word.length(); i++) {
            if (rightLetters.contains(word.charAt(i) + "")) {
                System.out.print(word.charAt(i));
                // replace it in the rightLetters
                rightLetters =  rightLetters.replaceFirst(word.charAt(i) + "", "*");
            } else {
                System.out.print('_');
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    /**
     * Function name : getUserInput
     * @return (String)
     * Inside the function:
     *  1. get the user input
     *  2. convert it to ascci code
     *  3. keep looping until the user enter a valid caracter
     */
    public static String getUserInput() {
        System.out.print("Guess: \t");
        String userLetter = scanner.next();
        int code = userLetter.toUpperCase().charAt(0);
        while (userLetter.length() != 1 || code < 65 || code > 92) {
            System.out.print("Enter a valide Caracter:\t");
            userLetter = scanner.next();
            code = userLetter.toUpperCase().charAt(0);
        }
        return userLetter;
    }
}





