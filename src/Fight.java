import java.util.Scanner;
import java.util.Random;


public class Fight /* will extend the Pokémon class eventually */ {

    int pokemonCode; //delete me
    Random randGen = new Random();
    int health = 100;
    String oppName;
    int oppHealth = 100;
    boolean dead = false;

    public Fight(int myPokemonCode, String oppName) {
        this.oppName = oppName;
        pokemonCode = myPokemonCode;
    }

    String[][] options = {
            {"Fight", "Run", "Switch"},
            {"", "", ""},
    };

    String[][][] moves = {
            {{"Zap", "15 Power, 50% success rate"}, {"Thunderbolt", "30 Power, 10% success rate"}}, //electric code: 0
            {{"Splash", "35 Power, 100% success rate"}, {""}} //water
    };

    Integer[][][] powerAndSuccess = {
            {{15, 50}, {30, 10}},
            {{35, 100}},
    };

    public void printAndChoose(int optionSet, Scanner scnr) {
        int num = 1;
        for (int i = 0; i < options[optionSet].length; i++) {
            System.out.printf("-%d %s\n", num, options[optionSet][i]);
            num += 1;
        }
        System.out.print("Choice: ");
        int choice = scnr.nextInt();
        if (optionSet == 0) {
            if (choice == 1) {
                fight(randGen, scnr);
            }
            if (choice == 2) {
                System.out.print("You manage to escape!");
            }
        }
    }

    public int printAndChooseMove(int moveSet, Scanner scnr) {
        System.out.println("\nSelect Your Move:");
        int num = 1;
        for (int i = 0; i < moves[moveSet].length; i++) {
            System.out.printf("-%d %s: %s\n", num, moves[moveSet][i][0], moves[moveSet][i][1]);
            num += 1;
        }
        System.out.print("Choice: ");
        return scnr.nextInt() - 1;
    }

    public void encounter(Scanner scnr) {
        System.out.println("You've encountered " + oppName + "! What do you do next?");
        printAndChoose(0, scnr);
    }


    public void fight(Random randGen, Scanner scnr) {

        int choice = printAndChooseMove(0, scnr);

        if (!dead) {
            if (randGen.nextInt(99) + 1 <= powerAndSuccess[pokemonCode][choice][1]) {
                hit(moves[pokemonCode][choice][0], powerAndSuccess[pokemonCode][choice][0]);
            } else {
                System.out.println(oppName + " dodged the attack!");
            }
        }

        if (!dead) {
            System.out.print(oppName + " attacks! ");
            if (randGen.nextInt(99) + 1 <= 80) {
                takeDamage(30); //delete 30
            } else {
                System.out.println("You dodged the attack!");
            }
        }


        System.out.println();
        System.out.println();
        printAndChoose(0, scnr);
    }

    private void hit(String moveName, int power ) {
        System.out.print("You hit [opposition pokemon] with " + moveName + "! It was very effective! They take " + power + " damage! ");
        oppHealth -= power;
        if (oppHealth <= 0) {
            System.out.print(oppName + " fainted!");
            dead = true;
        } else {
            System.out.println("They have " + oppHealth + " HP remaining.");
        }
    }

    private void takeDamage(int damage) {
        health -= damage;
        System.out.print("You take " + damage + " damage! ");
        if (health <= 0) {
            System.out.println("Your Pokémon fainted!");
            dead = true;
        } else {
            System.out.print("Your Pokémon has " + health + " HP remaining.");
        }
    }
}
