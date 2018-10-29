package com.iyaasoft.scanner;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

import java.io.ByteArrayInputStream;

import com.iyaasoft.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(properties = {"fileName=robotSimulatorCommandFile.txt", "test=false"})
public class RobotScannerTest {

    @Autowired
    private RobotScanner robotScanner;

    @Test
    public void shouldReadInputFromKeyboard() {
        ByteArrayInputStream in = new ByteArrayInputStream("PLACE 0,0,NORTH\nq\n".getBytes());
        String result = robotScanner.getByteArrayInput(in);
        assertThat(result, notNullValue());
        assertThat(result, containsString("PLACE 0,0,NORTH"));
    }
}
