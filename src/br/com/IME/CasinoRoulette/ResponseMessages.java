package br.com.IME.CasinoRoulette;

/**
 * Class that convert object to String messages to response client
 * Pattern of return messages of bet: bet/{value_roulette}/{0/1}
 * Pattern of return messages of player info: player/info/{name}/{coins}
 * Pattern of return messages of error: error/message
 *  -
 */
public class ResponseMessages {
    public static String bet_message(int value_roulette, boolean result){
        return "bet/" + value_roulette + "/" + (result ? 1 : 0);
    }
    public static String user_message(String name, int coins) {
        return "player/info/" + name + "/" + coins;
    }
    public static String error_message(String message){
        return "error/"+ message;
    }
}
