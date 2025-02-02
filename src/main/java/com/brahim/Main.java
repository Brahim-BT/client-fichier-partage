package com.brahim;

public class Main {
    public static void main(String[] args) {
        try {
            FileClient client = new FileClient();
            client.connect();
            client.showMenu();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}