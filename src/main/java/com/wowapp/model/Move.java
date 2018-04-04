package com.wowapp.model;

/*************************************************************************
 * * Yaypay CONFIDENTIAL   2018 
 * * All Rights Reserved. * *
 * NOTICE: All information contained herein is, and remains the property of Yaypay Incorporated and its suppliers, if any.
 * The intellectual and technical concepts contained  herein are proprietary to Yaypay Incorporated 
 * and its suppliers and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material  is strictly forbidden unless prior written permission is obtained  from Yaypay Incorporated.
 * Author : Oleg Artyomov
 * Date Created: 4/4/2018 5:35 PM
 */
public enum Move {
    ROCK, PAPER, SCISSORS;

    /**
     * Сравнивает текущий ход с переданным в параметре otherMove и определяет
     * победу, поражение или ничью.
     *
     * @param otherMove ход, с которым сравнивается текущий
     * @return 1 если текущий ход бьет другой, -1 если другой ход бьет текущий,
     * 0 в случае ничьей
     */
    public int compareMoves(Move otherMove) {
        // Ничья
        if (this == otherMove)
            return 0;

        switch (this) {
            case ROCK:
                return (otherMove == SCISSORS ? 1 : -1);
            case PAPER:
                return (otherMove == ROCK ? 1 : -1);
            case SCISSORS:
                return (otherMove == PAPER ? 1 : -1);
        }

        // Этот код не должен выполняться никогда

        return 0;
    }


    public static Move findByLetter(char letter){
        if (letter == 'R' || letter == 'P' || letter == 'S') {
            // Input is correct
            switch (letter) {
                case 'R':
                    return Move.ROCK;
                case 'P':
                    return Move.PAPER;
                case 'S':
                    return Move.SCISSORS;
            }
        }
      return null;
    }
}
