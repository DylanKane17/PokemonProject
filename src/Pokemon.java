/************************************************************************
 * @file Pokemon.java
 * @brief This program implements java to create a Pokemon base class
 * @author Dylan Kane, Neela Kuntamukkala
 * @data April 22, 2025
 *************************************************************************/

import java.util.Random;
public class Pokemon {
    //Protected fields
    protected String pmName;
    protected String pmType;
    protected String pmGender;
    protected int pmCode;

    //default level is 1 and xp is 0
    protected int level;
    protected int xp;

    //default constructor
    public Pokemon(){
        pmName = "unknown";
        pmType = "unknown";
        pmGender = "unknown";
        level = 1;
        xp = 0;

    }
    //partially parameterized constructor
    public Pokemon(String type){
        pmName = "unknown";
        pmType = type;
        pmGender = "unknown";
        level = 1;
        xp = 0;
    }

    //partially parameterized constructor 2 (used also for OppPokemon, where gender and kind is less relevant)
    public Pokemon(String name, String type, int code){
        pmName = name;
        pmType = type;
        pmGender = "unknown";
        pmCode = code;
        level = 1;
    }

    //set methods
    public void setPmName(String name){
        pmName = name;
    }
    public void setPmType(String type){
        pmType = type;
    }
    public void setPmGender(Random rand){
        String[] genders = {"Male", "Female"};
        pmGender = genders[rand.nextInt(genders.length)];
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public void setPmCode(int pmCode) {
        this.pmCode = pmCode;
    }

    //get methods
    public String getPmName(){
        return pmName;
    }
    public String getPmType(){
        return pmType;
    }
    public String getPmGender(){
        return pmGender;
    }
    public int getPmCode() {
        return pmCode;
    }
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }

    //Three useful methods
    public void addMoreXp(int xp) { //adds xp to Pokemon after a battle is completed
        this.xp += xp;
        System.out.printf("%s gained %d XP from the battle.\n", pmName, xp);
        decideWhetherToLevelUp(); //method calls another method
    }
    private void decideWhetherToLevelUp() { //decides whether Pokemon should level up
        if (xp >= (level * 100)) {
            level += 1; //levels up
            System.out.printf("Congratulations! %s has levelled up!\n", pmName);
            System.out.println("Current level: " + level);
        }
    }
    public boolean didDodge(Random randGen) { //decides whether Pokemon dodged, more likely to dodge if a higher level
        if ((level < 3) && (randGen.nextInt(99) + 1 >= 80)) return true; //20% chance
        if ((level < 5) && (randGen.nextInt(99) + 1 >= 70)) return true; //30% chance
        if ((level < 6) && (randGen.nextInt(99) + 1 >= 65)) return true; //35% chance
        return false; //Pokemon did not dodge
    }

    //prints pokemon info
    public void displayPmInfo(){
        System.out.printf("%s\nType: %s\nGender: %s\n", pmName, pmType, pmGender);
    }

}

