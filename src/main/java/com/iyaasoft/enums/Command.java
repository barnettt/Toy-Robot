package com.iyaasoft.enums;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public enum Command {

    PLACE,
    MOVE,
    RIGHT,
    REPORT,
    LEFT;

    public static List<String> getValuesAsList() {

        List<Command> commandValues = Arrays.asList(Command.values());

        return commandValues.stream().map(val -> val.name()).collect(toList());
    }
}
