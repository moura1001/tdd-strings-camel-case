import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringManagerTest {
    @ParameterizedTest
    @ValueSource(strings = {"nome", "Nome"})
    void converteUmaStringSimplesSendoCaseInsensitiveParaUmaListaDeTamanho1ContendoPalavrasMinusculas(String input) {
        List<String> result = StringManager.converterCamelCase(input);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(input.toLowerCase(), result.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"nomeComposto", "NomeComposto"})
    void converteUmaStringCompostaSendoCaseInsensitiveParaUmaListaDeTamanho2ContendoPalavrasMinusculas(String input) {
        List<String> result = StringManager.converterCamelCase(input);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("nome", result.get(0));
        assertEquals("composto", result.get(1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"CPF", "numeroCPF", "numeroCPFContribuinte"})
    void trataPalavrasMaiusculasNumaStringComoElementosDistintosQueDevemEstaNaLista(String input) {
        List<String> result = StringManager.converterCamelCase(input);
        assertNotNull(result);
        assertTrue(result.contains("CPF"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"recupera10Primeiros"})
    void converteUmaStringComNumerosParaUmaListaDeTamanho3(String input) {
        List<String> result = StringManager.converterCamelCase(input);
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("recupera", result.get(0));
        assertEquals("10", result.get(1));
        assertEquals("primeiros", result.get(2));
    }
}