import java.util.Random;
public class Pokemon {
    protected String pmName;
    protected String pmType;
    protected String pmGender;
    protected String pmKind;
    protected int pmCode;

    //default constructor
    public Pokemon(){
        pmName = "unknown";
        pmType = "unknown";
        pmGender = "unknown";
        pmKind = "unknown";
    }
    //partially parameterized constructor
    public Pokemon(String type, String kind){
        pmName = "unknown";
        pmType = type;
        pmGender = "unknown";
        pmKind = kind;
    }

    //partially parameterized constructor 2
    public Pokemon(String type, String kind, int code){
        pmName = "unknown";
        pmType = type;
        pmGender = "unknown";
        pmKind = kind;
        pmCode = code;
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

    //print pokemon info
    public void displayPmInfo(){
        System.out.printf("%s (%s)\nType: %s\nGender: %s\n", pmName, pmKind, pmType, pmGender);
    }

}

