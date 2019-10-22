package br.com.IME.CasinoRoulette;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

/**
 * Class that convert Strings messages from client to objects
 * Pattern of player messages: player/{name}/{initialCoins}
 * Pattern of bets:
 *  - bet/red_black/{0/1}/{ap}
 *  - bet/high_low/{0/1}/{ap}
 *  - bet/even_odd/{0/1}/{ap}
 *  - bet/dozen/{0/1/2}/{ap}
 *  - bet/line/{n1}/{ap1}/{n2}/{ap2}/{n3}/{ap3}/{n4}/{ap4}/{n5}/{ap5}/{n6}/{ap6}
 *  - bet/five/{n1}/{ap1}/{n2}/{ap2}/{n3}/{ap3}/{n4}/{ap4}/{n5}/{ap5}
 *  - bet/square/{n1}/{ap1}/{n2}/{ap2}/{n3}/{ap3}/{n4}/{ap4}
 *  - bet/street/{n1}/{ap1}/{n2}/{ap2}/{n3}/{ap3}
 *  - bet/two_numbers/{n1}/{ap1}/{n2}/{ap2}
 *  - bet/straight_up/{n1}/{ap1}
 *  Pattern of end session: end
 *
 *  -
 */

public class RequestMessages {
    private String typeRequest;
    private String label;
    private int[] intParamsList;

    RequestMessages(String message){
        List<String> paramsList = Arrays.asList(message.split("/"));
        // check if type is valid
        if (!Arrays.asList(ImportantConstants.TYPE_OPTIONS).contains(paramsList.get(0))) throw new InvalidParameterException();

        // fill the attributes
        this.typeRequest = paramsList.get(0);
        if (paramsList.size() > 1){
            this.label = paramsList.get(1);
            this.intParamsList = new int[paramsList.size()-2];
            for (int i = 2; i < paramsList.size(); i++){
                this.intParamsList[i-2] = Integer.parseInt(paramsList.get(i));
            }
        }
    }

    public String getTypeRequest() {
        return typeRequest;
    }

    public String getLabel() {
        return label;
    }

    public int[] getIntParamsList() {
        return intParamsList;
    }
}
