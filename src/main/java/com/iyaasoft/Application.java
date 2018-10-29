package com.iyaasoft;

import java.util.List;

import com.iyaasoft.command.CommandObject;
import com.iyaasoft.robot.IRobot;
import com.iyaasoft.robot.RobotCommander;
import com.iyaasoft.routes.ValidRoutes;
import com.iyaasoft.scanner.RobotScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static SpringApplication app;
    @Value("${test}")
    boolean test;
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${fileName}")
    private String fileName;

    public static void main(String[] args) {

        app = new SpringApplication(Application.class);
        app.run();
    }

    public static void exitApplication(ApplicationContext ctx, int returnCode) {
        app.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 0;
            }
        });

    }

    @Bean
    public IRobot irobot() {
        return new IRobot();
    }

    @Bean
    public ValidRoutes validRoutes() {
        return new ValidRoutes();
    }

    @Bean
    public RobotScanner scanner() {
        return new RobotScanner();
    }

    @Autowired
    @Bean
    public ApplicationContext context(ApplicationContext context) {
        return context;
    }

    @Override
    public void run(final String... args) throws Exception {

        RobotCommander robotCommander = (RobotCommander) applicationContext.getBean("robotCommander");
        List<CommandObject> commands = null;
        try {
            if (args != null && args.length > 0) {
                commands = robotCommander.getRobotCommandsFromKeyboard();
                robotCommander.executeCommands(commands);
            } else if (test) {
                // do nothing
            } else {
                commands = robotCommander.getRobotCommandsToExecute(null);
                robotCommander.executeCommands(commands);
            }

        } catch (Exception e) {

            System.out.println("Whoops error Toy Robot Simulator exiting ...  \n\n" + e);
            e.printStackTrace();

        }
        System.out.println("Toy Robot Simulator ... shutting down!!  \n\n");

    }
}

