package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void testIsStrike() throws Exception {
        Frame frameIsStrike = new Frame(1, new Roll("John", 10, false));
        assertTrue(frameIsStrike.isStrike());

        Frame frameIsNotStrike = new Frame(1, new Roll("John", 3, false), new Roll("John", 4, false));
        assertFalse(frameIsNotStrike.isStrike());
    }

    @Test
    void testIsSpare() throws Exception {
        Frame frameIsSpare = new Frame(1, new Roll("John", 3, false), new Roll("John", 7, false));
        assertTrue(frameIsSpare.isSpare());

        Frame frameIsNotSpare = new Frame(1, new Roll("John", 3, false), new Roll("John", 4, false));
        assertFalse(frameIsNotSpare.isStrike());
    }

    @Test
    void getScore() throws Exception {
        Frame frame1 = new Frame(1, new Roll("John", 3, false), new Roll("John", 7, false));
        Frame frame2 = new Frame(2, new Roll("John", 10, false));
        Frame frame3 = new Frame(3, new Roll("John", 4, false), new Roll("John", 3, false));
        Frame frame4 = new Frame(4, new Roll("John", 2, false), new Roll("John", 5, false));
        Frame frame5 = new LastFrame(5, new Roll("John", 0, true), new Roll("John", 10, false),new Roll("John", 3, false));
        frame1.setNext(frame2);
        frame2.setNext(frame3);
        frame3.setNext(frame4);
        frame4.setNext(frame5);

        frame2.setPrevious(frame1);
        frame3.setPrevious(frame2);
        frame4.setPrevious(frame3);
        frame5.setPrevious(frame4);

        assertEquals(20, frame1.getScore());
        assertEquals(37, frame2.getScore());
        assertEquals(44, frame3.getScore());
        assertEquals(51, frame4.getScore());
        assertEquals(64, frame5.getScore());
    }
}