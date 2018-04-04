package com.wowapp.model;

import com.wowapp.prediction.TreePredictor;

/*************************************************************************
 * * Yaypay CONFIDENTIAL   2018 
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated 
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 * Author : Oleg Artyomov
 * Date Created: 4/4/2018 5:37 PM
 */
public class Computer {

    private TreePredictor treePredictor;

    public Computer(TreePredictor treePredictor) {
        this.treePredictor = treePredictor;
    }

    public Move getMove() {
        //We are calculating computer way depends on external player way
        return treePredictor.predict();
    }
}