package com.iyaasoft;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
@TestPropertySource(properties = {"test=true"})
public class ToyRobotPlacementTest {

    @Autowired
    RobotDriver driver;

    @Test
    public void shouldPlaceRobotAtZeroZero() {

        assertThat(driver.setLocation(0, 0, FaceDirection.SOUTHWEST, Command.PLACE), is(true));
    }


    @Test
    public void shouldIgnoreInvalidXPlacement() {
        assertThat(driver.setLocation(6, 0, FaceDirection.SOUTHWEST, Command.PLACE), is(false));
    }

    @Test
    public void shouldIgnoreInvalidYPlacement() {
        assertThat(driver.setLocation(1, 8, FaceDirection.SOUTHWEST, Command.PLACE), is(false));
    }

    @Test
    public void shouldIgnoreValidXYLimitPlacement() {
        assertThat(driver.setLocation(5, 5, FaceDirection.SOUTHWEST, Command.PLACE), is(true));
    }

    @Test
    public void shouldIgnoreNegativeXNumberPlacement() {

        assertThat(driver.setLocation(-1, 2, FaceDirection.SOUTHWEST, Command.PLACE), is(false));
    }

    @Test
    public void shouldIgnoreNegativeYNumberPlacement() {

        assertThat(driver.setLocation(3, -2, FaceDirection.SOUTHWEST, Command.PLACE), is(false));
    }

}
