package com.company;

public class DataClassFile {
    private StringBuilder Date;
    private StringBuilder PlugBord;
    private StringBuilder Rotor1;
    private StringBuilder Rotor2;
    private StringBuilder Rotor3;
    //constructor
    public DataClassFile(StringBuilder date, StringBuilder plugBord,
                         StringBuilder rotor1, StringBuilder rotor2,
                         StringBuilder rotor3) {
        Date = date;
        PlugBord = plugBord;
        Rotor1 = rotor1;
        Rotor2 = rotor2;
        Rotor3 = rotor3;
    }
    //setters and getters
    public StringBuilder getDate() {
        return Date;
    }
    public void setDate(StringBuilder date) {
        Date = date;
    }
    public StringBuilder getPlugBord() {
        return PlugBord;
    }
    public void setPlugBord(StringBuilder plugBord) {
        PlugBord = plugBord;
    }
    public StringBuilder getRotor1() {
        return Rotor1;
    }
    public void setRotor1(StringBuilder rotor1) {
        Rotor1 = rotor1;
    }
    public StringBuilder getRotor2() {
        return Rotor2;
    }
    public void setRotor2(StringBuilder rotor2) {
        Rotor2 = rotor2;
    }
    public StringBuilder getRotor3() {
        return Rotor3;
    }
    public void setRotor3(StringBuilder rotor3) {
        Rotor3 = rotor3;
    }
}
