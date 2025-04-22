/************************************************************************
 * @file PokemonBattle.java
 * @brief This program implements java to create a program class. It prompts the user to select their Pokemon and an enemy to fight.
 * @author Dylan Kane, Neela Kuntamukkala
 * @data April 22, 2025
 *************************************************************************/


import java.util.Random;
import java.util.Scanner;

public class PokemonBattle {
    public static void main(String[] args){


        Scanner scnr = new Scanner(System.in);
        Random randGen = new Random();

        boolean fighting = true; //will be used to break out of while loop

        //Pokemon you can pick from
        Pokemon charmander = new Pokemon("Fire", "Charmander", 2);
        Pokemon bulbasaur = new Pokemon("Grass","Bulbasaur", 1);
        Pokemon squirtle = new Pokemon("Water","Squirtle", 0);

        //Enemy Pokemon
        OppPokemon magikarp = new OppPokemon("Magikarp", "Water", 0, 50, "Easy", 10);
        OppPokemon flareon = new OppPokemon("Flareon", "Fire", 2, 150, "Medium", 15);
        OppPokemon sceptile = new OppPokemon("Mega Sceptile", "Grass", 1, 500, "Hard", 25);

        //Array to make selection and printing more versatile
        OppPokemon[] oppPokemonList = {magikarp, flareon, sceptile};

        Pokemon yourPokemon = null;
        String name;
        String userInput;
        char confirmName;

        System.out.println("Welcome to Pokemon Battle!\n\nLet's get started! First, which Pokemon" +
                " would you like to choose? Charmander, Bulbasaur or Squirtle?");
        userInput = scnr.next();
        while(!userInput.equalsIgnoreCase("Charmander") &&
                !userInput.equalsIgnoreCase("Bulbasaur") &&
                !userInput.equalsIgnoreCase("Squirtle")) {
            System.out.print("Invalid choice. Enter again: ");
            userInput = scnr.next();
        }
        if (userInput.equalsIgnoreCase("charmander")){
            yourPokemon = charmander;
        }
        else if (userInput.equalsIgnoreCase("bulbasaur")){
            yourPokemon = bulbasaur;
        }
        else if (userInput.equalsIgnoreCase("squirtle")){
            yourPokemon = squirtle;
        }
        yourPokemon.setPmGender(randGen);

        System.out.println("Good choice! What would you like to name your " + userInput + "?");
        name = scnr.next();
        yourPokemon.setPmName(name);
        System.out.println("Confirm: Your " + userInput + "'s name is " + yourPokemon.getPmName() + "?(y/n)");
        confirmName = scnr.next().charAt(0);
        while ((confirmName != 'y') && (confirmName != 'n')){
            System.out.println("Invalid response. Try again(y/n): ");
            confirmName = scnr.next().charAt(0);
        }
        if(confirmName == 'y'){
            System.out.println("Excellent!");
        }
        else{
            System.out.print("Enter new name (You cannot edit name after this): ");
            name = scnr.next();
            yourPokemon.setPmName(name);
        }
        System.out.println("Success: Your " + userInput + "'s name is now " + yourPokemon.getPmName() + "\n");
        yourPokemon.displayPmInfo();

        System.out.println();

        while(fighting) {
            System.out.println("BATTLE: Who do you fight?");

            for (int i = 0; i < oppPokemonList.length; i++) {//will print each enemy option
                int num = i + 1;
                System.out.printf("-%d ", num);
                oppPokemonList[i].printInfo();
                System.out.println();
            }
            System.out.print("CHOICE: ");
            int choice = scnr.nextInt();
            while (choice != 1 && choice != 2 && choice != 3) { //checks if choice is valid
                System.out.print("ERROR! Please make a valid choice: ");
                choice = scnr.nextInt();
            }

            Fight FightSim = new Fight(yourPokemon, oppPokemonList[choice - 1]); //starts fight sim
            FightSim.encounter(scnr);

            System.out.println("Want to attempt another fight? (y/n)"); //asks if user wants to keep going
            if (scnr.next().charAt(0) == 'n') fighting = false; //breaks out of loop
            System.out.println();
        }


    }
}


