package com.iyaasoft;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.robot.RobotDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(properties = {"fileName=robotSimulatorCommandFile.txt", "test=false"})
public class ToyRobotLeftRightTest {

    @Autowired
    RobotDriver driver;

    @Test
    public void shouldTurnRobotLeftWest() {
        driver.setLocation(3, 2, FaceDirection.NORTH, Command.PLACE);
        driver.turn(Command.LEFT);
        assertThat(driver.getRobot().getCommand(), is(Command.LEFT));
        assertThat(driver.getRobot().getDirection(), is(FaceDirection.WEST));
    }

    @Test
    public void shouldTurnRobotRightEast() {
        driver.setLocation(2, 1, FaceDirection.NORTH, Command.PLACE);
        driver.turn(Command.RIGHT);
        assertThat(driver.getRobot().getCommand(), is(Command.RIGHT));
        assertThat(driver.getRobot().getDirection(), is(FaceDirection.EAST));
    }

    @Test
    public void shouldTurnRobotLeftNorth() {
        driver.setLocation(0, 1, FaceDirection.EAST, Command.PLACE);
        driver.turn(Command.LEFT);
        assertThat(driver.getRobot().getCommand(), is(Command.LEFT));
        assertThat(driver.getRobot().getDirection(), is(FaceDirection.NORTH));
    }

    @Test
    public void shouldTurnRobotRightWest() {
        driver.setLocation(0, 1, FaceDirection.SOUTH, Command.PLACE);
        driver.turn(Command.RIGHT);
        assertThat(driver.getRobot().getCommand(), is(Command.RIGHT));
        assertThat(driver.getRobot().getDirection(), is(FaceDirection.WEST));
    }

    @Test
    public void shouldTurnRobotLeftSouth() {
        driver.setLocation(0, 1, FaceDirection.WEST, Command.PLACE);
        driver.turn(Command.LEFT);
        assertThat(driver.getRobot().getCommand(), is(Command.LEFT));
        assertThat(driver.getRobot().getDirection(), is(FaceDirection.SOUTH));
    }
}
