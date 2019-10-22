package br.com.IME.CasinoRoulette;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bet {
    private boolean isUnitaryBet;
    private String type;
    private int unitaryBetValue;
    private int unitaryBetCoins;
    private HashMap<Integer, Integer> betItems;


    Bet(String type, int[] intParamsList) throws CustomException{
        if (!Player.getIsLogged()) throw new CustomException("Player not found");
        if (Arrays.asList(ImportantConstants.BET_TYPE_1).contains(type)){
            this.isUnitaryBet = true;
            this.unitaryBetValue = intParamsList[0];
            this.unitaryBetCoins = intParamsList[1];
            this.type = type;
        } else if (Arrays.asList(ImportantConstants.BET_TYPE_2).contains(type)) {
            this.isUnitaryBet = false;
            this.betItems = new HashMap<Integer, Integer>();
            this.type = type;
            for (int i = 0; i < intParamsList.length; i = i+2){
                this.betItems.put(intParamsList[i], intParamsList[i+1]);
            }
        } else throw new InvalidParameterException();
    }

    private boolean isADesiredResult(int value){
        if (Objects.equals(this.type, ImportantConstants.RED_BLACK)){
            return (unitaryBetValue == 0) ? Arrays.binarySearch(ImportantConstants.RED_VALUES, value) > 0 : Arrays.binarySearch(ImportantConstants.BLACK_VALUES, value) > 0;
        }
        if (Objects.equals(this.type, ImportantConstants.HIGH_LOW)){
            return (unitaryBetValue == 0) == (value > 18);
        }
        if (Objects.equals(this.type, ImportantConstants.EVEN_ODD)){
            return (unitaryBetValue == 0) == (value%2 == 0);
        }
        if (Objects.equals(this.type, ImportantConstants.DOZEN)) {
            return (unitaryBetValue == 0) ? (value < 13) : ((unitaryBetValue == 1) ? (value > 12 && value < 25) : (value > 24));
        }
        return false;
    }

    private int runRoulette() {
        int max = ImportantConstants.MAX_VALUE_ROULETTE;
        int min = ImportantConstants.MIN_VALUE_ROULETTE;
        int value = (int)(Math.random()*((max-min)+1))+min;
        return value;
    }

    private int totalBetCoins() {
        if(this.isUnitaryBet) return this.unitaryBetCoins;
        int total = 0;
        for (Map.Entry<Integer, Integer> entry: this.betItems.entrySet()) {
            total = total + entry.getValue();
        }
    }

    public void checkResults(){
        int value = this.runRoulette();
        System.out.println("Resultado:" + value);
        if (this.isUnitaryBet){
            boolean result = this.isADesiredResult(value);
            System.out.println("Ganhou:" + result);
        } else {
            boolean result = (this.betItems.get(value) != null);
            System.out.println("Ganhou:" + result);
        }
    }
}

