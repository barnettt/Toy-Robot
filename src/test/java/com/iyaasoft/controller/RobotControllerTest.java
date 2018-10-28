package com.iyaasoft.controller;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.List;

import com.iyaasoft.Application;
import com.iyaasoft.command.CommandObject;
import com.iyaasoft.enums.Command;
import com.iyaasoft.enums.FaceDirection;
import com.iyaasoft.robot.RobotCommander;
import com.iyaasoft.routes.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(properties = {"fileName=robotSimulatorCommandFile.txt", "test=false"})
public class RobotControllerTest {

    @Autowired
    RobotCommander robotCommander;


    @Test
    public void shouldImplementRobotCommands() throws Exception {
        robotCommander.clearCommand();

        robotCommander.getRobotDriver().execute(getCommands());
        assertThat(robotCommander.getRobotDriver().getRobot().getDirection(), is(FaceDirection.EAST));
        assertThat(robotCommander.getRobotDriver().getRobot().getPoint().getX(),is(1));
        assertThat(robotCommander.getRobotDriver().getRobot().getPoint().getY(), is(0));

    }

    @Test
    public void shouldScanAndRunCommands() throws Exception {
        robotCommander.clearCommand();
        List<CommandObject> commands = robotCommander.getRobotCommandsToExecute(null);

        assertThat(commands, notNullValue());
        assertThat(commands, hasSize(28));
        assertThat(robotCommander.getRobotDriver().getRobot().getDirection(), is(FaceDirection.NORTH));
        assertThat(robotCommander.getRobotDriver().getRobot().getPoint().getX(),is(4));
        assertThat(robotCommander.getRobotDriver().getRobot().getPoint().getY(), is(0));
    }

    private List<CommandObject> getCommands() {

        List<CommandObject> commands = new ArrayList<>();
        CommandObject command1 = new CommandObject(new Point(0, 1), FaceDirection.SOUTHWEST, Command.PLACE);
        CommandObject command2 = new CommandObject(null, null, Command.MOVE);
        CommandObject command3 = new CommandObject(null, null, Command.MOVE);
        CommandObject command4 = new CommandObject(null, null, Command.REPORT);
        CommandObject command5 = new CommandObject(new Point(0, 0), FaceDirection.NORTH, Command.PLACE);
        CommandObject command6 = new CommandObject(null, null, Command.MOVE);
        CommandObject command7 = new CommandObject(null, null, Command.RIGHT);
        CommandObject command8 = new CommandObject(null, null, Command.REPORT);

        commands.add(command1);
        commands.add(command2);
        commands.add(command3);
        commands.add(command4);
        commands.add(command5);
        commands.add(command6);
        commands.add(command7);
        commands.add(command8);

        return commands;
    }


}
