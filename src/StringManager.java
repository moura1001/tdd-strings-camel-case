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
            StringManager.validarCaractere(i, c);
            element = StringManager.adicionarNovaPalavraNaListaSeNecessario(original, i, c, element, result);
        }
        if(result.isEmpty() || !element.isEmpty())
            result.add(element);
    }

    private static void validarCaractere(int i, char c) throws StringManagerException {
        boolean isFirstCharacter = i == 0;
        if(isFirstCharacter && Character.isDigit(c))
            throw new StringManagerException("Inválido → não deve começar com números");
    }

    private static String adicionarNovaPalavraNaListaSeNecessario(String original, int i, char current, String element, List<String> result) {
        boolean isFirstCharacter = i == 0;
        boolean isFinalCharacter = i+1 >= original.length();
        char previous = i > 0 ? original.charAt(i-1) : current;
        char next = !isFinalCharacter ? original.charAt(i+1) : current;
        if(Character.isAlphabetic(current))
            return StringManager.tratarLetra(isFirstCharacter, isFinalCharacter, previous, current, next, element, result);
        else
            return StringManager.tratarNumero(isFinalCharacter, previous, current, next, element, result);
    }

    private static String tratarNumero(boolean isFinalCharacter, char previous, char current, char next, String element, List<String> result) {
        if(!isFinalCharacter && Character.isLowerCase(previous)){
            result.add(element);
            return "" + current;
        }
        return element + current;
    }

    private static String tratarLetra(boolean isFirstCharacter, boolean isFinalCharacter, char previous, char current, char next, String element, List<String> result) {
        if(isFirstCharacter && Character.isLowerCase(next))
            return "" + Character.toLowerCase(current);
        else if(!isFirstCharacter && Character.isUpperCase(current) && !isFinalCharacter && !Character.isDigit(previous))
            return StringManager.adicionarNovaPalavraNaListaSeNecessario2(previous, current, next, element, result);
        else if(!isFinalCharacter && Character.isDigit(previous)){
            result.add(element);
            return "" + Character.toLowerCase(current);
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
