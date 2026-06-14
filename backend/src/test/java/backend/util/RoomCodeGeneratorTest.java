package backend.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class RoomCodeGeneratorTest {

    @Test
    void testCodeLength() {

        String code = RoomCodeGenerator.generate();

        assertEquals(6, code.length());
    }

    @Test
    void testCharactersValid() {

        String code = RoomCodeGenerator.generate();

        assertTrue(code.matches("[A-Z0-9]{6}"));
    }

    @Test
    void testUniquenessBasic() {

        Set<String> codes = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            codes.add(RoomCodeGenerator.generate());
        }

        assertTrue(codes.size() > 900);
    }
}