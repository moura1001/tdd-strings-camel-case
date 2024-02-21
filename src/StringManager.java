import java.util.ArrayList;
import java.util.List;

public class StringManager {
    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<>(4);
        String element = "";
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            if(i > 0 && Character.isUpperCase(c)){
                result.add(element.toLowerCase());
                element = "" + c;
                continue;
            }
            element += c;
        }
        if(result.isEmpty() || !element.isEmpty())
            result.add(element.toLowerCase());
        return result;
    }
}
