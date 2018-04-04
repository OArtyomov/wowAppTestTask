package com.wowapp.model;

import org.junit.Test;

import static com.wowapp.model.Move.PAPER;
import static com.wowapp.model.Move.ROCK;
import static com.wowapp.model.Move.SCISSORS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MoveTest {

    @Test
    public void compareMoves() {
        assertEquals(ROCK.compareMoves(ROCK), 0);
        assertEquals(ROCK.compareMoves(PAPER), -1);
        assertEquals(ROCK.compareMoves(SCISSORS), 1);


        assertEquals(PAPER.compareMoves(PAPER), 0);
        assertEquals(PAPER.compareMoves(SCISSORS), -1);
        assertEquals(PAPER.compareMoves(ROCK), 1);

        assertEquals(SCISSORS.compareMoves(SCISSORS), 0);
        assertEquals(SCISSORS.compareMoves(ROCK), -1);
        assertEquals(SCISSORS.compareMoves(PAPER), 1);

    }

    @Test
    public void findByLetter() {
        assertEquals(Move.findByLetter('R'), ROCK);
        assertEquals(Move.findByLetter('P'), PAPER);
        assertEquals(Move.findByLetter('S'), SCISSORS);

        assertNull(Move.findByLetter('r'));
        assertNull(Move.findByLetter('p'));
        assertNull(Move.findByLetter('c'));
    }
}