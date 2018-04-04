package com.wowapp;


import com.wowapp.model.Computer;
import com.wowapp.model.Move;
import com.wowapp.model.User;
import com.wowapp.prediction.TreePredictor;

public class RockPaperScissors {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;

    private TreePredictor treePredictor;


    public RockPaperScissors() {
        treePredictor = new TreePredictor();
        treePredictor.predict();
        user = new User();
        computer = new Computer(treePredictor);
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public void startGame() {
        System.out.println("ROCK, PAPER, SCISSORS!");

        // Получение ходов
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nYour choice " + userMove + ".");
        System.out.println("Computer choice " + computerMove + ".\n");
        treePredictor.store(userMove);
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