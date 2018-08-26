package com.lijiankun24.koala;

public class Printer {

    public static void print(String name, Object value) {
        System.out.println("The " + name + " is " + value);
    }

    public static void onExit(Object param, int opcode) {
        System.out.println("The " + param + " is " + opcode);
    }
}
