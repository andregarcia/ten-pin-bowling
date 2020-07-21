import exception.InputParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testPerfectGame() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_perfect_game.txt").getPath();

        //then
        Main.main(new String[]{filePath});

    }

    @Test
    void testIncompleteInput() {
        //given
        String filePath = getClass().getClassLoader().getResource("test_input_partial.txt").getPath();

        //then
        assertThrows(InputParseException.class, () -> Main.main(new String[]{filePath}));

    }
}