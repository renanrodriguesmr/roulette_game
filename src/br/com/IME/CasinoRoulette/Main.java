package br.com.IME.CasinoRoulette;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Player player = null;

        System.out.println("Enter with Port:\n");
        int PORT = sc.nextInt();
        String line = sc.nextLine();

        ServerSocket listenSocket = new ServerSocket(59001);
        Socket clientSocket = listenSocket.accept();

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Server listening:\n");

        while(true){
            String requestMessage  = in.readLine();

            try {

                RequestMessages reqMsg = new RequestMessages(requestMessage);
                String type = reqMsg.getTypeRequest();

                if (type.equals(ImportantConstants.PLAYER_STRING)){
                    player = new Player(reqMsg.getLabel(), reqMsg.getIntParamsList()[0]);
                    String message = ResponseMessages.user_message(player.getName(), player.getCoins());
                    out.println(message);
                }

                if (type.equals(ImportantConstants.BET_STRING)){
                    String label = reqMsg.getLabel();
                    int result = 0;

                    if (Arrays.asList(ImportantConstants.BET_TYPE_1).contains(label)){
                        OutsideBet bet = new OutsideBet(label, reqMsg.getIntParamsList());
                        result = bet.checkResult(player);
                    } else if (Arrays.asList(ImportantConstants.BET_TYPE_2).contains(label)) {
                        AnnouncedBet bet = new AnnouncedBet(label, reqMsg.getIntParamsList());
                        result = bet.checkResult(player);
                    } else throw new InvalidParameterException();

                    if (player != null) {
                        player.setCoins(result);
                        String message = ResponseMessages.user_message(player.getName(), player.getCoins());
                        out.println(message);
                    }
                }

                if (type.equals(ImportantConstants.END_STRING)){
                    String message = ResponseMessages.user_message(player.getName(), player.getCoins());
                    out.println(message);
                    endGame(player);
                }
            } catch (Exception e) {
                String message = ResponseMessages.error_message(e.getMessage());
                out.println(message);
            }
        }
    }

    private static void endGame(Player player){
        Player.logout();
    }
}
