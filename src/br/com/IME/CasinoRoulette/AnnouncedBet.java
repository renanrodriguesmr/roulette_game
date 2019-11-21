package br.com.IME.CasinoRoulette;

import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnnouncedBet extends Bet {
    private HashMap<Integer, Integer> betItems;
    private String type;
    AnnouncedBet(String type, int[] intParamsList) throws CustomException, InvalidParameterException{
        this.validatePlayer();
        if (Arrays.asList(ImportantConstants.BET_TYPE_2).contains(type)) {
            this.betItems = new HashMap<Integer, Integer>();
            this.type = type;
            for (int i = 0; i < intParamsList.length; i = i+2){
                this.betItems.put(intParamsList[i], intParamsList[i+1]);
            }
        } else throw new InvalidParameterException();
    }

    @Override
    protected int totalBetCoins() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry: this.betItems.entrySet()) {
            total = total + entry.getValue();
        }

        return total;
    }

    @Override
    public int checkResult(Player player, PrintWriter out){
        if (!this.checkCoins(player)){
            String message = ResponseMessages.error_message("not_coins_enough");
            out.println(message);
            return 0;
        }
        int value = this.runRoulette();
        boolean result = (this.betItems.get(value) != null);
        String message = ResponseMessages.bet_message(value, result);
        out.println(message);
        return this.calculateProfits(result, value);
    }

    private int calculateProfits(boolean result, int value){
        return (result) ? this.betItems.get(value)*(ImportantConstants.MULTIPLIER_FACTOR.get(this.type)) : (-this.totalBetCoins());
    }
}
