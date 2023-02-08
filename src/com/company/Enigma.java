package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Enigma {
    public static DataClassFile setSetting (StringBuilder date) throws IOException {
        ArrayList<DataClassFile> dataClassFiles = FileReader.read();
        boolean isOk = true;
        DataClassFile setting = new DataClassFile(new StringBuilder("")
                , new StringBuilder(""), new StringBuilder("")
                , new StringBuilder(""), new StringBuilder(""));
        while (isOk) {
            //get setting from date
            for (int i = 0; i < dataClassFiles.size(); i++) {
                if (date.toString().equals(dataClassFiles.get(i).getDate().toString())) {
                    setting.setDate(dataClassFiles.get(i).getDate());
                    setting.setPlugBord(dataClassFiles.get(i).getPlugBord());
                    setting.setRotor1(dataClassFiles.get(i).getRotor1());
                    setting.setRotor2(dataClassFiles.get(i).getRotor2());
                    setting.setRotor3(dataClassFiles.get(i).getRotor3());
                    isOk=false;
                    break;
                }
            }
        }
        return updateSetting(setting);
    }
    public static boolean inData(StringBuilder date) throws IOException {
        ArrayList<DataClassFile> dataClassFiles = FileReader.read();
        boolean isOk = false;
        //get setting from date
        for (int i = 0; i < dataClassFiles.size(); i++) {
            if (date.toString().equals(dataClassFiles.get(i).getDate().toString())) {
                isOk = true;
                break;
            }
        }
        return isOk;
    }
    public static DataClassFile updateSetting(DataClassFile setting){
        setting.getRotor1().deleteCharAt(0);
        setting.getRotor2().deleteCharAt(0);
        setting.getRotor3().deleteCharAt(0);
        setting.getPlugBord().deleteCharAt(0);
        setting.getRotor1().deleteCharAt(setting.getRotor1().length() - 1);
        setting.getRotor2().deleteCharAt(setting.getRotor2().length() - 1);
        setting.getRotor3().deleteCharAt(setting.getRotor3().length() - 1);
        setting.getPlugBord().deleteCharAt(setting.getPlugBord().length() - 1);
        return setting;
    }
    public static StringBuilder codeAndDecode(StringBuilder inp , DataClassFile setting){
        Map<Character,Character> reflector = reflector();
        Map<Character,Character> plugBordCode = plugBord(setting.getPlugBord());
        Map<Character,Character> rotor1Code = rotor(setting.getRotor1());
        Map<Character,Character> rotor2Code = rotor(setting.getRotor2());
        Map<Character,Character> rotor3Code = rotor(setting.getRotor3());
        Map<Character,Character> reversRotor3Code = reversRotor(rotor3Code);
        Map<Character,Character> reversRotor2Code = reversRotor(rotor2Code);
        Map<Character,Character> reversRotor1Code = reversRotor(rotor1Code);
        StringBuilder result = new StringBuilder();
        int conR2=0,conR3=0;
        for (int i = 0; i < inp.length(); i++) {
            Character ch= plugBordCode.get(inp.charAt(i));
            if (ch == null) ch = inp.charAt(i);
            ch = rotor3Code.get(ch);
            ch = rotor2Code.get(ch);
            ch = rotor1Code.get(ch);
            ch = reflector.get(ch);
            ch = reversRotor1Code.get(ch);
            ch = reversRotor2Code.get(ch);
            ch = reversRotor3Code.get(ch);
            Character temp = ch ;
//            ch = reversPlugBordCode.get(ch);
            ch = plugBordCode.get(ch);
            if (ch == null) ch = temp;
            result.append(ch);
            rotor3Code = changeRotor(rotor3Code);
            reversRotor3Code = reversRotor(rotor3Code);
            conR3++;
            if (conR3 == 26) {
                rotor2Code = changeRotor(rotor2Code);
                conR2++;
                conR3 =0;
                reversRotor2Code = reversRotor(rotor2Code);
            }
            if (conR2 == 26) {
                rotor1Code = changeRotor(rotor1Code);
                conR2 =0;
                reversRotor1Code = reversRotor(rotor1Code);
            }
        }
        return result;
    }
    public static Map<Character,Character> reflector(){
        Map<Character,Character> map = new HashMap<>();
        char first = 97 ;
        char last = 122;

        for (int i = 0; i < 26; i++) {
            map.put(first,last);
            first++;
            last--;
        }

        return map;
    }
    static Map<Character,Character> plugBord(StringBuilder str){
        Map<Character,Character> map = new HashMap<>();
        String [] chars = str.toString().split("");
        for (int i = 0; i < chars.length-1 ;) {
            map.put(chars[i].charAt(0) , chars[i+1].charAt(0));
            i+=3;
        }
        for (int i = 0; i < chars.length-1 ;) {
            map.put(chars[i+1].charAt(0) , chars[i].charAt(0));
            i+=3;
        }
        return map;
    }
    static Map<Character,Character> rotor(StringBuilder str){
        Map<Character,Character> map = new HashMap<>();
        char first = 97 ;
        for (int i = 0; i < 26; i++) {
            map.put(first,str.charAt(i));
            first++;
        }
        return map;
    }
    static Map<Character,Character> changeRotor(Map<Character,Character> map){
        Map<Character,Character> tempMap = new HashMap<>();
        char last = 122;//z
        char last1 = 121;//y
        char first = 97;//a
        Character temp = map.get(last);
        for (int i = 26; i > 0; i--) {
            Character ch = map.get(last1);
            tempMap.put(last, ch);
            last--;
            last1--;
        }
        tempMap.put(first,temp);
//print
//        System.out.println("print change rotor:");
//        char pri = 97;
//        for (int i = 0; i < 26; i++) {
//            System.out.print(map.get(pri));
//            pri++;
//        }
//        System.out.println();
//        pri = 97;
//        for (int i = 0; i < 26; i++) {
//            System.out.print(tempMap.get(pri));
//            pri++;
//        }
//        System.out.println();
        return tempMap;
    }
    static Map<Character,Character> reversRotor(Map<Character,Character> map){
        Map<Character,Character> tempMap = new HashMap<>();
        char first = 97;
        for (int i = 0; i < 26; i++) {
            tempMap.put(map.get(first),first);
            first++;
        }
        //print
//        System.out.println("print revers rotor:");
//        char pri = 97;
//        for (int i = 0; i < 26; i++) {
//            System.out.print(map.get(pri));
//            pri++;
//        }
//        System.out.println();
//        pri = 97;
//        for (int i = 0; i < 26; i++) {
//            System.out.print(tempMap.get(pri));
//            pri++;
//        }
//        System.out.println();
        return tempMap;
    }
}
