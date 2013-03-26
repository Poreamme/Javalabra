
package ircbot;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SavedProfilesTest {
    
    private SavedProfiles instance;
    
    public SavedProfilesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new SavedProfiles();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveProfile method, of class SavedProfiles.
     */
    @Test
    public void testSaveProfile() {
        boolean expected = true;
        boolean temp = instance.saveProfile(new Profile("testi.serveri.fi", "testiOwner", "testiNick", "#testiKanava"));
        assertEquals("LoadProfiles should have returned \"" + expected + "\"." + " and was + "+ temp + "\"." , expected, temp);
    }

    /**
     * Test of loadProfiles method, of class SavedProfiles.
     */
    @Test
    public void testLoadProfiles() {
        HashMap<String, Profile> expected = new HashMap<String, Profile>();
        expected.put("irc.inet.fi", new Profile("irc.inet.fi", "Poreamme", "PoreBot", "#Porebottaus"));
        expected.put("irc.paivola.fi", new Profile("irc.paivola.fi", "Yakuchi", "YakuBot", "#testailuva"));
        expected.put("irc.sorcery.net", new Profile("irc.sorcery.net", "LettiPois", "LettiBot", "#GrindBot"));

        assertEquals("LoadProfiles should have returned \"" + expected + "\"." + " and was + "+ instance.loadProfiles() + "\"." , expected, instance.loadProfiles());
    }
}
