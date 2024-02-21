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
}