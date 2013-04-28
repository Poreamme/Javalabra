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
public class IrcBotDebugTest {
    
    public IrcBotDebug instance;
    
    public IrcBotDebugTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        instance = new IrcBotDebug(new Profile("irc.inet.fi", "Poreamme", "PoreBot", "#Porebottaus"));
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
