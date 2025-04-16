import java.util.Scanner;
import java.util.Random;


public class Fight {

    int pokemonCode; //delete me
    Random randGen = new Random();
    int pmHp = 100;
    int oppHp;
    boolean dead = false;

    String pmName; //user's Pokemon
    String oppName;//enemy Pokemon
    int oppAttack; //enemy's attack power

    public Fight(Pokemon pokemon, OppPokemon oppPokemon) {
        oppName = oppPokemon.getPmName();
        pokemonCode = pokemon.getPmCode();
        pmName = pokemon.getPmName();
        oppHp = oppPokemon.getHp();
        oppAttack = oppPokemon.getPower();
    }

    String[][] options = {
            {"Fight", "Run", "Switch"},
            {"", "", ""},
    };

    String[][][] moves = {
            {{"Zap", "[ELECTRIC]"}, {"Thunderbolt", "[ELECTRIC]"}, {"Tackle", "[NORMAL]"}}, //electric code: 0
            {{"Splash", "[WATER]"}, {"Dive", "[WATER]"}, {"Take Down", "[NORMAL]"}}, //water code: 1
            {{"Vine Whip", "[GRASS]"}, {"Seed Bomb", "[GRASS]"}, {"Heal", "[NORMAL]"}}, //grass code: 2
            {{"Vine Whip", "[GRASS]"}, {"", "[GRASS]"}, {"Scratch", "[NORMAL]"}} //fire code: 3
    };

    int[][][] powerAndSuccess = { //{power, success %}
            {{15, 90}, {40, 30}, {80, 15}}, //high power, low chances of success
            {{10, 100}, {25, 90}, {50, 60}}, //low power, high chances of success
            {{15, 70}, {25, 60}, {0, 100}}, //low attack, can heal
            {{20, 80}, {30, 35}, {40, 55}} //all-rounder
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
            if (choice == 1) fight(randGen, scnr);
            if (choice == 2) System.out.println("You manage to escape!");
        }
    }

    public int printAndChooseMove(int moveSet, Scanner scnr) {
        System.out.println("\nSelect Your Move:");
        int num = 1;
        for (int i = 0; i < moves[moveSet].length; i++) { //prints out in form of a checklist
            System.out.printf("-%d %s: %d attack, %d success chance %s\n", num, moves[moveSet][i][0], powerAndSuccess[moveSet][i][0], powerAndSuccess[moveSet][i][1], moves[moveSet][i][1]);
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

        int choice = printAndChooseMove(pokemonCode, scnr);

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
                takeDamage(oppAttack);
            } else {
                System.out.println("You dodged the attack!");
            }
        }

        if (!dead) {
            System.out.println();
            System.out.println();
            printAndChoose(0, scnr);
        }
    }

    private void hit(String moveName, int power ) {
        System.out.print("You hit " + oppName + " with " + moveName + "! It was very effective! They take " + power + " damage! ");
        oppHp -= power;
        if (oppHp <= 0) {
            System.out.println(oppName + " fainted!");
            dead = true;
        } else {
            System.out.println("They have " + oppHp + " HP remaining.");
        }
    }

    private void takeDamage(int damage) {
        pmHp -= damage;
        System.out.print("You take " + damage + " damage! ");
        if (pmHp <= 0) {
            System.out.println(pmName + " fainted!");
            dead = true;
        } else {
            System.out.print(pmName + " has " + pmHp + " HP remaining.");
        }
    }
}
