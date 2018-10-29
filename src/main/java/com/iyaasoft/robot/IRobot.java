package com.iyaasoft.robot;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;

public class IRobot {

    private Point point;
    private FaceDirection direction;
    private Command command;

    public Point getPoint() {
        return point;
    }

    public void setPoint(final Point point) {
        this.point = point;
    }

    public FaceDirection getDirection() {
        return direction;
    }

    public void setDirection(final FaceDirection direction) {
        this.direction = direction;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(final Command command) {
        this.command = command;
    }

    public void clearCommand() {
        command = null;
        direction = null;
        point = null;

    }

    public String report() {

        StringBuilder buff = new StringBuilder("\noutput: ");
        buff.append(point.getX());
        buff.append(",");
        buff.append(point.getY());
        buff.append(",");
        buff.append(direction.name());
        buff.append("\n");
        return buff.toString();
    }
}
