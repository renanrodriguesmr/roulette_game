package br.com.IME.CasinoRoulette;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // TODO LIST:  alterar moedas do jogador conforme o retorno, respostas, merda nas entradas

    public static void main(String[] args) {
        Player player = null;
        while(true){
            System.out.println("Enter with client request:\n");
            Scanner sc = new Scanner(System.in);
            String requestMessage = sc.nextLine();
            try {
                RequestMessages reqMsg = new RequestMessages(requestMessage);
                String type = reqMsg.getTypeRequest();
                if (type.equals(ImportantConstants.PLAYER_STRING)){
                    player = new Player(reqMsg.getLabel(), reqMsg.getIntParamsList()[0]);
                }
                if (type.equals(ImportantConstants.BET_STRING)){
                    Bet bet = new Bet(reqMsg.getLabel(), reqMsg.getIntParamsList());
                    bet.checkResults();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
