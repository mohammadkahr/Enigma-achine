package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileReader {
    public static ArrayList<DataClassFile> read() throws IOException{
        File file = new File(".\\");
        //DataClassFile [] dataClassFile = new DataClassFile[100];
        ArrayList<DataClassFile> dataClassFiles = new ArrayList<>();
        File f2 = new File(file, "EnigmaFile.txt");
        Scanner input = new Scanner(f2);
        for (int i = 0; i < 101; i++) {
            DataClassFile data = new DataClassFile(new StringBuilder("")
                    ,new StringBuilder(""), new StringBuilder("")
                    ,new StringBuilder(""),new StringBuilder(""));
            
            input.next();
            data.setDate(new StringBuilder(input.next()));
            input.nextLine();



            input.next();
            String str = "";
            for (int j = 0; j < 8; j++) {
                str += input.next().toLowerCase(Locale.ROOT);
            }
            data.setPlugBord(new StringBuilder(str));
            input.nextLine();

            input.next();
            data.setRotor1(new StringBuilder(input.next().toLowerCase(Locale.ROOT)));
            input.nextLine();

            input.next();
            data.setRotor2(new StringBuilder(input.next().toLowerCase(Locale.ROOT)));
            input.nextLine();

            input.next();
            data.setRotor3(new StringBuilder(input.next().toLowerCase(Locale.ROOT)));

            dataClassFiles.add(data);

        }
        input.close();
        return dataClassFiles;
    }
}