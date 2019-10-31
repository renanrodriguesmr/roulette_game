package br.com.IME.CasinoRoulette;

abstract class Bet {

    protected void validatePlayer() throws CustomException{
        if (!Player.getIsLogged()) throw new CustomException("Player not found");
    }

    protected int runRoulette() {
        int max = ImportantConstants.MAX_VALUE_ROULETTE;
        int min = ImportantConstants.MIN_VALUE_ROULETTE;
        int value = (int)(Math.random()*((max-min)+1))+min;
        System.out.println("Resultado:" + value);
        return value;
    }

    protected abstract int totalBetCoins();

    public abstract int checkResult();

}
