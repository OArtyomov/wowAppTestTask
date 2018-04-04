package com.wowapp.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    @Spy
    private User user;

    @Test
    public void testGetMoveForValidString() {
        doReturn("R").when(user).getInputString();
        assertEquals(user.getMove(), Move.ROCK);
        verify(user, times(1)).getInputString();
    }

    @Test
    public void testGetMoveForInValidString() {
        doReturn("Z").doReturn("R").when(user).getInputString();
        assertEquals(user.getMove(), Move.ROCK);
        verify(user, times(2)).getInputString();
    }
}