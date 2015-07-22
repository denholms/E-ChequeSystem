package eCheque.Test;


/**
 * Created by bolinkd on 13/07/15.
 */

import eCheque.RSAGenerator;
import org.junit.*;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;


public class RSAGeneratorTest {
    public RSAGeneratorTest(){

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("\nSETUP CLASS RUNNING -- Nothing to do though");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        System.out.println("\nTEARDOWN CLASS RUNNING -- Nothing to do though");
    }

    @Before
    public void setUp() {
        System.out.println("\nSETUP IS RUNNING -- Nothing to do though");
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN IS RUNNING -- Nothing to do though");
    }

    @Test
    public void testRSAGenerator() throws NoSuchAlgorithmException{
        System.out.println("testRSAGenerator");

        RSAGenerator instance = new RSAGenerator();
        String expResult = "java.security.KeyPair@108c4c35";
        KeyPair result = instance.GenerateRSAKeys();
        Assert.assertNotNull("No KeyPair Generated" ,result);
        Assert.assertNotNull("No Private Key"       ,result.getPrivate());
        Assert.assertNotNull("No Public Key"        ,result.getPublic());
    }


}
