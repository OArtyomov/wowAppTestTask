package com.wowapp;

import org.junit.Test;

public class TreePredictorTest {

    private TreePredictor treePredictor = new TreePredictor();

    @Test
    public void predict() {
        treePredictor.predict();
        treePredictor.store("P");
        System.out.println(treePredictor.predict());
        treePredictor.store("S");
        System.out.println(treePredictor.predict());
        treePredictor.store("S");
        System.out.println(treePredictor.predict());
        treePredictor.store("P");
        System.out.println(treePredictor.predict());
    }
}