package com.iyaasoft.rules;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;
import com.iyaasoft.routes.ValidRoutes;

public class XyZeroMovementRule implements ToyRobotRule {

    private Point point;
    private FaceDirection faceDirection;
    private Command command;

    BiPredicate<Integer, FaceDirection> xPredicate = (val, direction) -> val == 0 && (direction == FaceDirection.WEST);
    BiPredicate<Integer, FaceDirection> yPredicate = (val, direction) -> val == 0 && (direction == FaceDirection.SOUTH);
    Predicate<Command> isTurnCommand = RobotFunctions.isTurnCommand;

    public XyZeroMovementRule(Point currentPoint, FaceDirection currentDirection, Command newCommand) {

        this.point = currentPoint;
        this.faceDirection = currentDirection != null ? currentDirection : this.faceDirection;
        this.command = newCommand;
    }

    @Override
    public boolean execute() {
        if (Command.PLACE.equals(this.command) || isTurnCommand.test(command)) {
            return true;
        }

        Map<FaceDirection, FaceDirection> directions;
        final Point currentPoint = new Point(this.point.getX(), this.point.getY());
        directions = ValidRoutes.ValidRouteMap.get(command);
        final FaceDirection direction = directions.get(faceDirection);
        Predicate<FaceDirection> directionPredicate = (newDirection) -> checkDirectionAndPoint(newDirection, currentPoint);
        return !directionPredicate.test(direction);
    }

    private boolean checkDirectionAndPoint(final FaceDirection newDirection, final Point point) {

        return xPredicate.test(point.getX(), newDirection) || yPredicate.test(point.getX(), newDirection);
    }
}
