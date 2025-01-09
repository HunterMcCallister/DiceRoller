import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    public void rollDice(int sides, int rolls) {
        if (sides < 1 || rolls < 1) {
            System.out.println("Sides and rolls must be greater than 0");
            return;
        }
        int sumDice = 0; // sum of all the dice after all the rolls
        int[] frequencies = new int[sides];


        Random random = new Random();
        System.out.println("Rolling a " + sides + "-sided dice " + rolls + " rolls");
        for (int i = 1; i <= rolls; i++) {
            int roll = random.nextInt(sides) + 1; // Random number between 1 and sides +1 because it starts at 0
            frequencies[roll - 1]++; // increments the array at that rolls location.
            System.out.println("Roll #" + i + ": " + roll);
            sumDice += roll;
        }
        System.out.println("\n --- Total ---\nTotal of all rolls: " + sumDice);
        double average = (double) sumDice / rolls;
        System.out.println("\n --- Average ---\nAverage of all rolls: " + average);

        // Display the frequencies
        System.out.println("\n --- Frequencies ---");
        for (int i = 0; i < frequencies.length; i++) {
            System.out.println((i + 1) + ": " + frequencies[i] + " times");// i + 1maps the index (0-based) to the dice (1-based)
        }

        // Find the most frequent rolls
        int maxFrequency = findMaxFrequencies(frequencies);


        System.out.println("\n --- Max frequency ---");
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] == maxFrequency) {
                System.out.println("Face " + (i + 1) + ": " + frequencies[i] + " times");
            }
        }
    }

    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRoller(); // create the Object dice roller
        Scanner scanner = new Scanner(System.in); // Scanner for user input
        boolean keepPlaying = true; // starts positive for the game loop


        while (keepPlaying) {
            System.out.print("Want to play Dice Roller? (Y/N): ");
            String playResponse = scanner.nextLine().toLowerCase();

            switch (playResponse) {
                case "y":
                    int sides = getPositiveInput(scanner, "Enter the number of sides for the dice: ");
                    int rolls = getPositiveInput(scanner, "Enter the number of rolls for the dice: ");
                    diceRoller.rollDice(sides, rolls);

                    boolean validResponse = false;

                    while (!validResponse) {
                        System.out.print("Roll again? (Y/N): ");
                        String rollAgainResponse = scanner.nextLine().toLowerCase();
                        if (rollAgainResponse.equals("y")) {
                            validResponse = true;
                        } else if (rollAgainResponse.equals("n")) {
                            validResponse = true;
                            keepPlaying = false;
                            System.out.println("Goodbye!");
                        } else {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                    }
                    break;

                case "n":
                    keepPlaying = false;
                    System.out.print("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");

            }
        }


        scanner.close();

    }

    private static int getPositiveInput(Scanner scanner, String prompt) {
        int value = -1; // initialize with an invalid value for the loops
        while (value <= 0) { //keep looping until a positive number is given.
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt(); //read the integer
                scanner.nextLine(); // clears to scanner to avoid conflict with loop
                if (value <= 0) {
                    System.out.println("Must be a positive number");
                }
            } else {
                System.out.println("Must be a positive number");
                scanner.nextLine(); // clears the invalid input
            }


        }
        return value; //returns a verified integer
    }

    private int findMaxFrequencies(int[] frequencies) {
        int maxFrequency = 0;
        for (int frequency : frequencies) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }
        return maxFrequency;
    }

}
