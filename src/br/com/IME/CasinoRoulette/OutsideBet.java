package br.com.IME.CasinoRoulette;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Objects;

public class OutsideBet extends Bet {

    private String type;
    private int unitaryBetValue;
    private int unitaryBetCoins;


    OutsideBet(String type, int[] intParamsList) throws CustomException, InvalidParameterException{
        this.validatePlayer();
        if (Arrays.asList(ImportantConstants.BET_TYPE_1).contains(type)){
            this.unitaryBetValue = intParamsList[0];
            this.unitaryBetCoins = intParamsList[1];
            this.type = type;
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
    @Override
    protected int totalBetCoins() {
        return this.unitaryBetCoins;
    }

    @Override
    public int checkResult(){
        int value = this.runRoulette();
        boolean result = this.isADesiredResult(value);
        System.out.println("Ganhou:" + result);
        return this.calculateProfits(result);
    }

    private int calculateProfits(boolean result){
        return (result) ? this.totalBetCoins()*(ImportantConstants.MULTIPLIER_FACTOR.get(this.type)) : (-this.totalBetCoins());
    }
}
