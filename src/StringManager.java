import java.util.ArrayList;
import java.util.List;

public class StringManager {
    public static List<String> converterCamelCase(String original) {
        List<String> result = new ArrayList<>(4);
        StringManager.dividirPalavrasPorCaracterMaiusculoEAdicionarNaLista(original, result);
        return result;
    }

    private static void dividirPalavrasPorCaracterMaiusculoEAdicionarNaLista(String original, List<String> result) {
        String element = "";
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            element = StringManager.adicionarNovaPalavraNaListaSeNecessario(original, i, c, element, result);
        }
        if(result.isEmpty() || !element.isEmpty())
            result.add(element);
    }

    private static String adicionarNovaPalavraNaListaSeNecessario(String original, int i, char current, String element, List<String> result) {
        boolean isFirstCharacter = i == 0;
        boolean isFinalCharacter = i+1 >= original.length();
        char previous = i > 0 ? original.charAt(i-1) : current;
        char next = !isFinalCharacter ? original.charAt(i+1) : current;
        if(isFirstCharacter && Character.isLowerCase(next)) {
            return "" + Character.toLowerCase(current);
        } else if(!isFirstCharacter && Character.isUpperCase(current) && !isFinalCharacter){
            return StringManager.adicionarNovaPalavraNaListaSeNecessario2(previous, current, next, element, result);
        }
        return element + current;
    }

    private static String adicionarNovaPalavraNaListaSeNecessario2(char previous, char current, char next, String element, List<String> result) {
        if(Character.isLowerCase(next)) {
            return StringManager.adicionarNovaPalavraNaLista(previous, current, next, element, result);
        } else if(Character.isUpperCase(next) && Character.isLowerCase(previous)) {
            return StringManager.adicionarNovaPalavraNaLista(previous, current, next, element, result);
        }
        return element + current;
    }

    private static String adicionarNovaPalavraNaLista(char previous, char current, char next, String element, List<String> result) {
        result.add(element);
        if(Character.isUpperCase(current) && Character.isLowerCase(previous) && Character.isLowerCase(next)) {
            return "" + Character.toLowerCase(current);
        } else if(Character.isUpperCase(current) && Character.isUpperCase(next)) {
            return "" + current;
        }
        return element + current;
    }
}
