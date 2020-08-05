package com.example.project;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMain{
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    private String getOutput() {
        return testOut.toString();
    }
    @BeforeEach
    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }
    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
    @Test
    public void testBasic()
    {
        Main.main(new String[0]);
        String actual = getOutput();

        Pattern p = Pattern.compile("^I love dogs! I have a dog named Coco. My dog's very smart!(.*)$", Pattern.DOTALL);
        Matcher m = p.matcher(actual);
        assertTrue(m.find(), "Expected string matching 'I love dogs! I have a dog named Coco. My dog's very smart!'.\nGot: "+actual);
    }
    @Test
    public  void testChallenge(){
        Main.main(new String[0]);
        String actual = getOutput();
        Pattern p = Pattern.compile("(.*)I love dogs and cats! I have a dog named Coco. My dog's very smart!(.*)", Pattern.DOTALL);
        Matcher m = p.matcher(actual);
        assertTrue(m.find(), "Expected string matching 'I love dogs and cats! I have a dog named Coco. My dog's very smart!'.\nGot: "+actual);
    }
    @Test
    public void testCounter(){
        Main.main(new String[0]);
        String actual = getOutput();
        Pattern p = Pattern.compile("(.*)3(.*)");
        Matcher m = p.matcher(actual);
        assertTrue(m.find(), "Expected an output line with counter = 3. \nGot: "+actual);
    }
}