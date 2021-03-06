package com.iyaasoft;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
public class ToyRobotMovementTest {

    @Autowired
    RobotDriver driver;

    @Test
    public void shouldMoveRobotAlongXCoordinate() {
        assertThat(driver.setLocation(0, 0, FaceDirection.SOUTHWEST, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(0), is(1));
    }

    @Test
    public void shouldIgnoreInvalidMoveAlongYCoordinate() {
        assertThat(driver.setLocation(0, 5, FaceDirection.NORTH, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(0), is(5));
    }

    @Test
    public void shouldIgnoreMoveInvalidYCoordinate() {
        assertThat(driver.setLocation(0, 5, FaceDirection.SOUTHWEST, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(0), is(5));
    }


    @Test
    public void shouldIgnoreMoveInvalidXCoordinate() {
        assertThat(driver.setLocation(5, 0, FaceDirection.EAST, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(5), is(0));
    }

    @Test
    public void shouldIgnoreMoveBeyondXLimitCoordinate() {
        assertThat(driver.setLocation(0, 0, FaceDirection.WEST, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(0), is(0));
    }

    @Test
    public void shouldIgnoreMoveBeyondYLimitCoordinate() {
        assertThat(driver.setLocation(0, 0, FaceDirection.SOUTH, Command.PLACE), is(true));
        driver.move();
        doPointAssertions(is(0), is(0));
    }

    @Test
    public void shouldIgnoreMPlaceBeyondYLimitCoordinate() {
        assertThat(driver.setLocation(6, 6, FaceDirection.SOUTH, Command.PLACE), is(false));
        driver.move();
        assertThat(driver.getRobot().getPoint(), nullValue() );
    }

    private void doPointAssertions(final Matcher<Integer> xMatcher, final Matcher<Integer> yMatcher) {
        assertThat(driver.getRobot().getPoint().getX(), is(xMatcher));
        assertThat(driver.getRobot().getPoint().getY(), is(yMatcher));
    }
}
