package com.wowapp.model;

import com.wowapp.prediction.TreePredictor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.wowapp.model.Move.PAPER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ComputerTest {

    @InjectMocks
    private Computer computer;

    @Mock
    private TreePredictor predictor;

    @Test
    public void getMoveWhichFullyDelegateToPredictor() {
        Move result = PAPER;
        when(predictor.predict()).thenReturn(result);
        assertEquals(computer.getMove(), result);
        verify(predictor, times(1)).predict();
    }
}