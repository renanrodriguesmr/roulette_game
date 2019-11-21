package br.com.IME.CasinoRoulette;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player = null;
        System.out.println("Enter with IP:\n");
        int IP = sc.nextInt();
        System.out.println("Enter with Port:\n");
        int PORT = sc.nextInt();
        System.out.println("Server listening:\n");
        while(true){
            String requestMessage = sc.nextLine();
            try {
                RequestMessages reqMsg = new RequestMessages(requestMessage);
                String type = reqMsg.getTypeRequest();
                if (type.equals(ImportantConstants.PLAYER_STRING)){
                    player = new Player(reqMsg.getLabel(), reqMsg.getIntParamsList()[0]);
                    String message = ResponseMessages.user_message(player.getName(), player.getCoins());
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
                        String message = ResponseMessages.user_message(player.getName(), player.getCoins());
                        System.out.println(message);
                    }
                }
                if (type.equals(ImportantConstants.END_STRING)){
                    String message = ResponseMessages.user_message(player.getName(), player.getCoins());
                    System.out.println(message);
                    endGame(player);
                }
            } catch (Exception e) {
                String message = ResponseMessages.error_message(e.getMessage());
                System.out.println(message);
            }
        }
    }

    private static void endGame(Player player){
        Player.logout();
    }
}
