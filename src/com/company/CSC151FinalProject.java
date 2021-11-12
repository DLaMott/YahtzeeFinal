package com.company;

import java.io.File;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

abstract class Game {

    // Constants
    public final static int SCORE_NO_VALUE = -1;
    public final long seed = (new java.util.Date()).getTime();
    public final Random generator = new Random(seed);

    // Properties
    private String borderChar = "*";
    private String turnLabel = "Turn #";
    private int displayWidth = 70;
    private final int[] scores;
    private int turnCount = 0;
    private String invalidInputMessage = "*** Invalid input ***";
    private boolean turnOver = false;
    private boolean gameExit = false;
    private boolean gameComplete = false;


    // Setters and getters
    public String getBorderChar() {
        return borderChar;
    }

    public void setBorderChar(String borderChar) {
        this.borderChar = borderChar;
    }

    public String getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(String turnLabel) {
        this.turnLabel = turnLabel;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public int getScore(int index) {
        return scores[index];
    }

    public void setScore(int index, int value) {
        scores[index] = value;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public String getInvalidInputMessage() {
        return invalidInputMessage;
    }

    public void setInvalidInputMessage(String invalidInputMessage) {
        this.invalidInputMessage = invalidInputMessage;
    }

    public boolean isTurnOver() {
        return turnOver;
    }

    public void setTurnOver(boolean turnOver) {
        this.turnOver = turnOver;
    }

    public boolean isGameExit() {
        return gameExit;
    }

    public void setGameExit(boolean gameExit) {
        this.gameExit = gameExit;
    }

    public boolean isGameComplete() {
        return gameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        this.gameComplete = gameComplete;
    }

    private Random getGenerator() {
        return generator;
    }

    // Constructor
    public Game(int numberOfScores) {
        scores = new int[numberOfScores];
        Arrays.fill(scores, SCORE_NO_VALUE);
    }

    // Methods
    public final int getRandomInt() {
        return (getGenerator().nextInt(Yahtzee.MAX_NUMBER_ON_DIE) + 1);
    }

}

class Yahtzee extends Game {

    // Constants
    final static int NUMBER_OF_CATEGORIES = 14;
    final static int NUMBER_OF_CATEGORIES_TO_COMPLETE_GAME = 13;

    final static int UPPER_CATEGORY_UPPER_BOUND_INDEX = 5;
    final static int LOWER_CATEGORY_UPPER_BOUND_INDEX = 13;

    final static int NUMBER_OF_DICE = 5;

    final static int ACES_INDEX = 0;
    final static int TWOS_INDEX = 1;
    final static int THREES_INDEX = 2;
    final static int FOURS_INDEX = 3;
    final static int FIVES_INDEX = 4;
    final static int SIXES_INDEX = 5;
    final static int THREE_KIND_INDEX = 6;
    final static int FOUR_KIND_INDEX = 7;
    final static int FULL_HOUSE_INDEX = 8;
    final static int SMALL_STRAIGHT_INDEX = 9;
    final static int LARGE_STRAIGHT_INDEX = 10;
    final static int YAHTZEE_INDEX = 11;
    final static int CHANCE_INDEX = 12;
    final static int YAHTZEE_BONUS_INDEX = 13;

    final static int DIE_NUMBER_INDEX = 0;
    final static int DIE_COUNT_INDEX = 1;
    final static int DIE_COUNT_COLUMN_SIZE = 2;

    final static int FULL_HOUSE_NUMBER_IN_GROUP_1 = 2;
    final static int FULL_HOUSE_NUMBER_IN_GROUP_2 = 3;

    final static int MAX_NUMBER_ON_DIE = 6;

    final static String REROLL_MESSAGE_1 = "Enter: S for ScoreCard; D for Dice; X to Exit";
    final static String REROLL_MESSAGE_2 = "Or: A series of numbers to re-roll dice as follows:";
    final static String REROLL_MESSAGE_3 = "\t\tYou may re-roll any of the dice by entering the die #s without spaces.";
    final static String REROLL_MESSAGE_4 = "\t\tFor example, to re-roll dice #1, #3 & #4, enter 134 or enter 0 for none.";
    final static String REROLL_MESSAGE_5 = "\t\tYou have %d roll(s) left this turn.";
    final static String REROLL_MESSAGE_6 = "Which of the dice would you like to roll again? ";

    final static String CATEGORY_MESSAGE_1 = "Enter: 1-14 for category; S for ScoreCard; D for Dice; X to Exit";
    final static String CATEGORY_MESSAGE_2 = "Which category would you like to choose? ";

    final static String EXIT_RESPONSE = "X";
    final static String SCORE_CARD_RESPONSE = "S";
    final static String DISPLAY_DICE_RESPONSE = "D";
    final static String END_TURN_RESPONSE = "0";

    // Properties
    private int fullHouseScore = 25;
    private int smallStraightScore = 30;
    private int largeStraightScore = 40;
    private int yahtzeeScore = 50;
    private int yahtzeeBonusScore = 100;
    private String rollLabel = "Roll #";
    private final int[] dice = new int[NUMBER_OF_DICE];
    private int numberOfRolls = 0;
    private int maxNumberRolls = 3;
    private String welcomeMessage = "Welcome to YAHTZEE";
    private String pressEnterMessage = "Press the Enter key to continue: ";

    // Setters and getters
    public int getFullHouseScore() {
        return fullHouseScore;
    }

    public void setFullHouseScore(int fullHouseScore) {
        if (fullHouseScore >= 0)
            this.fullHouseScore = fullHouseScore;
        else
            this.fullHouseScore = 0;
    }

    public int getSmallStraightScore() {
        return smallStraightScore;
    }

    public void setSmallStraightScore(int smallStraightScore) {
        if (smallStraightScore >= 0)
            this.smallStraightScore = smallStraightScore;
        else
            this.smallStraightScore = 0;
    }

    public int getLargeStraightScore() {
        return largeStraightScore;
    }

    public void setLargeStraightScore(int largeStraightScore) {
        if (largeStraightScore >= 0)
            this.largeStraightScore = largeStraightScore;
        else
            this.largeStraightScore = 0;
    }

    public int getYahtzeeScore() {
        return yahtzeeScore;
    }

    public void setYahtzeeScore(int yahtzeeScore) {
        if (yahtzeeScore >= 0)
            this.yahtzeeScore = yahtzeeScore;
        else
            this.yahtzeeScore = 0;
    }

    public int getYahtzeeBonusScore() {
        return yahtzeeBonusScore;
    }

    public void setYahtzeeBonusScore(int yahtzeeBonusScore) {
        if (yahtzeeBonusScore >= 0)
            this.yahtzeeBonusScore = yahtzeeBonusScore;
        else
            this.yahtzeeBonusScore = 0;
    }

    public String getRollLabel() {
        return rollLabel;
    }

    public void setRollLabel(String rollLabel) {
        this.rollLabel = rollLabel;
    }

    public int getDice(int index) {
        return dice[index];
    }

    public void setDice(int index, int value) {
        dice[index] = value;
    }

    public int getNumberOfRolls() {
        return numberOfRolls;
    }

    public void setNumberOfRolls(int numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }

    public int getMaxNumberRolls() {
        return maxNumberRolls;
    }

    public void setMaxNumberRolls(int maxNumberRolls) {
        this.maxNumberRolls = maxNumberRolls;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getPressEnterMessage() {
        return pressEnterMessage;
    }

    public void setPressEnterMessage(String pressEnterMessage) {
        this.pressEnterMessage = pressEnterMessage;
    }

    // Constructor
    public Yahtzee() {
        super(NUMBER_OF_CATEGORIES);
    }

    // Methods
    public void displayTurnNumber() {

        int labelLength;
        int centerValue;

        System.out.println(getBorderChar().repeat(getDisplayWidth()));
        labelLength = (getTurnLabel() + getTurnCount() + " " + getRollLabel() + numberOfRolls).length();
        centerValue = labelLength + ((getDisplayWidth()) - labelLength) / 2;
        System.out.printf("%" + centerValue + "s", getTurnLabel() + getTurnCount() + " " + getRollLabel() + numberOfRolls);
        System.out.println();
        System.out.println(getBorderChar().repeat(getDisplayWidth()));
    }

    public void displayDice() {

        final char UNICODE_DIE_INDEX = '\u267F';

        final String DIE_LABEL_PREFIX = "Die #";
        final String DIE_LABEL_SUFFIX = " = ";

        System.out.println();

        displayTurnNumber();

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            System.out.print(DIE_LABEL_PREFIX + (i + 1) + DIE_LABEL_SUFFIX);

            System.out.print((char) ((int) UNICODE_DIE_INDEX + dice[i]));

            System.out.println(" (" + (dice[i]) + ")");
        }

        System.out.println();
    }

    //***
    //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
    //***
    //*** Overload the displayScoreSheet method by doing the following:
    //***
    //*** 1) Modify the current displayScoreSheet method below as follows:
    //***    a) Add one parameter named outStream of datatype PrintStream.
    //***    b) In displayScoreSheet method only, replace every System.out with variable outStream.
    //***           For example, System.out.println(); becomes outStream.println();
    //*** 2) Write an overloaded public method named displayScoreSheet that
    //***        returns no value (i.e. void) and receives no parameters.
    //*** 3) Write the method body as follows:
    //***    a) Invoke the overloaded, one-parameter displayScoreSheet method from Step 1 on variable
    //***          variable "this" passing the argument of System.out
    //***    b) Do not put quotes around this or System.out
    //***    c) Step 3(a) is one line of code.

    //***
    //*** Your overloaded, no-arg method for Step 2 & 3 goes here.
    //***

    // Overloaded display score sheet to send to System
    public void displayScoreSheet(){
       this.displayScoreSheet(System.out);
    }

    //***
    //*** Modify this following method (displayScoreSheet) for Step 1.
    //***


    // Display now takes a printstream argument named outStream
    public void displayScoreSheet(PrintStream outStream) {

        final String[] labels = new String[NUMBER_OF_CATEGORIES];

        labels[ACES_INDEX] = "Aces";
        labels[TWOS_INDEX] = "Twos";
        labels[THREES_INDEX] = "Threes";
        labels[FOURS_INDEX] = "Fours";
        labels[FIVES_INDEX] ="Fives";
        labels[SIXES_INDEX] = "Sixes";
        labels[THREE_KIND_INDEX] = "3 of a kind";
        labels[FOUR_KIND_INDEX] = "4 of a kind";
        labels[FULL_HOUSE_INDEX] = "Full House";
        labels[SMALL_STRAIGHT_INDEX] = "Sm. Straight";
        labels[LARGE_STRAIGHT_INDEX] = "Lg. Straight";
        labels[YAHTZEE_INDEX] = "YAHTZEE";
        labels[CHANCE_INDEX] = "Chance";
        labels[YAHTZEE_BONUS_INDEX] = "YAHTZEE BONUS";

        final int BONUS_THRESHOLD = 63;
        final int BONUS_SCORE = 35;

        final String UPPER_SECTION_LABEL = "UPPER SECTION";
        final String LOWER_SECTION_LABEL = "LOWER SECTION";
        final String UPPER_SECTION_SUBTOTAL_LABEL = "TOTAL SCORE";
        final String UPPER_SECTION_BONUS_LABEL = "BONUS if >= 63";
        final String UPPER_SECTION_TOTAL_LABEL = "TOTAL of Upper Section";
        final String LOWER_SECTION_TOTAL_LABEL = "TOTAL of Lower Section";
        final String GRAND_TOTAL_LABEL = "GRAND TOTAL";

        final String OPTION_SUFFIX_ONE_DIGIT = ")  ";
        final String OPTION_SUFFIX_TWO_DIGIT = ") ";

        final String EQUALS_LABEL = " = ";

        int upperScoreTotal = calculateUpperScore();
        int lowerScoreTotal = calculateLowerScore();

        //Printstream set to send to system
        outStream.println();
        outStream.println(UPPER_SECTION_LABEL);

        for (int i = 0; i <= UPPER_CATEGORY_UPPER_BOUND_INDEX; i++) {
            if (getScore(i) == SCORE_NO_VALUE)
                //Printstream set to send to system
                outStream.println((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i]);
            else
                //Printstream set to send to system
                outStream.println((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i] + EQUALS_LABEL + getScore(i));
        }

        if (upperScoreTotal > 0)
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_SUBTOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_SUBTOTAL_LABEL);

        if (upperScoreTotal >= BONUS_THRESHOLD)
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_BONUS_LABEL + EQUALS_LABEL + BONUS_SCORE);
        else
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_BONUS_LABEL);

