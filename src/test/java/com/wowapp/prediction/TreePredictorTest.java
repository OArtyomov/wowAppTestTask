package com.wowapp.prediction;

import com.wowapp.model.Move;
import com.wowapp.prediction.TreePredictor;
import org.junit.Test;

public class TreePredictorTest {

    private TreePredictor treePredictor = new TreePredictor();

    @Test
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