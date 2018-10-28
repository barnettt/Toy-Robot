package com.iyaasoft.routes;

import java.util.HashMap;
import java.util.Map;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;

public class ValidRoutes {

    public final static Map<Command, Map<FaceDirection, FaceDirection>> ValidRouteMap = new HashMap<>();

    {
        HashMap<FaceDirection, FaceDirection> leftDirections = new HashMap<>();
        leftDirections.put(FaceDirection.NORTH, FaceDirection.WEST);
        leftDirections.put(FaceDirection.SOUTH, FaceDirection.EAST);
        leftDirections.put(FaceDirection.WEST, FaceDirection.SOUTH);
        leftDirections.put(FaceDirection.EAST, FaceDirection.NORTH);
        leftDirections.put(FaceDirection.SOUTHWEST, FaceDirection.WEST);
        ValidRouteMap.put(Command.LEFT, leftDirections);
        HashMap<FaceDirection, FaceDirection> rightDirections = new HashMap<>();
        rightDirections.put(FaceDirection.NORTH, FaceDirection.EAST);
        rightDirections.put(FaceDirection.SOUTH, FaceDirection.WEST);
        rightDirections.put(FaceDirection.WEST, FaceDirection.NORTH);
        rightDirections.put(FaceDirection.EAST, FaceDirection.SOUTH);
        rightDirections.put(FaceDirection.SOUTHWEST, FaceDirection.NORTH);
        ValidRouteMap.put(Command.RIGHT, rightDirections);
    }


}
