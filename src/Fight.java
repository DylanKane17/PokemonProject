/************************************************************************
 * @file Fight.java
 * @brief This program implements java to simulate a Pokemon-style fight
 * @author Dylan Kane, Neela Kuntamukkala
 * @data April 22, 2025
 *************************************************************************/



import java.util.Scanner;
import java.util.Random;


public class Fight {

    //Fields relevant to the fight sim
    private Random randGen = new Random();
    private int pmHp = 100;
    private boolean dead = false;
    private int choice;

    //Fields to obtain from the OppPokemon and Pokemon objects
    private Pokemon userPokemon; //user's Pokemon
    private OppPokemon oppPokemon; //enemy Pokemon
    private String pmName; //user's Pokemon name
    private String oppName;//enemy Pokemon's name
    private int pmCode; //user's Pokemon code
    private int oppCode; //enemy's Pokemon code (to calculate elemental damage)
    private int oppHp;
    private int oppAttack;//enemy's attack power

    //note: as this class does not interact with the main(), no set/get methods were needed

    //default constructor
    public Fight() {
        oppName = "unknown";
        pmCode = -1;
        pmName = "unknown";
        oppHp = -1;
        oppAttack = -1;
        oppCode = -1;
    }

    //parameterized constructor
    public Fight(Pokemon pokemon, OppPokemon oppPokemon) {
        userPokemon = pokemon;
        this.oppPokemon = oppPokemon;
        oppName = oppPokemon.getPmName();
        pmCode = pokemon.getPmCode();
        pmName = pokemon.getPmName();
        oppHp = oppPokemon.getHp();
        oppAttack = oppPokemon.getPower();
        oppCode = oppPokemon.getPmCode();
    }

    //array for the fighting options
    String[] options = {
            "Fight", "Run"
    };

    //array for the moves
    String[][][] moves = { //{move name, move type}
            {{"Splash", "[WATER]"}, {"Dive", "[WATER]"}, {"Take Down", "[NORMAL]"}}, //water code: 0
            {{"Vine Whip", "[GRASS]"}, {"Seed Bomb", "[GRASS]"}, {"Hit", "[NORMAL]"}}, //grass code: 1
            {{"Fireball", "[FIRE]"}, {"Volcano", "[FIRE]"}, {"Scratch", "[NORMAL]"}} //fire code: 2
    };

    //array for the power and success rate of each move
    int[][][] powerAndSuccess = { //{power, success %}
            {{10, 100}, {25, 90}, {50, 60}}, //low power, high chances of success
            {{15, 70}, {45, 20}, {80, 10}}, //high power, low chances of success
            {{20, 80}, {30, 35}, {40, 55}} //all-rounder
    };

    public void printAndChoose(Scanner scnr) {
        int num = 1;
        for (int i = 0; i < options.length; i++) { //prints Fight and Run as options
            System.out.printf("-%d %s\n", num, options[i]);
            num += 1;
        }
        System.out.print("Choice: "); //prompts user to make a decision
        int decision = scnr.nextInt();
            if (decision == 1) fight(randGen, scnr); //user prompted to make a move
            if (decision == 2) System.out.println("You manage to escape!"); //exits fight
    }

    public int printAndChooseMove(int moveSet, Scanner scnr) {
        System.out.println("\nSelect Your Move:");
        int num = 1;
        for (int i = 0; i < moves[moveSet].length; i++) { //prints out in form of a checklist based on the pokemon type
            System.out.printf("-%d %s: %d attack, %d success chance %s\n", num, moves[moveSet][i][0], (powerAndSuccess[moveSet][i][0] * userPokemon.getLevel()), powerAndSuccess[moveSet][i][1], moves[moveSet][i][1]);
            num += 1;
        }
        System.out.print("Choice: ");
        return scnr.nextInt() - 1;
    }

    public void encounter(Scanner scnr) { //called in main() to begin fight sim
        System.out.println("You've encountered " + oppName + "! What do you do next?");
        printAndChoose( scnr); //prompts user to either fight or run
    }

    public void fight(Random randGen, Scanner scnr) {

        choice = printAndChooseMove(pmCode, scnr);

        if (!dead) {
            if (randGen.nextInt(99) + 1 <= powerAndSuccess[pmCode][choice][1]) { //randomly decides if your move works based on success rate
                hit(moves[pmCode][choice][0], (powerAndSuccess[pmCode][choice][0] * userPokemon.getLevel())); //hits opponent Pokemon, power scales based on level
            } else {
                System.out.println(oppName + " dodged the attack!"); //move is not a success, hit() method is not called and opponent Pokemon takes no damage
            }
        }

        if (!dead) {
            System.out.print(oppName + " attacks! ");
            if (userPokemon.didDodge(randGen)) { //decides whether Pokemon dodged
                takeDamage(oppAttack); //will cause your Pokemon to take damage
            } else {
                System.out.println("You dodged the attack!");
            }
        }

        if (!dead) { //if neither the user's Pokemon nor the opponent Pokemon is dead, the user will be prompted to fight or run again
            System.out.println();
            System.out.println();
            printAndChoose( scnr);
        }
    }

    private void hit(String moveName, int power ) { //called when a successful hit is made
        System.out.print("You hit " + oppName + " with " + moveName + "! ");
        String moveType = moves[pmCode][choice][1]; //will be ether "[FIRE]", "[WATER]", "[GRASS]" or "[NORMAL]"

        if (power > oppPokemon.decideElementalDamage(moveType, power)) { //calculates if the move will be worse with elemental effects
            System.out.print("It was not very effective! ");
        } else if (power < oppPokemon.decideElementalDamage(moveType, power)) { //calculates if the move will be better with elemental effects
            System.out.print("It was very effective! ");
        } //if power = (power with elemental effects), will not print anything

        power = oppPokemon.decideElementalDamage(moveType, power); //adds elemental effects
        System.out.println("They took " + power + " damage!");
        oppHp -= power;
        if (oppHp <= 0) {
            System.out.println(oppName + " fainted!");
            userPokemon.addMoreXp(oppPokemon.calculateXP()); //adds XP to user's Pokemon
            dead = true; //stops the printAndChoose() from running again; stops the fight
        } else {
            System.out.println("They have " + oppHp + " HP remaining."); //prints remaining hp
        }
    }

    private void takeDamage(int damage) { //called when your Pokemon takes damage (based on opponent's attack level)
        pmHp -= damage;
        System.out.print("You take " + damage + " damage! "); //prints how much damage you took
        if (pmHp <= 0) {
            System.out.println(pmName + " fainted!");
            dead = true; //stops the printAndChoose() from running again; stops the fight
        } else {
            System.out.print(pmName + " has " + pmHp + " HP remaining."); //prints remaining hp to user
        }
    }
}
