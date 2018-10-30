package com.iyaasoft.scanner;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iyaasoft.util.Utilities;

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
        List<String> inputList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String read = scanner.nextLine();

            if ("exit".equalsIgnoreCase(Utilities.removeEndLineCharFromString(read))
                    || "q".equalsIgnoreCase(Utilities.removeEndLineCharFromString(read)) ) {
                break;
            }
            inputList.add(read);
            System.out.println("input : " + inputList);
        }
        return convertToString(inputList);
    }

    private String convertToString(final List<String> inputList) {
        final StringBuilder builder  = new StringBuilder();
        inputList.forEach(entry ->   builder.append(entry+Utilities.END_OF_LINE));
        return builder.toString();
    }

    public String getKeyboardInput() {
        scanner = new Scanner(System.in);
        return getInputString();
    }
}
