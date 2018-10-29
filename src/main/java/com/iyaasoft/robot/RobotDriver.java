package com.iyaasoft.robot;

import static com.iyaasoft.routes.ValidRoutes.ValidRouteMap;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import com.iyaasoft.command.CommandObject;
import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.routes.Point;
import com.iyaasoft.rules.RobotFunctions;
import com.iyaasoft.rules.XyZeroMovementRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotDriver {

    public static Predicate<Command> isTurnCommand = RobotFunctions.isTurnCommand;
    public static Predicate<Command> isReportCommand = RobotFunctions.isReportCommand;
    public static Predicate<Command> isMoveCommand = RobotFunctions.isMoveCommand;
    public static Predicate<Command> isPlaceCommand = RobotFunctions.isPlaceCommand;
    @Autowired
    IRobot robot;
    Predicate<Point> xyPredicate = RobotFunctions.xyPredicate;
    private Function<Command, FaceDirection> turnAndFace = (command) -> {
        Map<FaceDirection, FaceDirection> directions = ValidRouteMap.get(command);
        return directions.get(robot.getDirection());
    };
    private BiFunction<FaceDirection, Point, Point> moveInDirection = RobotFunctions.moveInDirection;

    public RobotDriver() {

    }

    public boolean setLocation(final int x, final int y, final FaceDirection direction, Command command) {

        Point position = new Point(x, y);
        if (!xyPredicate.test(position)) {
            getRobot().clearCommand();
            return false;
        }
        getRobot().setCommand(command);
        getRobot().setPoint(position);
        getRobot().setDirection(direction);
        return true;
    }

    public IRobot getRobot() {
        return robot;
    }

    public void move() {

        if (isMoveAllowed(getRobot().getCommand())) {
            Point copyOfPoint = new Point(getRobot().getPoint().getX(), getRobot().getPoint().getY());
            Point newPoint = getRobot().getDirection() != null ? moveInDirection.apply(getRobot().getDirection(), copyOfPoint) : null;
            if (newPoint != null && xyPredicate.test(newPoint)) {
                getRobot().setPoint(newPoint);
            }
        }
    }

    public void turn(Command inCommand) {
        getRobot().setCommand(inCommand);
        getRobot().setDirection(turnAndFace.apply(inCommand));
    }

    private boolean isMoveAllowed(final Command inCommand) {
        XyZeroMovementRule xyZeroMovementRule = new XyZeroMovementRule(getRobot().getPoint(), getRobot().getDirection(), inCommand);
        return xyZeroMovementRule.execute();
    }

    public void execute(final List<CommandObject> commands) {

        commands.forEach(command ->
        {

            if (command.getAction() != null && isPlaceCommand.test(command.getAction())) {
                setLocation(command.getPoint().getX(), command.getPoint().getX(), command.getDirection(), command.getAction());
            } else if (command.getAction() != null && isTurnCommand.test(command.getAction())) {
                turn(command.getAction());
            } else if (command.getAction() != null && isReportCommand.test(command.getAction())) {
                if (getRobot().getDirection() != null) report();
            } else if (command.getAction() != null && isMoveCommand.test(command.getAction())) {
                move();
            }

        });
    }

    private void report() {
        System.out.println(getRobot().report());
    }


}
