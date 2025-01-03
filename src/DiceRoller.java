import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    public void rollDice(int sides, int rolls) {
        if (sides < 1 || rolls < 1) {
            System.out.println("Sides and rolls must be greater than 0");
            return;
        }
        int sumDice = 0; // sum of all the dice after all the rolls


        Random random = new Random();
        System.out.println("Rolling a " + sides + "-sided dice " + rolls + " rolls");
        for (int i = 1; i <= rolls; i++) {
            int roll = random.nextInt(sides) + 1; // Random number between 1 and sides +1 because it starts at 0
            System.out.println("Roll #" + i + ": " + roll);
            sumDice += roll;
        }
        System.out.println("\n --- Total ---\nTotal of all rolls: " + sumDice);
        double average = (double) sumDice / rolls;
        System.out.println("\n --- Average ---\nAverage of all rolls: " + average);
    }

    public static void main(String[] args) {
        DiceRoller diceRoller = new DiceRoller(); // create the Object dice roller
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        int sides = getPositiveInput(scanner, "Enter the number of sides for the dice: ");
        int rolls = getPositiveInput(scanner, "Enter the number of rolls for the dice: ");

        diceRoller.rollDice(sides, rolls);
        scanner.close();

    }
    private static int getPositiveInput(Scanner scanner, String prompt){
        int value = -1; // initialize with an invalid value for the loops
        while(value <= 0){ //keep looping until a positive number is given.
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt(); //read the integer
                if (value <= 0) {
                    System.out.println("Must be a positive number");
                }
            } else {
                System.out.println("Must be a positive number");
                scanner.next(); // clears the invalid input
            }


        }
        return value; //returns a verified integer
    }

}