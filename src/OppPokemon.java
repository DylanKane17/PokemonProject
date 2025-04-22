/************************************************************************
 * @file OppPokemon.java
 * @brief This program implements java to create an opponent Pokemon (OppPokemon) extended class
 * @author Dylan Kane, Neela Kuntamukkala
 * @data April 22, 2025
 *************************************************************************/


public class OppPokemon extends Pokemon {
    //private fields
    private String difficulty;
    private int hp;
    private int power;

    public OppPokemon() { //default constructor
        super(); //super is used to call default constructor of the base class
        hp = -1;
        difficulty = "unknown";
        power = -1;
    }

    public OppPokemon(String name, String type, int code, int hp, String difficulty, int power) { //parameterized constructor
        super(name, type, code); //super is used to call parameterized constructor of base class
        this.hp = hp;
        this.difficulty = difficulty;
        this.power = power;
    }

    //necessary get methods
    public int getHp() {
        return hp;
    }
    public int getPower() {
        return power;
    }
    public String getDifficulty() {
        return difficulty;
    }

    //set methods
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    //Two useful methods
    public int calculateXP() { //decides how much XP to give the player
        if (difficulty.equals("Easy")) return 100;
        if (difficulty.equals("Medium")) return 200;
        return 500; //if not easy or medium, will be hard
    }
    public int decideElementalDamage(String moveType, int userPower) { //calculates damage to deal based on elements
        if (moveType.equals("[WATER]")) {
            if (pmCode == 1) return userPower/2; //water VS grass = ineffective
            if (pmCode == 2) return userPower*2; //water VS fire = effective
        }
        if (moveType.equals("[FIRE]")) {
            if (pmCode == 0) return userPower/2; // fire VS water = ineffective
            if (pmCode == 1) return userPower*2; //fire VS grass = effective
        }
        if (moveType.equals("[GRASS]")) {
            if (pmCode == 2) return userPower/2; // grass VS fire = ineffective
            if (pmCode == 0) return userPower*2; //grass VS water = effective
        }
        return userPower; //if it is [NORMAL], or the same type, it will return the power with no changes
    }

    public void printInfo() { //prints info about Pokémon to display as options to the player
        System.out.printf("%s [%s], DIFFICULTY: %s", pmName, pmType, difficulty);
    }

}
