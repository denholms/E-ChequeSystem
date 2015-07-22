package eCheque.Test;


/**
 * Created by bolinkd on 13/07/15.
 */

import eCheque.RSAGenerator;
import org.junit.*;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;


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
    public void KeysMatchRSAGenerator() throws NoSuchAlgorithmException{
        System.out.println("testRSAGenerator");
        RSAGenerator instance = new RSAGenerator();
        KeyPair pair = instance.GenerateRSAKeys();

        Key key = pair.getPrivate();

        if (key instanceof RSAPrivateCrtKey) {
            RSAPrivateCrtKey pvt = (RSAPrivateCrtKey) key;
            BigInteger e = pvt.getPublicExponent();
            RSAPublicKey pub = (RSAPublicKey) pair.getPublic();
            Assert.assertTrue("Keys Match",e.equals(pub.getPublicExponent()) && pvt.getModulus().equals(pub.getModulus()));
        }
    }



}
