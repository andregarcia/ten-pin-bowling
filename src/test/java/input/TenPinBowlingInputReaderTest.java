package input;

import domain.Roll;
import exception.InputParseException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TenPinBowlingInputReaderTest {


    @Test
    void testReadFile() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_partial.txt").getPath();

        //when
        final List<Roll> rollList = TenPinBowlingInputReader.readFile(filePath);

        //then
        assertEquals(4, rollList.size());
        assertEquals("Jeff", rollList.get(0).getPlayer());
        assertEquals(10, rollList.get(0).getScore());
        assertEquals(false, rollList.get(0).getFault());

        assertEquals("John", rollList.get(1).getPlayer());
        assertEquals(3, rollList.get(1).getScore());
        assertEquals(false, rollList.get(1).getFault());

        assertEquals("John", rollList.get(2).getPlayer());
        assertEquals(7, rollList.get(2).getScore());
        assertEquals(false, rollList.get(2).getFault());

        assertEquals("Jeff", rollList.get(3).getPlayer());
        assertEquals(0, rollList.get(3).getScore());
        assertEquals(true, rollList.get(3).getFault());

    }

    @Test
    void testReadFileInvalidInput1() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_invalid_score_1.txt").getPath();

        //when
        assertThrows(InputParseException.class, () -> TenPinBowlingInputReader.readFile(filePath));
    }

    @Test
    void testReadFileInvalidInput2() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_invalid_score_2.txt").getPath();

        //when
        assertThrows(InputParseException.class, () -> TenPinBowlingInputReader.readFile(filePath));
    }

    @Test
    void testReadFileInvalidInput3() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_invalid_score_3.txt").getPath();

        //when
        assertThrows(InputParseException.class, () -> TenPinBowlingInputReader.readFile(filePath));
    }
}