package com.iyaasoft;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.robot.RobotDriver;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(properties = {"test=true"})
public class ToyRobotRulesTest {

    @Autowired
    RobotDriver robotDriver;

    @Test
    public void shouldPreventInvalidMoveFromPointZeroZero_afterChangeDirection() {
        robotDriver.setLocation(0, 0, FaceDirection.SOUTHWEST, Command.PLACE);
        robotDriver.turn(Command.LEFT);
        robotDriver.move();

        doMoveAssertions(is(FaceDirection.WEST), is(0),is(0));

    }

    @Test
    public void shouldNotPreventInValidMoveFromTheNorth_afterChangeDirection() {
        robotDriver.setLocation(0, 0, FaceDirection.NORTH, Command.PLACE);
        robotDriver.turn(Command.LEFT);
        robotDriver.move();

        doMoveAssertions(is(FaceDirection.WEST), is(0), is(0));

    }


    @Test
    public void shouldPreventInvalidMoveFromEast_afterChangeDirection() {
        robotDriver.setLocation(0, 0, FaceDirection.EAST, Command.PLACE);
        robotDriver.turn(Command.RIGHT);
        robotDriver.move();

        doMoveAssertions(is(FaceDirection.SOUTH), is(0),is(0));
    }

    @Test
    public void shouldNotPreventInvalidMoveFromEast_afterChangeDirection() {
        robotDriver.setLocation(2, 1, FaceDirection.EAST, Command.PLACE);
        robotDriver.turn(Command.RIGHT);
        robotDriver.move();
        robotDriver.move();
        doMoveAssertions(is(FaceDirection.SOUTH), is(2),is(0));

    }

    private void doMoveAssertions(final Matcher<FaceDirection> matcher, final Matcher<Integer> matcher2, final Matcher<Integer> matcher3) {
        assertThat(robotDriver.getRobot().getDirection(), matcher);
        assertThat(robotDriver.getRobot().getPoint().getX(), matcher2);
        assertThat(robotDriver.getRobot().getPoint().getY(), matcher3);
    }

    @Test
    public void shouldNotPreventValidMoveFromTheNorth_afterChangeDirection() {
        robotDriver.setLocation(0, 0, FaceDirection.NORTH, Command.PLACE);
        robotDriver.turn(Command.RIGHT);
        robotDriver.move();

        doMoveAssertions(is(FaceDirection.EAST), is(1), is(0));

    }


}