        if (upperScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                //Printstream set to send to system
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + BONUS_SCORE));
            else
                //Printstream set to send to system
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_TOTAL_LABEL);
        //Printstream set to send to system
        outStream.println();
        //Printstream set to send to system
        outStream.println(LOWER_SECTION_LABEL);

        for (int i = (UPPER_CATEGORY_UPPER_BOUND_INDEX + 1); i <= LOWER_CATEGORY_UPPER_BOUND_INDEX; i++) {
            if (i < 9)
                //Printstream set to send to system
                outStream.print((i+1) + OPTION_SUFFIX_ONE_DIGIT + labels[i]);
            else
                //Printstream set to send to system
                outStream.print((i+1) + OPTION_SUFFIX_TWO_DIGIT + labels[i]);

            if (i != YAHTZEE_BONUS_INDEX) {
                if (getScore(i) != SCORE_NO_VALUE)
                    //Printstream set to send to system
                    outStream.println(EQUALS_LABEL + getScore(i));
                else
                    //Printstream set to send to system
                    outStream.println();
            }
            else {
                if (getScore(i) != SCORE_NO_VALUE)
                    //Printstream set to send to system
                    outStream.println(EQUALS_LABEL + (getScore(i) * getYahtzeeBonusScore()));
                else
                    //Printstream set to send to system
                    outStream.println();
            }
        }

        if (lowerScoreTotal > 0)
            //Printstream set to send to system
            outStream.println(LOWER_SECTION_TOTAL_LABEL + EQUALS_LABEL + lowerScoreTotal);
        else
            //Printstream set to send to system
            outStream.println(LOWER_SECTION_TOTAL_LABEL);

        if (upperScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                //Printstream set to send to system
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + BONUS_SCORE));
            else
                //Printstream set to send to system
                outStream.println(UPPER_SECTION_TOTAL_LABEL + EQUALS_LABEL + upperScoreTotal);
        else
            //Printstream set to send to system
            outStream.println(UPPER_SECTION_TOTAL_LABEL);

        if (upperScoreTotal + lowerScoreTotal > 0)
            if (upperScoreTotal >= BONUS_THRESHOLD)
                //Printstream set to send to system
                outStream.println(GRAND_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + lowerScoreTotal + BONUS_SCORE));
            else
                //Printstream set to send to system
                outStream.println(GRAND_TOTAL_LABEL + EQUALS_LABEL + (upperScoreTotal + lowerScoreTotal));
        else
            //Printstream set to send to system
            outStream.println(GRAND_TOTAL_LABEL);

        outStream.println();
    }



    public void calculateTurnScore(int scoreOption) {

        int scoreOption2Index = scoreOption - 1;

        if (scoreOption2Index <= UPPER_CATEGORY_UPPER_BOUND_INDEX)
            setScore(scoreOption2Index, calculateUpperSectionCategory(scoreOption));
        else {
            int[][] dieCount = calculateLowerSectionCategory();

            switch (scoreOption2Index) {

                case THREE_KIND_INDEX:
                    setScore(THREE_KIND_INDEX, calculateNOfKind(dieCount, 3));

                    break;

                case FOUR_KIND_INDEX:
                    setScore(FOUR_KIND_INDEX, calculateNOfKind(dieCount, 4));

                    break;

                case FULL_HOUSE_INDEX:
                    setScore(FULL_HOUSE_INDEX, calculateFullHouse(dieCount));

                    break;

                case SMALL_STRAIGHT_INDEX:
                    if (calculateNStraight(dieCount, 4))
                        setScore(SMALL_STRAIGHT_INDEX, getSmallStraightScore());

                    else
                        setScore(SMALL_STRAIGHT_INDEX, 0);

                    break;

                case LARGE_STRAIGHT_INDEX:
                    if (calculateNStraight(dieCount, 5))
                        setScore(LARGE_STRAIGHT_INDEX, getLargeStraightScore());
                    else
                        setScore(LARGE_STRAIGHT_INDEX, 0);

                    break;

                case YAHTZEE_INDEX:
                    if (dieCount.length == 1)
                        setScore(YAHTZEE_INDEX, getYahtzeeScore());
                    else
                        setScore(YAHTZEE_INDEX, 0);

                    setScore(YAHTZEE_BONUS_INDEX, 0);

                    break;

                case CHANCE_INDEX:
                    setScore(CHANCE_INDEX, calculateChance());

                    break;

                case YAHTZEE_BONUS_INDEX:
                    if (getScore(YAHTZEE_INDEX) == SCORE_NO_VALUE) {
                        setScore(YAHTZEE_INDEX, calculateYahtzee());
                        setScore(YAHTZEE_BONUS_INDEX, 0);
                    }
                    else if (getScore(YAHTZEE_INDEX) == getYahtzeeScore() && calculateYahtzee() == getYahtzeeScore())
                        setScore(YAHTZEE_BONUS_INDEX, getScore(YAHTZEE_BONUS_INDEX) + 1);

                    break;
            }
        }
    }

    public int[][] calculateLowerSectionCategory() {

        int[] tempDice = new int[NUMBER_OF_DICE];
        System.arraycopy(dice, 0, tempDice, 0, dice.length);
        Arrays.sort(tempDice);

        int[][] dieCount = new int[1][DIE_COUNT_COLUMN_SIZE];
        dieCount[dieCount.length-1][DIE_NUMBER_INDEX] = tempDice[0];
        dieCount[dieCount.length-1][DIE_COUNT_INDEX] = 1;

        for (int i = 1; i < tempDice.length; i++) {
            if (tempDice[i] == dieCount[dieCount.length-1][DIE_NUMBER_INDEX]) {
                dieCount[dieCount.length-1][DIE_COUNT_INDEX]++;
            }
            else {
                int[][] tempDieCount = new int[dieCount.length + 1][DIE_COUNT_COLUMN_SIZE];
                System.arraycopy(dieCount, 0, tempDieCount, 0, dieCount.length);

                tempDieCount[tempDieCount.length-1][DIE_NUMBER_INDEX] = tempDice[i];
                tempDieCount[tempDieCount.length-1][DIE_COUNT_INDEX] = 1;

                dieCount = tempDieCount;
            }
        }

        return dieCount;
    }

    public int calculateUpperSectionCategory(int dieNumber) {

        int score = 0;

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            if (dice[i] == dieNumber)
                score += dice[i];
        }

        return score;
    }

    public int calculateNOfKind(int[][] dieCount, int nKind) {

        int score = 0;
        boolean isNKind = false;

        for (int i = 0; i < dieCount.length; i++) {
            if (dieCount[i][DIE_COUNT_INDEX] >= nKind)
                isNKind = true;
        }

        if (isNKind)
            for (int i = 0; i < NUMBER_OF_DICE; i++)
                score += dice[i];

        return score;
    }

    public int calculateFullHouse(int[][] dieCount) {

        int score = 0;

        if (dieCount.length == 2 &&
                ((dieCount[0][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_1 &&
                        dieCount[1][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_2)
                        ||
                        (dieCount[1][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_1 &&
                                dieCount[0][DIE_COUNT_INDEX] == FULL_HOUSE_NUMBER_IN_GROUP_2)))
            score = getFullHouseScore();

        return score;
    }

    public boolean calculateNStraight(int[][] dieCount, int nStraight) {

        boolean isNStraight = false;
        int n = 1;

        for (int i = 0; i < dieCount.length-1; i++) {
            if (dieCount[i][DIE_NUMBER_INDEX] == dieCount[i+1][DIE_NUMBER_INDEX]-1)
                n++;
            else
                n = 1;
        }

        if (n >= nStraight)
            isNStraight = true;

        return isNStraight;
    }

    public int calculateYahtzee() {

        int score = 0;
        boolean isYahtzee = true;

        for (int i = 0; i < NUMBER_OF_DICE - 1; i++) {
            if (dice[i] != dice[i+1]) {
                isYahtzee = false;
            }
        }

        if (isYahtzee)
            score = getYahtzeeScore();

        return score;
    }

    public int calculateChance() {

        int chance = 0;

        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            chance += dice[i];
        }

        return chance;
    }

    public int calculateUpperScore() {

        int score = 0;

        for (int i = 0; i <= UPPER_CATEGORY_UPPER_BOUND_INDEX; i++)
            if (getScore(i) != SCORE_NO_VALUE)
                score += getScore(i);

        return score;
    }

    public int calculateLowerScore() {

        int score = 0;

        for (int i = UPPER_CATEGORY_UPPER_BOUND_INDEX + 1; i <= LOWER_CATEGORY_UPPER_BOUND_INDEX; i++)
            if (i != YAHTZEE_BONUS_INDEX) {
                if (getScore(i) != SCORE_NO_VALUE)
                    score += getScore(i);
            }
            else {
                if (getScore(i) != SCORE_NO_VALUE)
                    score += (getScore(i) * getYahtzeeBonusScore());
            }

        return score;
    }

    public boolean isCategoryUsed(int scoreOption) {

        boolean used = false;

        if ((scoreOption - 1) != YAHTZEE_BONUS_INDEX) {
            if (getScore(scoreOption - 1) != SCORE_NO_VALUE)
                used = true;
        }
        else {
            if (getScore(YAHTZEE_INDEX) == getYahtzeeScore() && calculateYahtzee() != getYahtzeeScore())
                used = true;
        }

        return used;
    }

    public boolean isGameOver() {

        boolean gameOver = true;

        for (int i = 0; i < NUMBER_OF_CATEGORIES_TO_COMPLETE_GAME; i++)
            if (getScore(i) == SCORE_NO_VALUE)
                gameOver = false;

        return gameOver;
    }

    public void displayErrorMessage() {

        System.out.println();

        System.out.println(getBorderChar().repeat(getInvalidInputMessage().length()));
        System.out.println(getInvalidInputMessage());
        System.out.println(getBorderChar().repeat(getInvalidInputMessage().length()));
    }

}

public class CSC151FinalProject {

    public static void main(String[] args) {

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Declare a variable named GameOfYahztee.
        //*** 2) Create a new instance (using the new operator) of class Yahtzee and assign the instance to
        //***        variable GameOfYahztee.
        //***
        //*** This is one line of code.

        // GameOfYahtzee is a new implementation of the class Yahtzee
        Yahtzee GameOfYahtzee = new Yahtzee();

        final int ASCII_DICE_INDEX = 49;
        final String OUTPUT_FILE_NAME = "output.txt";
        final String OUTPUT_FILE_ERROR_MESSAGE = "Error opening file: ";

        String dice2Reroll;
        String scoreOptionInput;
        int scoreOption = 0;

        Scanner input = new Scanner(System.in);

        System.out.println();

        //***
        //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
        //***
        //*** 1) Invoke method getWelcomeMessage on variable GameOfYahztee passing no arguments
        //***        sending the return value to the console.
        //***
        //*** This is one line of code.

        // Display the welcome message using the get welcome method
       System.out.println(GameOfYahtzee.getWelcomeMessage());

        do {
            System.out.println();

            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Invoke method getPressEnterMessage on variable GameOfYahztee passing no arguments
            //***        sending the return value to the console.
            //***
            //*** This is one line of code.

            // Display press enter message by using the getter method
            System.out.println(GameOfYahtzee.getPressEnterMessage());
            input.nextLine();

            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Add 1 to the value of property turnCount by invoking the getter and setter of property turnCount
            //***        on variable GameOfYahztee.
            //***
            //*** This can be completed in as little as one line of code. If you prefer, it can be completed in more
            //***     than one line of code.

            //Getter and setter methods called to update player turn count.
           // GameOfYahtzee.getTurnCount();
            GameOfYahtzee.setTurnCount(GameOfYahtzee.getTurnCount() + 1);

            for (int i = 0; i < Yahtzee.NUMBER_OF_DICE; i++) {

                //***
                //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
                //***
                //*** 1) Invoke method getRandomInt on variable GameOfYahztee passing no arguments.
                //*** 2) Invoke method setDice on variable GameOfYahztee.
                //***    a) The first argument being the for-loop control variable i.
                //***    b) The second argument being the return value of Step 1.
                //***
                //*** This can be completed in as little as one line of code. If you prefer, it can be completed in more
                //***     than one line of code.

                //Setter method called to update die.
                GameOfYahtzee.setDice(i, GameOfYahtzee.getRandomInt());

            }
            //***
            //*** INSTRUCTIONS FOR CODE FOR YOU TO WRITE
            //***
            //*** 1) Invoke method setNumberOfRolls on variable GameOfYahztee passing the argument of 1.
            //*** 2) Invoke method displayDice on variable GameOfYahztee passing no arguments.
            //*** 3) Invoke method setTurnOver on variable GameOfYahztee passing the argument of false.
            //***
            //*** This is three lines of code.
            //***
            //*** Setter methods called to set rolls and turnover to false. Die is displayed.
            //***
            GameOfYahtzee.setNumberOfRolls(1);
            GameOfYahtzee.displayDice();
            GameOfYahtzee.setTurnOver(false);

            do {
                System.out.println();
                System.out.println(Yahtzee.REROLL_MESSAGE_1);
                System.out.println();

                System.out.println(Yahtzee.REROLL_MESSAGE_2);
                System.out.println(Yahtzee.REROLL_MESSAGE_3);
                System.out.println(Yahtzee.REROLL_MESSAGE_4);

                System.out.println();
                System.out.printf(Yahtzee.REROLL_MESSAGE_5, (GameOfYahtzee.getMaxNumberRolls() - GameOfYahtzee.getNumberOfRolls()));
                System.out.println();

                System.out.println();
                System.out.print(Yahtzee.REROLL_MESSAGE_6);

                dice2Reroll = input.nextLine();
                dice2Reroll = dice2Reroll.trim();

                switch (dice2Reroll.toUpperCase()) {

                    // Constant from Yahtzee
                    case Yahtzee.EXIT_RESPONSE:
                        // Setters used to set turn and game exit
                        GameOfYahtzee.setTurnOver(false);
                        GameOfYahtzee.setGameExit(true);
                        break;
                    // Constant from Yahtzee
                    case Yahtzee.SCORE_CARD_RESPONSE:
                        // displays the score sheet
                        GameOfYahtzee.displayScoreSheet();
                        break;
                    // Constant from Yahtzee
                    case Yahtzee.DISPLAY_DICE_RESPONSE:
                        // displays the die
                        GameOfYahtzee.displayDice();
                        break;
                    // Constant from Yahtzee
                    case Yahtzee.END_TURN_RESPONSE:
                        // setter method to set turn over to true
                        GameOfYahtzee.setTurnOver(true);
                        break;
                    // Constant from Yahtzee
                    case "":
                        // error message displayed if blank
                        GameOfYahtzee.displayErrorMessage();
                        break;

                    default:
                        dice2Reroll = dice2Reroll.replace(" ", "");
                        String checkDice2Reroll = dice2Reroll;

                        for (int i = 1; i <= Yahtzee.NUMBER_OF_DICE; i++) {
                            checkDice2Reroll = checkDice2Reroll.replaceFirst(String.valueOf(i), " ");
                        }

                        if (checkDice2Reroll.isBlank()) {
                            for (int i = 0; i < dice2Reroll.length(); i++) {
                                GameOfYahtzee.setDice(((int) dice2Reroll.charAt(i)) - ASCII_DICE_INDEX, GameOfYahtzee.getRandomInt());
                            }

                            // Setter and getter methods to update the number of rolls in game.
                            GameOfYahtzee.setNumberOfRolls(GameOfYahtzee.getNumberOfRolls() + 1);
                            GameOfYahtzee.displayDice();

                            // If statement to determine if max number of rolls has been met
                            if (GameOfYahtzee.getNumberOfRolls() == GameOfYahtzee.getMaxNumberRolls())
                                GameOfYahtzee.setTurnOver(true);
                            } else {
                                GameOfYahtzee.displayErrorMessage();
                            }


                        } // This is the closing curly brace for the switch statement.
                }
                while (!GameOfYahtzee.isTurnOver()) ;

                if (!GameOfYahtzee.isGameExit()) {

                   // Displays score sheet using method
                    GameOfYahtzee.displayScoreSheet();

                    boolean isValidEntry;
                    boolean categoryPicked;
                    boolean continuePrompting;

                    do {

                        isValidEntry = true;
                        categoryPicked = false;

                        System.out.println();
                        System.out.println(Yahtzee.CATEGORY_MESSAGE_1);
                        System.out.println();

                        System.out.print(Yahtzee.CATEGORY_MESSAGE_2);

                        scoreOptionInput = input.nextLine();
                        scoreOptionInput = scoreOptionInput.trim();

                        scoreOption = 0;

                        switch (scoreOptionInput.toUpperCase()) {

                            // If exit response constant set game exit to true
                            case Yahtzee.EXIT_RESPONSE:
                                GameOfYahtzee.setGameExit(true);
                                break;
                            // If score card constant display score sheet
                            case Yahtzee.SCORE_CARD_RESPONSE:
                                GameOfYahtzee.displayScoreSheet();
                                break;
                            // If display die constant display die
                            case Yahtzee.DISPLAY_DICE_RESPONSE:
                                GameOfYahtzee.displayDice();
                                break;
                            // If blank constant display error
                            case "":
                                GameOfYahtzee.displayErrorMessage();
                                break;

                            default:
                                for (int x = 0; x < scoreOptionInput.length(); x++)
                                    if (!(Character.isDigit(scoreOptionInput.charAt(x)))) {
                                        isValidEntry = false;
                                        GameOfYahtzee.displayErrorMessage();
                                    }

                                if (isValidEntry) {
                                    scoreOption = Integer.parseInt(scoreOptionInput);

                                    if (scoreOption < 1 || scoreOption > Yahtzee.NUMBER_OF_CATEGORIES) {
                                        isValidEntry = false;
                                        GameOfYahtzee.displayErrorMessage();
                                    }
                                }

                                if (isValidEntry && GameOfYahtzee.isCategoryUsed(scoreOption)) {
                                    isValidEntry = false;
                                    GameOfYahtzee.displayErrorMessage();
                                }

                                if (isValidEntry) {
                                    categoryPicked = true;
                                    GameOfYahtzee.calculateTurnScore(scoreOption);
                                    GameOfYahtzee.displayScoreSheet();
                                    GameOfYahtzee.setGameComplete(GameOfYahtzee.isGameOver());
                                }
                        } // This is the closing curly brace for the switch statement.

                        continuePrompting = !GameOfYahtzee.isGameExit() && !categoryPicked;

                    } while (continuePrompting);

                } // This is the closing curly brace for the if-statement.

            } while (!GameOfYahtzee.isGameExit() && !GameOfYahtzee.isGameComplete());

            //display score sheet with method
            GameOfYahtzee.displayScoreSheet();

            // New instance of File set to file constant
            File outputFile = new File(OUTPUT_FILE_NAME);
       
            try {
                PrintStream outputStream = new PrintStream(outputFile);
                GameOfYahtzee.displayScoreSheet(outputStream);
            } catch (Exception ex) {
                // Prints File constant plus the name
                System.out.println(OUTPUT_FILE_ERROR_MESSAGE + outputFile.getName());
                ex.printStackTrace();
            }
        }
    }
