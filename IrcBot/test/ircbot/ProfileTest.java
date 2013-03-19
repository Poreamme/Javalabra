
package ircbot;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileTest {
    
    private Profile instance;
    
    public ProfileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Profile("irc.test.fi", "Testamme", "testBot", "#testChannel");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getServer method, of class Profile.
     */
    @Test
    public void testGetServer() {
        String expected = "irc.test.fi";
        assertEquals("getServer should have returned \""+ expected+ "\".", expected, instance.getServer());
    }

    /**
     * Test of getOwner method, of class Profile.
     */
    @Test
    public void testGetOwner() {
        String expected = "Testamme";
        assertEquals("getOwner should have returned \""+ expected+ "\".", expected, instance.getOwner());
    }

    /**
     * Test of getNick method, of class Profile.
     */
    @Test
    public void testGetNick() {
        String expected = "testBot";
        assertEquals("getNick should have returned \""+ expected+ "\".", expected, instance.getNick());
    }

    /**
     * Test of getChannel method, of class Profile.
     */
    @Test
    public void testGetChannel() {
        String expected = "#testChannel";
        assertEquals("getChannel should have returned \""+ expected+ "\".", expected, instance.getChannel());
    }

    /**
     * Test of toString method, of class Profile.
     */
    @Test
    public void testToString() {
        String expected = "irc.test.fi\n"
                + "Testamme\n"
                + "testBot\n"
                + "#testChannel";
        assertEquals("ToString should have returned \""+ expected+ "\".", expected, instance.toString());
    }

    /**
     * Test of equals method, of class Profile.
     */
    @Test
    public void testEquals() {
        boolean expected = true;
        Profile temp = new Profile(instance.getServer(), instance.getOwner(), instance.getNick(), instance.getChannel());
        assertEquals("Equals should have returned \"" + expected + "\".", expected, instance.equals(temp));
    }
}
