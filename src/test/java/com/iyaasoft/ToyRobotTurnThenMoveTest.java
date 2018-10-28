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
public class ToyRobotTurnThenMoveTest {


    @Autowired
    RobotDriver driver;

    @Test
    public void shouldTurnRobotLeft_andNotMove() {
        driver.setLocation(0, 0, FaceDirection.SOUTHWEST, Command.PLACE);
        driver.turn(Command.LEFT);
        driver.move();
        doLeftRightAssertions(is(0), is(0), is(Command.LEFT), is(FaceDirection.WEST));
    }


    @Test
    public void shouldTurnRobotRight_andNotMove() {
        driver.setLocation(0, 0, FaceDirection.EAST, Command.PLACE);
        driver.turn(Command.RIGHT);
        driver.move();
        doLeftRightAssertions(is(0), is(0), is(Command.RIGHT), is(FaceDirection.SOUTH));
    }

    @Test
    public void shouldTurnRobotRight_andMove() {
        driver.setLocation(0, 0, FaceDirection.NORTH, Command.PLACE);
        driver.turn(Command.RIGHT);
        driver.move();
        doLeftRightAssertions(is(1), is(0), is(Command.RIGHT), is(FaceDirection.EAST));
    }

    @Test
    public void shouldTurnRobotLeft_andMove() {
        driver.setLocation(0, 0, FaceDirection.SOUTH, Command.PLACE);
        driver.turn(Command.LEFT);
        driver.move();
        doLeftRightAssertions(is(1), is(0), is(Command.LEFT), is(FaceDirection.EAST));
    }

    private void doLeftRightAssertions(final Matcher<Integer> matcher1, final Matcher<Integer> matcher2, final Matcher<Command> matcher3, final Matcher<FaceDirection> matcher4) {
        assertThat(driver.getRobot().getPoint().getX(), matcher1);
        assertThat(driver.getRobot().getPoint().getY(), matcher2);
        assertThat(driver.getRobot().getCommand(), matcher3);
        assertThat(driver.getRobot().getDirection(), matcher4);
    }
}
