package com.iyaasoft.enums;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public enum FaceDirection {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    SOUTHWEST;

    public static List<String> getValuesAsList() {
        List<FaceDirection> faceValues = Arrays.asList(FaceDirection.values());

        return faceValues.stream().map(val -> val.name()).collect(toList());
    }
}


