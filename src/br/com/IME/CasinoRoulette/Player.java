package br.com.IME.CasinoRoulette;

import java.security.InvalidParameterException;

public class Player {
    private String name;
    private int coins;
    private static Boolean isLogged=false;

    Player(String name, int coins) throws InvalidParameterException, CustomException {
        if (isLogged) throw new CustomException("Session Busy");
        if (name != null && !name.equals("") && coins > 0){
            this.name = name;
            this.coins = coins;
            isLogged = true;
        } else {
            throw new InvalidParameterException();
        }
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int value) {
        this.coins = Math.max(this.coins + value, 0);
    }

    public static Boolean getIsLogged(){
        return isLogged;
    }

    public static void logout(){
        isLogged = false;
    }

}
