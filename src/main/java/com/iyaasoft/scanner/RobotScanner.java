package com.iyaasoft.scanner;


import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class RobotScanner {

    Scanner scanner;

    public String getByteArrayInput(ByteArrayInputStream in) {
        if (in != null) {
            System.setIn(in);
            scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
        } else {
            return "";
        }
        String input;
        input = getInputString();
        return input;
    }

    private String getInputString() {

        String input = "\n";
        while (scanner.hasNextLine()) {
            String read = scanner.nextLine();

            if ("exit".equalsIgnoreCase(read) || "q".equalsIgnoreCase(read)) {
                break;
            }
                input += read.toUpperCase();
                System.out.println("input : " + input);
        }
        return input;
    }

    public String getKeyboardInput() {
        scanner = new Scanner(System.in);
        return getInputString();
    }
}
