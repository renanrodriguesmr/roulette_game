package br.com.IME.CasinoRoulette;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // TODO LIST:  respostas, problema nas entradas

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
                    String label = reqMsg.getLabel();
                    int result = 0;
                    if (Arrays.asList(ImportantConstants.BET_TYPE_1).contains(label)){
                        OutsideBet bet = new OutsideBet(label, reqMsg.getIntParamsList());
                        result = bet.checkResult();
                    } else if (Arrays.asList(ImportantConstants.BET_TYPE_2).contains(label)) {
                        AnnouncedBet bet = new AnnouncedBet(label, reqMsg.getIntParamsList());
                        result = bet.checkResult();
                    } else throw new InvalidParameterException();
                    if (player != null) {
                        player.setCoins(result);
                        endGame(player);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void endGame(Player player){
        int finalCoins = player.getCoins();
        System.out.println(finalCoins);
        Player.logout();
    }
}
