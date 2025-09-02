import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;
public final class Color {
    // Basic colors
    public static String RED = "\033[31m";
    public static String BLUE = "\033[34m";
    public static String GREEN = "\033[32m";
    public static String YELLOW = "\033[33m";
    public static String VIOLET = "\033[35m";
    public static String CYAN = "\033[36m";
    public static String BLACK = "\033[30m";
    public static String WHITE = "\033[37m";
    public static String GREY = "\033[90m";
    public static String RED2 = "\033[91m";
    public static String GREEN2 = "\033[92m";
    public static String YELLOW2 = "\033[93m";
    public static String BLUE2 = "\033[94m";
    public static String VIOLET2 = "\033[95m";
    public static String CYAN2 = "\033[96m";
    public static String WHITE2 = "\033[97m";
    // System formatting
    public static String END = "\033[0m";
    public static String BOLD = "\033[1m";
    public static String ITALIC = "\033[3m";
    public static String UNDERLINE = "\033[4m";
    public static String BLINK = "\033[5m";
    public static String BLINK2 = "\033[6m";
    public static String SELECTED = "\033[7m";
    public static Pattern FORMAT_REGEX = Pattern.compile("¤[A-Za-z0-9_]+¤|¤[A-Za-z0-9_]+\s|\s[A-Za-z0-9_]+¤");

    public  static String STUDENT = BOLD+VIOLET;

    /**
     * Returns a colorized string based on the ¤ format
     */
    public static String colorize(String x) {
        Matcher matches = FORMAT_REGEX.matcher(x);
        List<String> fields = Arrays.stream(Color.class.getDeclaredFields()).filter(field -> field.getType() == String.class).map(Field::getName).toList();
        while (matches.find()){
            String colorSymbol = matches.group();
            String fieldSymbol = colorSymbol.replace("¤","").replace(" ","").toUpperCase();
            Field field = null;
            try{
                if(fields.contains(fieldSymbol)){
                    field = Color.class.getDeclaredField(fieldSymbol);
                    x = x.replace(colorSymbol,(String)field.get(null));
            }
            }catch (Exception _){

            }
        }
        return x+Color.END;
    }
    public static String rainbow(String x){
        List<String> fields = Arrays.stream(Color.class.getDeclaredFields()).filter(field -> field.getType() == String.class).map(field->{
            try {
                return (String) field.get(null);
            } catch (IllegalAccessException e) {
                return "";
            }
        }).toList();

        char[] letters = x.toCharArray();
        StringBuilder builder = new StringBuilder();
        for(int index = 0; index < x.length(); index++ ){
            builder.append(fields.get(index % fields.size())).append(letters[index]);
        }
        return BOLD+builder+END;
    }
}
