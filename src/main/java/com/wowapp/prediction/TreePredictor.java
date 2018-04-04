package com.wowapp.prediction;

import com.wowapp.model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.wowapp.model.Move.PAPER;
import static com.wowapp.model.Move.ROCK;
import static com.wowapp.model.Move.SCISSORS;

public class TreePredictor {

    private Random random = new Random();

    private List<Move> choices = newArrayList(ROCK, PAPER, SCISSORS);

    //Computer move
    private Integer prevChoice;

    // Player move
    private Move prevMove;

    private Integer prevRes;

    private double[][] dataArray = new double[3][3];

    private List<int[]> gameMat = newArrayList();

    private List<int[]> rollMat = newArrayList();

    //It is our choices to win predicted choice
    private int[] beatMat = new int[]{1, 2, 0};

    private boolean playrand;

    public TreePredictor() {

        gameMat.add(new int[]{1, 0, 2});
        gameMat.add(new int[]{2, 1, 0});
        gameMat.add(new int[]{0, 2, 1});

        rollMat.add(new int[]{0, 1, 2});
        rollMat.add(new int[]{2, 0, 1});
        rollMat.add(new int[]{1, 2, 0});

        //self.losecount = 0;
        playrand = false;
        //self.mult = 1.0
    }

    private int gameRes(Move c1, Move c2) {
        int i1 = choices.indexOf(c1);
        int i2 = choices.indexOf(c2);
        return gameMat.get(i1)[i2];
    }

    private int indexOfInArray(int[] row, int element) {
        int index = -1;
        for (int currentItem : row) {
            index++;
            if (currentItem == element) {
                return index;
            }
        }
        throw new RuntimeException("Element not found");
    }

    private int rollInd(int i1, int inc) {
        int[] row = rollMat.get(i1);
        return indexOfInArray(row, inc);

    }

    private int getRollbyInd(int i1, int i2) {
        return rollMat.get(i1)[i2];
    }


    public Move predict() {
        if (prevChoice == null || prevRes == null) {
            int startIndex = new Random().nextInt(3);
            Move ret = choices.get(startIndex);
            prevMove = ret;
            return ret;
        }
        double[] arr = dataArray[prevRes];


        int predictedroll = weighted_choice(new int[]{0, 1, 2}, arr);

        int predictedchoice = rollInd(prevChoice, predictedroll);

        int choice = beatMat[predictedchoice];

        if (playrand) {
            playrand = false;

            choice = new Random().nextInt(3);
        }

        prevMove = choices.get(choice);
        return choices.get(choice);
    }

    private int weighted_choice(int[] values, double[] weights) {
        double total = 0.0;
        ArrayList<Double> cum_weights = new ArrayList<Double>();
        for (Double w : weights) {
            total = total + w;
            cum_weights.add(total);
        }
        return values[getIndex(total, cum_weights)];
    }


    public void store(Move c) {

        int i1 = choices.indexOf(c);

        if (!(prevChoice == null || prevRes == null)) {
            int roll = getRollbyInd(prevChoice, i1);
            for (int i = 0, n = 3; i < n; i++) {
                for (int j = 0, k = 3; j < k; j++) {
                    dataArray[i][j] = dataArray[i][j] * 0.9;
                }
            }
            dataArray[prevRes][roll] = dataArray[prevRes][roll] + 1;
        }


        prevChoice = i1;
        prevRes = gameRes(c, prevMove);
    }

    private int getIndex(double total, ArrayList<Double> cum_weights) {
        double value = random.nextDouble() * total;
        int i = 0;
        for (Double currentWeight : cum_weights) {
            if (value >= currentWeight) {
                return i;
            }
        }
        throw new RuntimeException("Index not found");
    }
}
