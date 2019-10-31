package br.com.IME.CasinoRoulette;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // TODO LIST:  alterar moedas do jogador conforme o retorno, respostas, problema nas entradas
    // TODO: refatorar a main e separar as apostas na classe Bet

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
                    if (Arrays.asList(ImportantConstants.BET_TYPE_1).contains(label)){
                        OutsideBet bet = new OutsideBet(label, reqMsg.getIntParamsList());
                        boolean result = bet.checkResult();
                    } else if (Arrays.asList(ImportantConstants.BET_TYPE_2).contains(label)) {
                        AnnouncedBet bet = new AnnouncedBet(label, reqMsg.getIntParamsList());
                        boolean result = bet.checkResult();
                    } else throw new InvalidParameterException();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
