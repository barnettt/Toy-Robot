package com.iyaasoft.robot;


import static java.util.stream.Collectors.toList;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import com.iyaasoft.command.CommandObject;
import com.iyaasoft.rules.RobotFunctions;
import com.iyaasoft.scanner.RobotScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class RobotCommander {

    @Autowired
    RobotScanner scanner;

    @Autowired
    RobotDriver robotDriver;

    @Value("${fileName}")
    String fileName;

    private final String[] SPLIT_BY = new String[]{" ",","};

    private static final String PLACE_COMMAND = "Place";

    private static final String END_OF_LINE = "\n";

    private BiFunction splitFunction = RobotFunctions.splitStringFunction;

    public List<String> readCommandInputFile(final String fileName) throws Exception {

        List<String> commandList;

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            commandList = stream.map(String::toUpperCase)
                    .collect(toList());
        }
        return commandList;
    }

    public List<CommandObject> getRobotCommandsToExecute(String keyBoardValue) throws Exception {

        List<String> commands;
        if (keyBoardValue != null) {
            commands = (List<String>)splitFunction.apply(keyBoardValue, new String[]{END_OF_LINE});
        } else {
            commands = readCommandInputFile(fileName);
        }

        List<CommandObject> commandsToBeExcuted = new ArrayList();

        buildCommandList(commands, commandsToBeExcuted);

        return commandsToBeExcuted;
    }


    public List<CommandObject> getRobotCommandsFromKeyboard() throws Exception {

        String input = scanner.getKeyboardInput();
        return getRobotCommandsToExecute(input);
    }

    public RobotDriver getRobotDriver() {
        return robotDriver;
    }

    public void clearCommand() {
        getRobotDriver().getRobot().clearCommand();
    }

    private void buildCommandList(final List<String> commands, final List<CommandObject> commandsToBeExcuted) {

        AtomicInteger count = new AtomicInteger(0);

        commands.stream().map(in -> getByteInputStream(in))
                .map(bytes -> scanner.getByteArrayInput(bytes))
                .map(value -> splitFunction.apply(value, SPLIT_BY))
                .forEach(item -> {
                    CommandObject command = new CommandObject();
                    for (Object value : (List) item) {

                        String val = removeEndLineCharFromString((String)value);

                        if (PLACE_COMMAND.equalsIgnoreCase(val)) {
                            command.isAction(val, command);
                            count.getAndIncrement();
                        } else {
                                if(count.get() > 0) {
                                    command.isAction(val, command);
                                    command.idDirection(val, command);
                                    command.isInt(val, command);
                                }
                        }
                    }
                    commandsToBeExcuted.add(command);
                });
    }

    private String  removeEndLineCharFromString(String value) {
        StringBuilder buff = new StringBuilder(value);
        if(value.contains(END_OF_LINE)){
            buff = new StringBuilder(value);
            buff.deleteCharAt(buff.indexOf(END_OF_LINE));
        }
        return buff.toString();
    }



    private ByteArrayInputStream getByteInputStream(String command) {

        return new ByteArrayInputStream(command.getBytes());
    }

    public void executeCommands(final List<CommandObject> commands) {
        robotDriver.execute(commands);
    }
}