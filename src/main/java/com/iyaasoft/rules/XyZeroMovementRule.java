package com.iyaasoft.rules;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;

public class XyZeroMovementRule implements ToyRobotRule {

    BiPredicate<Integer, FaceDirection> xPredicate = (val, direction) -> val == 0 && (direction == FaceDirection.WEST);
    BiPredicate<Integer, FaceDirection> yPredicate = (val, direction) -> val == 0 && (direction == FaceDirection.SOUTH);
    private Point point;
    private FaceDirection faceDirection;
    private Command command;

    public XyZeroMovementRule(Point currentPoint, FaceDirection currentDirection, Command newCommand) {

        this.point = currentPoint;
        this.faceDirection = currentDirection != null ? currentDirection : this.faceDirection;
        this.command = newCommand;
    }

    @Override
    public boolean execute() {
        if (Command.PLACE.equals(this.command)) {
            return true;
        }
        if (this.point == null) {
            return false;
        }
        final Point currentPoint = new Point(this.point.getX(), this.point.getY());
        Predicate<FaceDirection> directionPredicate = (newDirection) -> checkDirectionAndPoint(faceDirection, currentPoint);
        return !directionPredicate.test(faceDirection);
    }

    private boolean checkDirectionAndPoint(final FaceDirection newDirection, final Point point) {

        return xPredicate.test(point.getX(), newDirection) || yPredicate.test(point.getX(), newDirection);
    }
}
