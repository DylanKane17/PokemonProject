public class OppPokemon extends Pokemon {
    //enemy will have different hp
    private String difficulty;
    private int hp;
    private int power;

    //gender has been left out due to it not being relevant to the enemy Pokémon

    //note add default

    public OppPokemon(String name, String type, int code, int hp, String difficulty, int power) {
        pmName = name;
        pmType = type;
        pmCode = code;
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

    public void printInfo() {
        System.out.printf("%s [%s], DIFFICULTY: %s", pmName, pmType, difficulty);
    }

}
