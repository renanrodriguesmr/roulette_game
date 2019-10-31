package br.com.IME.CasinoRoulette;

import java.util.HashMap;

public class ImportantConstants {
    public static final String PLAYER_STRING = "player";
    public static final String END_STRING = "end";
    public static final String BET_STRING = "bet";
    public static final String[] TYPE_OPTIONS = {ImportantConstants.PLAYER_STRING, ImportantConstants.BET_STRING, ImportantConstants.END_STRING};

    public static final String RED_BLACK = "red_black";
    public static final String HIGH_LOW = "high_low";
    public static final String EVEN_ODD = "even_odd";
    public static final String DOZEN = "dozen";
    public static final String LINE = "line";
    public static final String FIVE = "five";
    public static final String SQUARE = "square";
    public static final String STREET = "street";
    public static final String TWO_NUMBERS = "two_numbers";
    public static final String STRAIGHT_UP = "straight_up";
    public static final String[] BET_TYPE_1 = {ImportantConstants.RED_BLACK, ImportantConstants.HIGH_LOW, ImportantConstants.EVEN_ODD, ImportantConstants.DOZEN};
    public static final String[] BET_TYPE_2 = {ImportantConstants.LINE, ImportantConstants.FIVE, ImportantConstants.SQUARE, ImportantConstants.STREET, ImportantConstants.TWO_NUMBERS, ImportantConstants.STRAIGHT_UP};

    public static final int[] BLACK_VALUES = {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
    public static final int[] RED_VALUES = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,29,30,32,34};

    public static final int MAX_VALUE_ROULETTE = 36;
    public static final int MIN_VALUE_ROULETTE = 1;

    public static final HashMap<String, Integer> MULTIPLIER_FACTOR;
    static {
        MULTIPLIER_FACTOR = new HashMap<String, Integer>();
        MULTIPLIER_FACTOR.put(RED_BLACK,1);
        MULTIPLIER_FACTOR.put(HIGH_LOW,1);
        MULTIPLIER_FACTOR.put(EVEN_ODD,1);
        MULTIPLIER_FACTOR.put(DOZEN,2);
        MULTIPLIER_FACTOR.put(LINE,5);
        MULTIPLIER_FACTOR.put(FIVE,6);
        MULTIPLIER_FACTOR.put(SQUARE,8);
        MULTIPLIER_FACTOR.put(STREET,11);
        MULTIPLIER_FACTOR.put(TWO_NUMBERS,17);
        MULTIPLIER_FACTOR.put(STRAIGHT_UP,35);
    }

}
