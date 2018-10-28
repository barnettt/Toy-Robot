package com.iyaasoft.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;

public class RobotFunctions {

    public static final Predicate<Point> xyPredicate = (p) -> p.getX() <= 5 && p.getX() >= 0 && p.getY() <= 5 && p.getY() >= 0;

    public static final BiFunction<FaceDirection, Point, Point> moveInDirection = (FaceDirection fd, Point p) -> {

        if (p.getX() < 0) return p;

        switch(fd.name()) {
            case  "EAST" : p.setX(p.getX() + 1); break;
            case  "WEST" : p.setX(p.getX() - 1); break;
            case  "NORTH" : p.setX(p.getX() + 1); break;
            case  "SOUTH" : p.setX(p.getX() - 1); break;
            case  "SOUTHWEST" : p.setX(p.getX() + 1); break;
        }
        return p;
    };

    public static final BiFunction<String, String[], List<String>> splitStringFunction = (stringData, regex) -> {

          List<String> result = new ArrayList();
          String[] resultArray = null;
          if(regex != null && regex.length > 0) {
              resultArray = stringData.split(regex[0]);
              String[] interMediate ;
              if (regex.length > 1) {
                  for (int i = 0; i < resultArray.length; i++) {
                      if (resultArray[i].contains(regex[1])) {
                          interMediate = resultArray[i].split(regex[1]);
                          result.add(resultArray[0]);
                          result.addAll(Arrays.asList(interMediate));
                      }
                  }
              }
          }
          if(result.isEmpty() && resultArray != null){
              result.addAll(Arrays.asList(resultArray));
          }

        return result;
    };

    public static Predicate<Command> isTurnCommand = (command) -> Command.LEFT.equals(command) || Command.RIGHT.equals(command);

    public static Predicate<Command> isReportCommand = (command) -> Command.REPORT.equals(command);

    public static Predicate<Command> isMoveCommand = (command) -> Command.MOVE.equals(command);

    public static Predicate<Command> isPlaceCommand = (command) -> Command.PLACE.equals(command);

}
