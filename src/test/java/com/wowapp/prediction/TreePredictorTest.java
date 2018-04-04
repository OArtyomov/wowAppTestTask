package com.wowapp.prediction;

import com.wowapp.model.Move;
import org.junit.Ignore;
import org.junit.Test;

public class TreePredictorTest {

    private TreePredictor treePredictor = new TreePredictor();

    @Test
    @Ignore
    public void predict() {
        treePredictor.predict();
        treePredictor.store(Move.PAPER);
        System.out.println(treePredictor.predict());
        treePredictor.store(Move.SCISSORS);
        System.out.println(treePredictor.predict());
        treePredictor.store(Move.SCISSORS);
        System.out.println(treePredictor.predict());
        treePredictor.store(Move.PAPER);
        System.out.println(treePredictor.predict());
    }
}