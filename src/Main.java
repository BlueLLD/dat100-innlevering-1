import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loopProgram = true;
        while (loopProgram){
            int task = forceIntRead(
                "Select a ¤cyan ¤bold task end¤ to run [¤green 0 end¤-¤green 3 end¤]\n¤green 0 end¤ to end the program",
            "¤cyan ¤bold Task end¤ has to be a ¤green number");

            flush();
            switch (task){
                case 1:
                    OppgaveO1();
                    break;
                case 2:
                    OppgaveO2();
                    break;
                case 3:
                    error("Not implemented");
                    break;
                default:
                    loopProgram = false;
            }
        }

        println(Color.rainbow("Hello, World!"));
    }
    /**
     * Prints a colored message based on the ¤ format
     */
    static void println(String x) {
        System.out.println(Color.colorize(x));
    }
    /**
     * Prints an error message
     */
    static void error(String x){
        println("¤red ERROR: ¤end¤"+x);
    }


    /**
     * "Flushes" the screen
     * In reality it just prints a bunch of newlines
     */
    static void flush(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     *  A function for assuring that the input will be a number
     * @param message the message to ask when reading the number
     * @return read number
     */
    static int forceIntRead(String message){
        return forceIntRead(message,null);
    }
    /**
     * A function for assuring that the input will be a number
     * @param message the message to ask when reading the number
     * @param errorMessage warning message to display if the user inputted wrong
     * @return read number
     */
    static int forceIntRead(String message,String errorMessage){
        return forceIntRead(message,errorMessage,null);
    }
    /**
     * A function for assuring that the input will be a number
     * @param message the message to ask when reading the number
     * @param errorMessage warning message to display if the user inputted wrong
     * @param predicate a check if the number is valid for your use case
     * @return read number
     */
    static int forceIntRead(String message,String errorMessage, Function<Integer,Boolean> predicate){
        while (true){
            println(message);
            if(input.hasNextInt()){
                int readInt = input.nextInt();
                if (predicate == null)
                    return readInt;
                if(predicate.apply(readInt))
                    return readInt;
                continue;
            }
            if(errorMessage != null)
                error(errorMessage);
            input.next();
        }
    }
    static <K,V> V findByPredicate(LinkedHashMap<K,V> mapToSearch, BiFunction<K,V, Boolean> predicate, V notFound){
        for (var KV : mapToSearch.entrySet()){
            if(predicate.apply(KV.getKey(),KV.getValue())){
                return KV.getValue();
            }
        }
        return notFound;
    }

    static void OppgaveO1(){
        println("Oppgave ¤violet O1");
        LinkedHashMap<Integer,Float> incomeToTax = new LinkedHashMap<>(){{
            put(217_400,0f);
            put(306_050,1.7f);
            put(697_150,4.0f);
            put(942_400,13.7f);
            put(1_410_750,16.7f);
        }};

        int income = forceIntRead("Hvor mye ¤bold ¤red tjente end¤ du i år?");
        float toTaxPercent = findByPredicate(incomeToTax,(key,value)->key>=income,17.7f);
        float tax = ((income/100.0f)*toTaxPercent);

        println("Siden du tjente ¤green "+income+"kr end¤ så må du skatte ¤blue " + toTaxPercent + "% end¤ som blir ¤red "+tax+"kr");
    }
    static void OppgaveO2(){
        println("Oppgave ¤violet O2");

        List<Student> students = new ArrayList<>(List.of());//new Student("Lars",100),new Student("Bro",50),new Student("The queen of dance",80)
        println("How many ¤student students end¤ are you registering?\nDefault ¤green 10");
        input.useDelimiter("\n");
        int amountOfStudents = input.hasNextInt() ? input.nextInt() : 10;
        input.reset();

        println("Register your ¤green "+amountOfStudents+" ¤student student"+(amountOfStudents > 1 ? "s" : "") + " end¤:");
        for(int i = 0;i<amountOfStudents;i++){
            println("Whats the ¤bold ¤yellow name end¤ of the ¤student student");
            String name = input.next();
            int score = forceIntRead("What ¤green score end¤ did the ¤student student end¤ get?","Grade has to be a ¤green number",number->{
                if(number > 100)
                    error("Grade cannot be higher than ¤red 100");
                if(number < 0)
                    error("Grade cannot be lower than ¤red 0");

                return number <= 100 && number >= 0;
            });
            flush();
            println("¤student Student end¤ ¤yellow "+name+" end¤ with the score ¤green "+score+" end¤ was added");
            students.add(new Student(name,score));
        }

        students.sort(Comparator.comparingInt(student -> student.score));
        println("¤student Students end¤ ranked by score");
        int count = 1;
        for(Student student : students.reversed()){
            println("- ¤yellow ¤bold "+(count++)+". ¤student "+student.name+ " end¤ | Grade: ¤blue "+student.getGrade()+ " end¤ | Score: ¤green "+student.score);
        }
    }
    static void OppgaveO3(){

    }
}