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
            element = StringManager.adicionarNovaPalavraNaListaSeNecessario(i, c, element, result);
            element += c;
        }
        if(result.isEmpty() || !element.isEmpty())
            result.add(element.toLowerCase());
    }

    private static String adicionarNovaPalavraNaListaSeNecessario(int i, char c, String element, List<String> result) {
        if(i > 0 && Character.isUpperCase(c)){
            result.add(element.toLowerCase());
            element = "";
        }
        return element;
    }
}
