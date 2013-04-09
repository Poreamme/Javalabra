/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ircbot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Miika
 */
public class IrcClientBotAppTest {
    
    IrcClientBotApp instance;
    
    public IrcClientBotAppTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException, IOException {
        instance = new IrcClientBotApp();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ChooseRunningMode method, of class IrcClientBotApp.
     */
    @Test
    public void testChooseRunningMode() throws Exception {
        boolean expected = false;
        assertEquals("ChooseRunningMode should've returned " + expected + ", but returned " + instance.ChooseRunningMode(),expected, instance.ChooseRunningMode());
    }

    /**
     * Test of ChooseProfile method, of class IrcClientBotApp.
     */
    @Test
    public void testChooseProfile() throws Exception {
        
    }

    /**
     * Test of initialize method, of class IrcClientBotApp.
     */
    @Test
    public void testInitialize() throws Exception {
       /*
        * boolean expected = chooseRunningMode();
        * if(expected){
        *   assert("chooseRunningMode() should have returned " + expected, expected, chooseRunningMode());
        * }else{
        *   assert("chooseRunningMode() should have returned " + expected, expected, chooseRunningMode());
        * }
        *
        */
    }

}
