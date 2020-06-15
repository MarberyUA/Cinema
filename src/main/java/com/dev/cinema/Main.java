package com.dev.cinema;

public class Main {
    public static void main(String[] args) {
        System.out.println(isNegative(-0.0f));
    }

    public static boolean isNegative(float n) {
        return n == -0;
    }
}
