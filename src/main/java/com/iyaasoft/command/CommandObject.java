package com.iyaasoft.command;

import java.util.ArrayList;
import java.util.List;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;

public class CommandObject {

    List<String> validNumbers = new ArrayList<>();
    private Command action;
    private FaceDirection direction;
    private Point point;

    {
        for (int i = 0; i < 9; i++) {
            validNumbers.add(String.valueOf(i));
        }
    }

    public CommandObject() {
    }

    public CommandObject(Point point, FaceDirection direction, Command command) {
        this.point = point;
        this.direction = direction;
        this.action = command;
    }

    public Command getAction() {
        return action;
    }

    public void setAction(final Command action) {
        this.action = action;
    }

    public FaceDirection getDirection() {
        return direction;
    }

    public void setDirection(final FaceDirection direction) {
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public void isInt(final String coord, final CommandObject command) {

        if (nullCheckValue(coord)) return;
        if (command.getPoint() == null && validNumbers.contains(coord)) {
            command.setPoint(new Point(Integer.valueOf(coord), 0));
        } else if (validNumbers.contains(coord)) {
            command.getPoint().setY(Integer.valueOf(coord));
        }
    }

    public void idDirection(final String item, final CommandObject command) {
        if (nullCheckValue(item)) return;
        List<String> directions = FaceDirection.getValuesAsList();

        if (command.getDirection() == null && directions.contains(item)) {
            command.setDirection(FaceDirection.valueOf(item));
        }

    }

    private boolean nullCheckValue(final String item) {
        if (item == null || item == "") {
            return true;
        }
        return false;
    }

    public void isAction(final String item, final CommandObject command) {
        if (nullCheckValue(item)) return;
        List<String> commands = Command.getValuesAsList();
        if (commands.contains(item) && command.getAction() == null) {
            command.setAction(Command.valueOf(item));
        }
    }

}
