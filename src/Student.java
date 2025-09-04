import java.util.LinkedHashMap;

public class Student {
    public String name;
    public int score;
    private final LinkedHashMap<Integer,String> scoreToGrade = new LinkedHashMap<>(){{
        put(39, "F");
        put(49, "E");
        put(59, "D");
        put(79, "C");
        put(89, "B");
    }};
    Student(String name, int score){
        this.name = name;
        this.score = score;
    }
    public String getGrade(){
        return Main.findByPredicate(scoreToGrade, (key,value)->key>=score,"A");
    }
}
