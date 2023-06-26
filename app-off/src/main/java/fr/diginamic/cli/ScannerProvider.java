package fr.diginamic.cli;

import java.util.Scanner;

public class ScannerProvider {
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (null == scanner) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
