/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.anfp.utilitarios;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author exvicad
 */
public class StringUtils {
    
    public static String encoding(String input){
        String response;
        if(input == null) return "";
        byte[] bytes = input.getBytes(StandardCharsets.ISO_8859_1);
        response = new String(bytes, StandardCharsets.UTF_8);
        return response;
    }
    
    public static int stringToInt(String input){
        if(input == null) return 0;
        return Integer.valueOf(input.trim().equals("") ? "0" : input.trim());
    }
    
    public static boolean stringToBoolean(String input){
        boolean output = false;
        return output;
    }
    
}
