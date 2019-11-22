package br.com.IME.CasinoRoulette;

@SuppressWarnings("serial")
public class CustomException extends Exception {
    public CustomException(String errorMessage) {
        super(errorMessage);
    }
}
