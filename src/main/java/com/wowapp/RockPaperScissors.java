package com.wowapp;


import java.util.Scanner;

public class RockPaperScissors {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;

    private TreePredictor treePredictor;

    private enum Move {
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
    }

    private class User {
        private Scanner inputScanner;

        public User() {
            inputScanner = new Scanner(System.in);
        }

        public Move getMove() {
            // Выведем запрос на ввод
            System.out.print("ROCK, PAPER, SCISSORS - enter first letter? ");

            // Прочитаем ввод пользователя
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'R' || firstLetter == 'P' || firstLetter == 'S') {
                // Ввод корректный
                switch (firstLetter) {
                    case 'R':
                        return Move.ROCK;
                    case 'P':
                        return Move.PAPER;
                    case 'S':
                        return Move.SCISSORS;
                }
            }

            // Ввод некорректный. Выведем запрос на ввод снова.
            return getMove();
        }

        public boolean playAgain() {
            System.out.print("Do you want to play again? ");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Y';
        }
    }

    private class Computer {
        public Move getMove() {
            String computerWay = treePredictor.predict();
            if (computerWay != null) {
                for (Move currentMode : Move.values()) {
                    if (currentMode.name().startsWith(computerWay)) {
                        return currentMode;
                    }
                }
            }
            throw new RuntimeException();
        }
    }

    public RockPaperScissors() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
        treePredictor = new TreePredictor();
        treePredictor.predict();
    }

    public void startGame() {
        System.out.println("ROCK, PAPER, SCISSORS!");

        // Получение ходов
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nYour choice " + userMove + ".");
        System.out.println("Computer choice " + computerMove + ".\n");
        String firstLetter = userMove.name().substring(0, 1);
        treePredictor.store(firstLetter);
        // Сравнение ходов и определение победителя
        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0: // Ничья
                System.out.println("Tie!");
                break;
            case 1: // Победил игрок
                System.out.println(userMove + " beats " + computerMove + ". You are the winner!");
                userScore++;
                break;
            case -1: // Победил компьютер
                System.out.println(computerMove + " beats " + userMove + ". You lose the game.");
                computerScore++;
                break;
        }
        numberOfGames++;

        // Предложим пользователю сыграть еще раз
        if (user.playAgain()) {
            System.out.println();
            startGame();
        } else {
            printGameStats();
        }
    }

    /**
     * Print statistic. Tie means 0.5 of victory
     *
     */
    private void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;

        // Вывод линии
        System.out.print("+");
        printDashes(68);
        System.out.println("+");

        // Вывод заголовков таблицы
        System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
                "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");

        // Вывод линии
        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");

        // Вывод значений
        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, numberOfGames, percentageWon * 100);

        // Вывод линии
        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        RockPaperScissors game = new RockPaperScissors();
        game.startGame();
    }
}