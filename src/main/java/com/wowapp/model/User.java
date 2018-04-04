package com.wowapp.model;

import java.util.Scanner;

/*************************************************************************
 * * Yaypay CONFIDENTIAL   2018 
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated 
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 * Author : Oleg Artyomov
 * Date Created: 4/4/2018 5:34 PM
 */
public class User {
    private Scanner inputScanner;

    public User() {
        inputScanner = new Scanner(System.in);
    }

    public Move getMove() {
        // Invite to start the game
        System.out.print("ROCK, PAPER, SCISSORS - enter first letter? ");
        String userInput = getInputString();
        userInput = userInput.toUpperCase();
        Move result = Move.findByLetter(userInput.charAt(0));
        if (result == null) {
            // Ввод некорректный. Выведем запрос на ввод снова.
            return getMove();
        }
        return result;
    }

    protected String getInputString() {
        return inputScanner.nextLine();
    }

    public boolean playAgain() {
        System.out.print("Do you want to play again? Press 'Y' for continue");
        String userInput = getInputString();
        userInput = userInput.toUpperCase();
        return userInput.charAt(0) == 'Y';
    }
}

