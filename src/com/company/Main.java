package com.company;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean isOk = true;
        DataClassFile setting = new DataClassFile(new StringBuilder("")
                , new StringBuilder(""), new StringBuilder("")
                , new StringBuilder(""), new StringBuilder(""));
        while (isOk) {
            System.out.print("Enter the date (yyyy/mm/dd) : ");
            StringBuilder date = new StringBuilder(input.next());

//            StringBuilder date = new StringBuilder("1111/11/11");
            boolean isSetting = Enigma.inData(date);
            if (isSetting) {
                setting = Enigma.setSetting(date);
                isOk = false;
            }
            else System.out.println("Enter a valid date!");
        }
        System.out.print("Enter the text :");
        StringBuilder inp = new StringBuilder(input.next());

        StringBuilder code = Enigma.codeAndDecode(inp,setting);
        StringBuilder deCode = Enigma.codeAndDecode(code,setting);

        System.out.println("The coded text : " + code);
        System.out.println("The encoded text : " + deCode);
    }

}
