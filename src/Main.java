import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in);

        Fight FightSim = new Fight(0, "Pikachu");

        FightSim.encounter(scnr);

    }
}