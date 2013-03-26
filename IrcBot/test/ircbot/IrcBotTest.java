/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ircbot;

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
public class IrcBotTest {
    
    public IrcBot instance;
    
    public IrcBotTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        instance = new IrcBot(new Profile("irc.inet.fi", "Poreamme", "PoreBot", "#Porebottaus"));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of send method, of class IrcBot.
     */
    @Test
    public void testSend() {
        int expected = 1;
        
        //vaiheessa....
       
    }
}
